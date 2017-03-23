package Controller;

import java.io.IOException;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import serviceLayer.validation;
import DAO.loginDAO;
import DAO.registrationDAO;
import DAO.uploadDAO;
import Model.loginModel;
import Model.registration;
import Model.retrieveFile;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String flag=request.getParameter("flag");
		
		if(flag.equals("login")){
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		loginModel login = new loginModel();
		login.setUser_id(userId);
		login.setPassword(password);
		
		validation val = new validation();
		String validationMandatory=val.mandatory_login(login);
		
		if(validationMandatory.equals("All fields are Mandatory") || validationMandatory.equals("User Id is mandatory!") || validationMandatory.equals("Password is mandatory!")){
			request.setAttribute("error", validationMandatory);
			RequestDispatcher rd=request.getRequestDispatcher("signin.jsp");
			rd.forward(request, response);
		}
		String matchFields=val.parameters_login(login);
		if( matchFields.equals("Password Should be alphabetic only!") || matchFields.equals("User Id Should be numeric only!")){
			request.setAttribute("error", matchFields);
			RequestDispatcher rd=request.getRequestDispatcher("signin.jsp");
			rd.forward(request, response);
		}
		
		
		loginDAO dao = new loginDAO();
		String value=dao.loginUser(login);
		
		
		
		
		if(value.equals("Invalid User Credentials")){
			request.setAttribute("error", "Invalid UserId and Password");
			RequestDispatcher rd=request.getRequestDispatcher("signin.jsp");
			rd.forward(request, response);
		}
		else{
			session.setAttribute("userId", userId);
			session.setAttribute("firstName", value);
			List<retrieveFile> media_files = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(userId);
			uploadDAO dao1= new uploadDAO();
			media_files=dao1.fetchMediaList(rf);
			
			session.setAttribute("media_files", media_files);
			response.sendRedirect("main.jsp");
			
		}
	}
	
		
		
		if(flag.equals("register")){
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String emailId = request.getParameter("emailId");
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			
			registration reg=new registration();
			reg.setFirst_name(firstName);
			reg.setLast_name(lastName);
			reg.setEmail_id(emailId);
			reg.setUser_id(userId);
			reg.setPassword(password);
			reg.setConfirmpassword(confirmPassword);
			
			
			
			validation val = new validation();
			String validationMandatory=val.mandatory(reg);
			if(validationMandatory.equals("All fields are Mandatory") || validationMandatory.equals("First name is mandatory!") || validationMandatory.equals("Last name is mandatory!") || validationMandatory.equals("Email Id is mandatory!") || validationMandatory.equals("User Id is mandatory!") || validationMandatory.equals("Password is mandatory!") || validationMandatory.equals("Confirm Password is mandatory!")){
				request.setAttribute("error", validationMandatory);
				RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
			}
			else
			{
				String matchFields=val.parameters(reg);
				if(matchFields.equals("Password and Confirm - Password Should be Same!") || matchFields.equals("Password Should be alphabetic only!") || matchFields.equals("User Id Should be numeric only!")){
					request.setAttribute("error", matchFields);
					RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
				}
				else{
					registrationDAO regdao=new registrationDAO();
					String validateRegistration=regdao.validate(reg);
					
					if(validateRegistration.equals("Email-Id is already registered!")){
						request.setAttribute("error", validateRegistration);
						RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
						rd.forward(request, response);
					}
					else if(validateRegistration.equals("User-Id is already registered!")){
						request.setAttribute("error", validateRegistration);
						RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
						rd.forward(request, response);
					}
					else{
						regdao.register(reg);
						request.setAttribute("first_name", firstName);
						request.setAttribute("last_name", lastName);
						RequestDispatcher rd=request.getRequestDispatcher("registered.jsp");
						rd.forward(request, response);
					}
				}	
			}
		}
		if(flag.equals("logout")){
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
	}
}
