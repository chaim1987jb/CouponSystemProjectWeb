package webServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.SystemGoingDownException;
import facade.AdminFacade;
import facade.ClientType;
import facade.CompanyFacade;
import facade.CouponSystem;
import facade.CustomerFacade;

/**
 * Servlet implementation class LoginServlet
 * The login servlet for the entering to the system.
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * This is the method that responsible to manage all the login logic.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		// TODO Auto-generated method stub
				String theType = request.getParameter("type");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
//				response.getWriter().println("nnoowww");

				//response.sendRedirect("http://localhost:8080/FinalCouponsSystem/adminSPA.html");

				ClientType theClientType =  ClientType.ADMINISTRATOR;
				switch (theType)
				{
				case "admin":
					AdminFacade admin = null;
					theClientType = ClientType.ADMINISTRATOR;
					try {
						admin	=  (AdminFacade) CouponSystem.getInstance().login(username, password, theClientType);
					} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e1) {
						
						e1.printStackTrace();
					}
					
					if(admin != null){
					
						HttpSession sess = request.getSession();
						sess.setAttribute("admin", admin);
						response.sendRedirect("http://localhost:8080/CouponSystemProjectWeb/AdminSPA.html");
						
					}
					else{
						
						response.sendRedirect("http://localhost:8080/CouponSystemProjectWeb/LoginFaild.html");

					}
					break;
			
				case "company":
					
					CompanyFacade company = null;
					
					try {
						company = (CompanyFacade) CouponSystem.getInstance().login(username, password, ClientType.COMPANY);
					} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(company !=null){
						
						HttpSession sess = request.getSession();
						sess.setAttribute("name",username );
						sess.setAttribute("company", company);
						response.sendRedirect("http://localhost:8080/CouponSystemProjectWeb/CompanySPA.html");
					}
					else{

						response.sendRedirect("http://localhost:8080/CouponSystemProjectWeb/LoginFaild.html");

					}
			
					break;
				case "customer":
				
					CustomerFacade customer = null;
					
					try {
						customer = (CustomerFacade) CouponSystem.getInstance().login(username, password, ClientType.CUSTOMER);
					} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(customer != null){
						
						HttpSession sess = request.getSession();
						sess.setAttribute("name",username );
						sess.setAttribute("customer", customer);
						response.sendRedirect("http://localhost:8080/CouponSystemProjectWeb/CustomerSPA.html");
					}
					else{

						response.sendRedirect("http://localhost:8080/CouponSystemProjectWeb/LoginFaild.html");

					}
			
					break;
		}
	}

}


