package com.revature.ers.servlet.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.model.Request;
import com.revature.ers.model.RequestDTO;
import com.revature.ers.service.UserActions;
import com.revature.ers.service.impl.UserActionsImpl;

public class NewRequestController {
	
	private UserActions userActions = new UserActionsImpl();
	private ObjectMapper mapper = new ObjectMapper();
	private RequestDTO requestDTO = null;
//	InputStream inputStream = null; // input stream of the upload file

	public void request(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		HttpSession ses = req.getSession(false);
		
		if((boolean) ses.getAttribute("loggedIn")) {
			
			if(req.getMethod().equals("POST")) {
				BufferedReader reader = req.getReader();
				StringBuilder stringBuilder = new StringBuilder();
				String line = reader.readLine();
//				System.out.println(line);
				
				while(line!=null) {
					stringBuilder.append(line);
					line = reader.readLine();
				}	
				
				String body = new String(stringBuilder);
				System.out.println(body);
				requestDTO = mapper.readValue(body, RequestDTO.class);
				Request returnRequest = userActions.makeRequest(requestDTO);
				String json = mapper.writeValueAsString(returnRequest);
				System.out.println(json);
				res.getWriter().print(json);
				
				if(returnRequest.getReimbId() != 0) {		
					res.setStatus(200);					
				} else {
					res.setStatus(401);
					res.getWriter().print("Unable to process request.");	
				}	
			} // end first if statement checking login valid	
		} else {
			res.setStatus(401);
			res.getWriter().print("Invalid credentials.");				
		}
	} // end method
} // end class


//
//package com.revature.ers.model;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//

//       
//      InputStream inputStream = null; // input stream of the upload file
//       
//      // obtains the upload file part in this multipart request
//      Part filePart = request.getPart("receipt");
//      if (filePart != null) {
//          // prints out some information for debugging
//          System.out.println(filePart.getName());
//          System.out.println(filePart.getSize());
//          System.out.println(filePart.getContentType());
//           
//          // obtains input stream of the upload file
//          inputStream = filePart.getInputStream();
//      }
//       
//      Connection conn = null; // connection to the database
//      String message = null;  // message will be sent back to client
//       
//      try {
//          // connects to the database
//          DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//          conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
//
//          // constructs SQL statement
//          String sql = "INSERT INTO contacts (first_name, last_name, photo) values (?, ?, ?)";
//          PreparedStatement statement = conn.prepareStatement(sql);
//          statement.setString(1, firstName);
//          statement.setString(2, lastName);
//           
//          if (inputStream != null) {
//              // fetches input stream of the upload file for the blob column
//              statement.setBlob(3, inputStream);
//          }
//
//          // sends the statement to the database server
//          int row = statement.executeUpdate();
//          if (row > 0) {
//              message = "File uploaded and saved into database";
//          }
//      } catch (SQLException ex) {
//          message = "ERROR: " + ex.getMessage();
//          ex.printStackTrace();
//      } finally {
//          if (conn != null) {
//              // closes the database connection
//              try {
//                  conn.close();
//              } catch (SQLException ex) {
//                  ex.printStackTrace();
//              }
//          }
//          // sets the message in request scope
//          request.setAttribute("Message", message);
//           
//          // forwards to the message page
//          getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
//      }
//  }
//}
