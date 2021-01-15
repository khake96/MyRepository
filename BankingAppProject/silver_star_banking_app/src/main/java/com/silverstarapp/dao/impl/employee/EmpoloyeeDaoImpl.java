package com.silverstarapp.dao.impl.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.silverstarapp.dao.dbutil.EmployeeSearchQueries;
import com.silverstarapp.dao.dbutil.PostgresSqlConnection;
import com.silverstarapp.dao.employee.EmployeeDAO;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App Employee DAO implementation provides
 * all of the body for the requirements specified in the EmployeeDAO 
 * Interface.
 */

public class EmpoloyeeDaoImpl implements EmployeeDAO {

	@Override
	public List<Account> viewAllAccounts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account viewCustomerAccount(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountApplication getNextApplication() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountApplication> getAllApplications() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int employeeLogin(Login login) throws BusinessException {
		int employeeID=-1;
		String employeePassword = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = EmployeeSearchQueries.EMPLOYEE_LOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUserName());
			employeePassword = login.toString(login.getPassword());
			preparedStatement.setString(2, employeePassword);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employeeID = resultSet.getInt("employee_ID");	
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");	
		}
		return employeeID;
	}

	@Override
	public Employee setEmployee(int employeeID) throws BusinessException {
		Employee employee = new Employee();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = EmployeeSearchQueries.EMPLOYEE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee.setId(employeeID); 
				employee.setFirstName(resultSet.getString("firstname"));
				employee.setLastName(resultSet.getString("lastname"));
				employee.setContact(resultSet.getString("contact"));		
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid employee ID!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid employee ID!");	
		}
		return employee;
	}
	
	

//	@Override
//	public log viewCustomerLog(Customer customer) throws BusinessException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
