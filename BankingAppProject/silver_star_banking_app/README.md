ROC1 Banking API 

Project Requirements:
______________________________________________________________________________________________________________
The Banking API will manage the bank accounts of its users. It will be managed by the Bank's employees and admins. Employees and Admins count as Standard users with additional abilities. All users must be able to update their personal information, such as username, password, first and last names, as well as email. Accounts owned by users must support withdrawal, deposit, and transfer. Transfer of funds should be allowed between accounts owned by the same user, as well as between accounts owned by different users. Standard users should be able to register and login to see their account information. They can have either Checking or Savings accounts. Employees can view all customer information, but not modify in any way. Admins can both view all user information, as well as directly modify it.
______________________________________________________________________________________________________________



Implementation
______________________________________________________________________________________________________________
This implementation of the assigned project is called Silver Star Banking. 

This simple project is implemented with a Java 8 backend in a Maven project. The repository was implemented using Postgres SQL writing to a local RDB. Interface in the DAO layer via JDBC. The database contains 3NF tables with an ERD diagram included in the github. 

Customer interface is through the console. All of the requirements have been met and the log files, utilizing log4j, record not only the options presented to the user but the user input for each step as well. 

Unfortunately, due to an error in cleaning up repositories on github, I only have the initial build I pushed to github of this project and not the finished code.