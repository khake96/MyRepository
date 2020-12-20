package com.revature.ers.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.servlet.controllers.GetEmployeeHistoryController;
import com.revature.ers.servlet.controllers.GetRequestHistoryController;
import com.revature.ers.servlet.controllers.LoginController;
import com.revature.ers.servlet.controllers.LogoutController;
import com.revature.ers.servlet.controllers.NewRequestController;
import com.revature.ers.servlet.controllers.PendingRequestController;
import com.revature.ers.servlet.controllers.ProcessRequestController;
import com.revature.ers.servlet.controllers.RequestStatusController;

public class MasterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private LoginController loginController = new LoginController();
	private LogoutController logoutController = new LogoutController();
	private NewRequestController newRequestController = new NewRequestController();
	private RequestStatusController requestStatus = new RequestStatusController();
	private ProcessRequestController processRequests = new ProcessRequestController();
	private PendingRequestController pendingRequests = new PendingRequestController();
	private GetRequestHistoryController requestHistory = new GetRequestHistoryController();
	private GetEmployeeHistoryController employeeHistory = new GetEmployeeHistoryController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
			res.setContentType("application/json");
			// By default tomcat will send back a successful status code if it finds a
			// servlet method.
			// Because all requests will hit this method, we are defaulting to not found and
			// will override for success requests.
			res.setStatus(404);			
			final String URI = req.getRequestURI().replace("/revature_ers/", "");	
			System.out.println(URI);
			switch (URI) {
			case "login":
				loginController.login(req, res);
				break;
			case "logout":
				logoutController.logout(req, res);
				break;
			case "request":
				newRequestController.request(req, res);
				break;
			case "status":
				requestStatus.status(req, res);
				break;
			case "pending":
				pendingRequests.getPendingRequests(req, res);
				break;
			case "process":
				processRequests.process(req, res);
				break;
			case "history":
				requestHistory.getRequestHistory(req, res);
				break;
			case "employeeHistory":
				employeeHistory.getRequestHistory(req, res);
				break;
			}
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		doGet(req, res);
	}
	
	

}
