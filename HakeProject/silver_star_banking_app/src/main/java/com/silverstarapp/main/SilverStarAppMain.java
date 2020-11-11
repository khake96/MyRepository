package com.silverstarapp.main;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.silverstarapp.dao.dbutil.PostgresSqlConnection;
import com.silverstarapp.dao.impl.EmpoloyeeDaoImpl;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.util.MenuConsoles;
import com.silverstarapp.main.util.PresentationLevelPrinter;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.AccountHistory;
import com.silverstarapp.model.Address;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;
import com.silverstarapp.model.User;
import com.silverstarapp.service.CustomerActions;
import com.silverstarapp.service.EmployeeActions;
import com.silverstarapp.service.UserActions;
import com.silverstarapp.service.impl.CustomerActionsImpl;
import com.silverstarapp.service.impl.EmployeeActionsImpl;
import com.silverstarapp.service.impl.UserActionsImpl;

/* This is the executable class file for the Silver Star Banking
 * App. 
 */

public class SilverStarAppMain {
	
	public static Logger log=Logger.getLogger(SilverStarAppMain.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		//PropertyConfigurator.configure("log4j.properties");
		int selection = 0;
		MenuConsoles menu = new MenuConsoles();
		Login login = new Login();
		EmployeeActions employeeActions = new EmployeeActionsImpl();
		CustomerActions customerActions = new CustomerActionsImpl();
		UserActions userActions = new UserActionsImpl();
		PresentationLevelPrinter.printSilverStarLogo();
		NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
		
		do {
			selection = menu.topLevelMenu();
			mainMenu:
			switch (selection) {	
				case 1: { // Entering Customer login menu 
					login = menu.loginMenu();
					Customer customer = null;
					int customerSelection = -1;
					try {
						try {
							customer = customerActions.customerLogin(login);
						} catch (NullPointerException e) {}
						if(customer.getFirstName()==null) {
							log.warn("Not a valid customer username or password.");
							break mainMenu;
						}
						// Login is Customer
						do {
							customerSelection = menu.customerMenu(customer);
							customerMenu:
								switch (customerSelection) {
								case 1: { //View Account Balance
									try {
										int accountNumber = menu.getAccountNumber();
										Account account = customerActions.getAccount(customer.getId(), accountNumber);
										if(account.getAccountNo()!=0) {
											log.info(account.toString());
										}
									} catch (BusinessException b) {
										log.warn(b.getMessage());
										break customerMenu;
									}
									break; 
								}
								case 2: { //Deposit Funds
									int accountNumber = menu.getAccountNumber();
									double depositAmount = menu.getDepositAmount();
									Account account = null;
									OffsetDateTime dt = OffsetDateTime.now();
									dt = dt.minusSeconds(20);
									try {
										account = customerActions.getAccountNoID(accountNumber);
										account = customerActions.depositAccount(account, depositAmount);
										} catch (BusinessException b) {
											log.info(b.getMessage());
											break customerMenu;
										} catch (NumberFormatException e) {
											log.debug(e.getMessage());
											break customerMenu;
										}
									if(account.getAccountNo()!=0) {
										log.info("Successful deposit into account number "+account.getAccountNo());
										log.info("Current balance is: "+ usd.format(account.getBalance()));
									}
									break;
								}
								case 3: { // Withdraw Funds
									try {
										int accountNumber = menu.getAccountNumber();
										double debitAmount = menu.getWithdrawAmount();
										Account account = customerActions.getAccountNoID(accountNumber);
										account = customerActions.debitAccount(customer.getId(), account, debitAmount);
										if(account.getAccountNo()!=0) {
											log.info("\nAccount debit successful.");
											log.info("Your new balance for account number " + account.getAccountNo() + " is "+ usd.format(account.getBalance()));
										}
									} catch (BusinessException b) {
										log.warn(b.getMessage());
										break customerMenu;
									}
									break;
								}
								case 4: { // Transfer Funds
									List<Account> accountList = new ArrayList();
									int accountNumber = 0;
									try {
										log.info("Account for withdrawal: ");
										accountNumber = menu.getAccountNumber();
										Account debitAccount = customerActions.getAccount(customer.getId(), accountNumber);
										// Check if account is valid.
										if (debitAccount.getAccountNo()!=0) {
											log.info("Account for deposit: ");
											int depositAccountNumber = menu.getAccountNumber();					
											Account creditAccount = customerActions.getAccountNoID(depositAccountNumber);										
											// Check if credit account is valid
											if (creditAccount.getAccountNo() != 0) {
												double transferAmount = menu.getTransferAmount();
												accountList =customerActions.transferFunds(customer.getId(), debitAccount, creditAccount, transferAmount);	
												if((accountList.get(0).getAccountNo()!=0 && accountList.get(1).getAccountNo()!=0)) {
													log.info("\nTransfer successful.");
													log.info("Debit account number " + accountList.get(0).getAccountNo() +
															" current balance: " + usd.format(accountList.get(0).getBalance()));
													log.info("Deposit account number " + accountList.get(1).getAccountNo() +
															" current balance: " + usd.format(accountList.get(1).getBalance()));
												} else log.info("Unable to complete transfer request! Please try again."); 
											} // End invalid deposit account number if
										} // End invalid debit account number if
									} catch (BusinessException b) {
										log.warn(b.getMessage());
										break customerMenu;
									} catch (IndexOutOfBoundsException e) { 
										log.warn(e.getMessage());
										log.info("Account number "+ accountNumber + "insufficient funds. Please try again.");}
									break;

								}		
								case 5: { // Apply for a new Account
									double startingBalance = menu.getStartingBalance();
									double incomeGross = menu.getIncome();
									double expenses = menu.getExpenses();
									AccountApplication application = new AccountApplication();
									try {
										application = customerActions.makeApplication(customer.getId(), startingBalance, incomeGross, expenses);	
										if(application.getApplicationID()!=0) {
											log.info("\nApplication is successful.");
											log.info("Your application id is: " + application.getApplicationID());
										}
									} catch (BusinessException b) {
										log.warn(b.getMessage());
										break customerMenu;
									}
									break;
								}
								case 0: { // customer wishes to leave customer menu
									break mainMenu;
								}
								default: {
									log.warn("Please enter digits 0-5. Thank you.");
									break;
								}
							} // end switch	
						}  while (customerSelection != 0);
					} catch (BusinessException b) {
						log.warn(b.getMessage());
						break mainMenu;
					}
					catch (NullPointerException e) {
						log.warn("Please enter a valid selection. Thank you.");
						break mainMenu;
					}
				} // end customer menu - top level case 1			
			 
				case 2: { // Entering Employee Login - top level case 2
					login = menu.loginMenu();
					Employee employee = null;
					int employeeSelection = -1;
					try {
						try {
							employee = employeeActions.employeeLogin(login);
						} catch (BusinessException e) {
							log.warn("Not a valid employee username or password.");
						} 
						  catch (SQLException e) {
							log.debug(e.getMessage());
						} catch (NullPointerException e) {
							log.debug("Not a valid employee username or password.");
						}
						if(employee.getFirstName()==null) {
							log.warn("Not a valid employee username or password.");
							break mainMenu;
						}
						// Login is Employee
					do {
						try { 
							employeeSelection = menu.employeeMenu(employee);
							employeeMenu:
							switch(employeeSelection) {
							case 1: { // View Account Applications
								try {
									List<AccountApplication> applicationList = new ArrayList(employeeActions.getAllApplications());
										log.info(employeeActions.formatApplicationList(applicationList));
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									break employeeMenu;
								} 
								break;	
							} // End case 1 switch account applications
							case 2: { // Approve account application
								int applicationID = 0;
								boolean approved = false;
								boolean success = false;
								// get user input for application id and boolean
								try {
									applicationID = menu.getAccountApplicationNumber();
									int i = menu.approveApplication();
									if (i==1) approved = true;
									else if (i==0) approved = false;
									else {
										log.warn("Please enter 1 or 0.");
										break employeeMenu;
									}
								} catch (NumberFormatException n) {
									log.debug(n.getMessage());
									log.info("Please enter 1 or 0.");
									break employeeMenu;
								}
								try { // Pass account application ID and approval status to service layer
									success = employeeActions.approveApplication(applicationID, approved);
									if(success && approved) {
										log.info("\nApplication ID "+applicationID+" successfully approved.");
										log.info("New acccount number is: " + EmpoloyeeDaoImpl.getLatestAccount());
									} else if (success) {
										log.info("\nApplication ID "+applicationID+" successfully denied.");
									} else log.info(applicationID + " is not a valid application number.");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									break employeeMenu;
								}
								break;
							}
							case 3: { // View customer account
								int accountNumber = menu.getAccountNumber();
								try {
									AccountHistory customerAccount = employeeActions.viewCustomerAccount(accountNumber);
									log.info("\nRequested Account: \n" + customerAccount.toString());
								} catch (Exception e) {
									log.warn(e.getMessage());
									break employeeMenu;
								}
								break;			
							}
							case 0: { 
								// User chooses main menu
								break mainMenu;				
							}
							default: {
								log.warn("Invalid selection, please choose 0-3.");
							}
						} // end switch for employee login selection
						} catch (SQLException e) {
							log.warn(e.getMessage());
							break mainMenu;
						} catch (NullPointerException e) {
							log.warn(e.getMessage());
							break mainMenu;
						}
					} while (employeeSelection != 0);
				} catch (NumberFormatException e) {
					log.warn(e.getMessage());
					break mainMenu;
				} catch (NullPointerException e) {
					log.warn(e.getMessage());
					break mainMenu;
				}
				break;
			} // end employee login			
				case 3: { // Entering new user menu
					// Collect data to complete a new user
					User user = menu.getUser();
					try {
						Address address = menu.getAddress();
						user.setAddress(address);
					} catch (NullPointerException e) {
						log.debug(e.getMessage());
						log.info("Error creating new address. Please try again.");
						break mainMenu;
					} catch (NumberFormatException e) {
						log.debug(e.getMessage());
						log.info("Error creating new address. Please try again.");
						break mainMenu;
					}
					// Check data for business valid and create a new customer
					Customer customer = new Customer();
					try {
						customer = userActions.newRegistrationApplication(user);
						if (customer.getId()>0) {
							log.info("Welcome to Silver Star Banking! You are now"
									+ "a customer! Your customer ID number is: " + customer.getId());
							log.info("Please log in again to apply for an account!");
						}
					} catch (BusinessException e1) {
						log.warn(e1.getMessage());
						break mainMenu;
					} catch (NullPointerException e1) {
						log.debug(e1.getMessage());
						log.info("Error creating new Silver Star Banking customer. Please try again.");
						break mainMenu;
					} catch (NumberFormatException e) {
						log.debug(e.getMessage());
						log.info("Error creating new Silver Star Banking customer. Please try again.");
						break mainMenu;
					}
					break;
				} // End User Menu - top level case 3
				case 0: { 
					// User chooses to exit Silver Star app 
					PresentationLevelPrinter.printSilverStarExit();
					menu.scanner.close();
					try {
						PostgresSqlConnection.getConnection().close();
					} catch (ClassNotFoundException | SQLException e) {
						log.debug(e.getMessage());
					}
					break;						
				}
				default:
					log.info("Please enter digits 1, 2, or 3. Thankyou!");
					break;
				} // end of top level switch statement
		} while (selection!=0);
	} // end of static main void
} // end of class


