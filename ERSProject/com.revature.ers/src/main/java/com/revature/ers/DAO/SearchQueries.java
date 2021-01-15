package com.revature.ers.DAO;

public class SearchQueries {
	
	public static final String VIEW_COMPANY_REQUESTS_HISTORY = "select ers_reimb.reimb_id, ers_reimb.reimb_amount, "
			+ "	ers_reimb.reimb_submitted, ers_reimb.reimb_resolved, "
			+ "	ers_reimb.reimb_description, ers_reimb.reimb_receipt, ers_reimb.reimb_author, "
			+ "	ers_reimb.reimb_resolver, ers_reimb.reimb_status_id, ers_reimb.reimb_type_id, "
			+ "	ers_users.user_first_name, ers_users.user_last_name	"
			+ "	from ers_reimb "
			+ "	inner join ers_users"
			+ "	on (ers_reimb.reimb_author = ers_users.ers_user_id)"
			+ "order by ers_reimb.reimb_id;";
	
	public static final String VIEW_EMPLOYEE_REQUESTS_HISTORY = "select ers_reimb.reimb_id, ers_reimb.reimb_amount, "
			+ "	ers_reimb.reimb_submitted, ers_reimb.reimb_resolved, "
			+ "	ers_reimb.reimb_description, ers_reimb.reimb_receipt, ers_reimb.reimb_author, "
			+ "	ers_reimb.reimb_resolver, ers_reimb.reimb_status_id, ers_reimb.reimb_type_id, "
			+ "	ers_users.user_first_name, ers_users.user_last_name	"
			+ "	from ers_reimb "
			+ "	inner join ers_users"
			+ "	on (ers_reimb.reimb_author = ers_users.ers_user_id)"
			+ " where ers_reimb.reimb_author = ? "
			+ "order by ers_reimb.reimb_id;";
	
	public static final String ADD_RESOLVER_TO_HISTORY = "select ers_reimb.reimb_id, ers_users.user_first_name, ers_users.user_last_name	\r\n"
			+ "			from ers_reimb  "		
			+ "			inner join ers_users "
			+ "			on (ers_reimb.reimb_resolver = ers_users.ers_user_id)"
			+ "			order by ers_reimb.reimb_id;";
	
	public static final String ADD_EMPLOYEE_RESOLVER_TO_HISTORY = "select ers_reimb.reimb_id, ers_users.user_first_name, ers_users.user_last_name	\r\n"
			+ "			from ers_reimb  "
			+ "			inner join ers_users "
			+ "			on (ers_reimb.reimb_resolver = ers_users.ers_user_id)"
			+ "         where ers_reimb.reimb_author = ?"
			+ "			order by ers_reimb.reimb_id;";
	
	public static final String VIEW_SINGLE_EMPLOYEE_REQUEST_HISTORY = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, "
			+ " reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id "
			+ "	from ers_reimb "
			+ "	where reimb_author = ? "
			+ "	order by reimb_submitted; ";
	
	public static final String VIEW_PENDING_REQUESTS = "select ers_reimb.reimb_id, ers_reimb.reimb_amount, ers_reimb.reimb_submitted,"+
			" ers_reimb.reimb_description," + 
			" ers_reimb.reimb_type_id, ers_reimb.reimb_author, ers_users.user_first_name,"+
			" ers_users.user_last_name"+ 
			" from ers_reimb"+
			" inner join ers_users"+
			" on ers_reimb.reimb_author = ers_users.ers_user_id"+ 
			" where (ers_reimb.reimb_status_id =  1) AND (ers_reimb.reimb_author != ?)"+
			" order by reimb_submitted;";
	
	public static final String SET_REQUEST_STATUS = "UPDATE ers_reimb "
			+ " set reimb_status_id = ?, "
			+ " reimb_resolved = current_timestamp, "
			+ " reimb_resolver = ? "
			+ " where reimb_id = ? and reimb_author != ?;"; // request author cannot approve/reject their own request 
	
	public static final String MAKE_NEW_REQUEST = "INSERT INTO ers_reimb(reimb_id, reimb_amount, reimb_submitted,"
			+ " reimb_description, reimb_status_id, reimb_author, reimb_type_id)"
			+ " VALUES (default, ?, current_timestamp, ?, 1, ?, ?);";
	
	public static final String USER_LOGIN = "select ers_user_id, user_first_name, "
			+ "user_last_name, user_email, user_role_id "
			+ "from ers_users "
			+ "where ers_password = ? and ers_username = ? "
			+ ";";
	
	public static final String UPDATE_REIMBURSEMENT_RECEIPT_IMAGE = "UPDATE ers_reimb "
			+ " SET reimb_receipt = ? "
			+ " WHERE reimb_id = ?;"; // available anytime 
	
	public static final String UPDATE_REIMBURSEMENT_REQUEST = "Build me dynamically"; // available only on pending status requests

	public static final String LAST_REQUEST_ENTERED = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, " 
			+" reimb_receipt, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id "
			+" FROM ers_reimb ORDER BY reimb_submitted DESC LIMIT 1; ";
	
}
