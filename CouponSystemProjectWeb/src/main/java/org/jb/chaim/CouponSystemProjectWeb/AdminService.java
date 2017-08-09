package org.jb.chaim.CouponSystemProjectWeb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import com.google.gson.GsonBuilder;

import exceptions.CompanyNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.DuplicateCompanyException;
import exceptions.DuplicateCustomerException;
import exceptions.SystemGoingDownException;
import facade.*;
import basic_classes.Company;
import basic_classes.Customer;

//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)

/**
 * The role of this class is to manage the entire system
 * @author CHAIM
 *
 */

@Path("/admin")
public class AdminService {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	AdminFacade facade = null;
	Gson gson = new Gson();

	/**
	 * The role of this method is to receive session and return an admin facade 
	 * object.
	 * @return AdminFacade
	 */
	private AdminFacade getFacade()
	{
		AdminFacade facade = (AdminFacade) request.getSession().getAttribute("admin");
		return facade;
	}



	/**
	 * The role fo this method is to recive a Company object from the client side and 
	 * send it to the data base to create process
	 * @param company
	 * @return Response
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@POST
	@Path("/createCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public javax.ws.rs.core.Response createCompany(Company company) throws ClassNotFoundException, SQLException  {

		AdminFacade facade = this.getFacade();
		
		try {
				facade.createCompany(company);
			} catch (ClassNotFoundException | DuplicateCompanyException | SQLException | InterruptedException
					| CompanyNotFoundException | SystemGoingDownException e) {
				
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			}
		

			return javax.ws.rs.core.Response.ok("The Company "+company.getCompName()+" successfuly created .").status(200).build();
	}

	/**
	 * This method role is to update company in the data base by reciving a compay instance 
	 * and send it to update process in the data base.
	 * @param company
	 * @return Response
	 * @throws IOException
	 */
	@PUT
	@Path("/updateCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateCompany(Company company) throws IOException {
		
			AdminFacade facade = this.getFacade();
			
				try {
					facade.updateCompany(company);
				} catch (ClassNotFoundException | SQLException | InterruptedException | CompanyNotFoundException
						| SystemGoingDownException e) {
					return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
				}
		

				 return javax.ws.rs.core.Response.ok("The company "+company.getCompName()+" successfuly updated ").status(200).build();
	
	}

	/**
	 * This method role is to remove company from the data base by reciving a company instance
	 * and send it to the data base to remove process.
	 * @param company
	 * @return Response
	 * @throws IOException
	 */
	@POST
	@Path("/removeCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeCompany(Company company) throws IOException {

		AdminFacade facade = this.getFacade();
		
				try {
					facade.removeCompany(company);
				} catch (ClassNotFoundException | SQLException | InterruptedException | CompanyNotFoundException
						| SystemGoingDownException e) {
					return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
				}
	
                    
				 return javax.ws.rs.core.Response.ok("The company "+company.getCompName()+" successfuly removed.").status(200).build();
	}

	/**
	 * This method role is to returne a company by match it's id field in the data base
	 * @param compID
	 * @return Response
	 */
	@GET
	@Path("/companyById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompany(@PathParam("id") long compID) {
		System.out.println("im in web service " + compID);
		
		AdminFacade facade = this.getFacade();

		Company company = null;


			 try {
				company = facade.getCompany(compID);
			} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException | CompanyNotFoundException e) {
				e.printStackTrace();
				return javax.ws.rs.core.Response.ok(e).status(500).build();
			}

			 return javax.ws.rs.core.Response.ok(company).status(200).build();
    
		
	}

	/**
	 * This method role is to return all the companies that exsist in the system
	 * @return Response
	 * @throws IOException
	 */
	@GET
	@Path("/getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	
	public  Response getAllCompanies() throws IOException {



		AdminFacade facade = this.getFacade();
		
		Collection<Company> companies =null;     
				
			try {
				 companies = facade.getAllCompanies();
			} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			}

		
			return javax.ws.rs.core.Response.ok(companies).status(200).build();
			
	}

	/**
	 * The role fo this method is to recive a Customer object from the client side and 
	 * send it to the data base to create process
	 * @param customer
	 * @return Response
	 */
	@POST
	@Path("/createCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public javax.ws.rs.core.Response createCustomer(Customer customer)  {


		AdminFacade facade = this.getFacade();
		try {
			facade.createCustomer(customer);
		} catch (ClassNotFoundException | DuplicateCustomerException | SQLException | InterruptedException
				| CustomerNotFoundException | SystemGoingDownException e) {
			
			return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
		}
	

		return javax.ws.rs.core.Response.ok("The customer "+customer.getCustName()+" successfuly created .").status(200).build();
}

	/**
	 * This method role is to update customer in the data base by reciving a customer instance 
	 * and send it to update process in the data base.
	 * @param customer
	 * @return Response
	 * @throws IOException
	 */
	@PUT
	@Path("/updateCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateCustomer(Customer customer) throws IOException {

		AdminFacade facade = this.getFacade();
		
				try {
					facade.updateCustomer(customer);
				} catch (ClassNotFoundException | SQLException | InterruptedException | CustomerNotFoundException
						| SystemGoingDownException e) {
					return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
				}
		
				return javax.ws.rs.core.Response.ok("The customer "+customer.getCustName()+" successfuly updated .").status(200).build();
				}
/**
 * This method role is to remove customer from the data base by reciving a customer instance
 * and send it to the data base to remove process.
 * @param customer
 * @return Response
 * @throws IOException
 */
	@POST
	@Path("/removeCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeCustomer(Customer customer) throws IOException {


		AdminFacade facade = this.getFacade();
		
			try {
				facade.removeCustomer(customer);
			} catch (ClassNotFoundException | SQLException | InterruptedException | CustomerNotFoundException
					| SystemGoingDownException e) {
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			}



			return javax.ws.rs.core.Response.ok("The customer "+customer.getCustName()+" successfuly removed .").status(200).build();
	}

	/**
	 * This method role is to returne a customer by match it's id field in the data base
	 * @param id
	 * @return
	 */
	@GET
	@Path("/customerById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("id") long id) {

		AdminFacade facade = this.getFacade();
		Customer customer = null;
		
			try {
				customer = facade.getCustomer(id);
			} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException | CustomerNotFoundException e) {
				return javax.ws.rs.core.Response.ok(e).status(500).build();
			}
		
			return javax.ws.rs.core.Response.ok(customer).status(200).build();
	}

	/**
	 * This method role is to return all the customers that exsist in the system
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCustomers() throws IOException {


		Collection<Customer> customers=null;
		AdminFacade facade = this.getFacade();
		
		    try {
				customers = facade.getAllCustomer();
			    }
			 catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e)
			 {
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			 }

		       return javax.ws.rs.core.Response.ok(customers).status(200).build();
	}
	
	/**
	 * This method role is to Prevent unsecured login
	 * by setting the session to null
	 */
	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void signOut(){
		
		HttpSession sess =  request.getSession();
		sess.setAttribute("admin", null);
		
		System.out.println("im out...");
		
	}


	

}

