package org.jb.chaim.CouponSystemProjectWeb;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.Iterator;
import java.util.Locale;

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
import javax.xml.ws.RequestWrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.mysql.fabric.Response;
import com.sun.research.ws.wadl.Response;
import basic_classes.Company;
import basic_classes.Coupon;
import basic_classes.CouponType;
import exceptions.CompanyNotFoundException;
import exceptions.CouponNotFoundException;
import exceptions.DuplicateCompanyException;
import exceptions.DuplicateCouponException;
import exceptions.SystemGoingDownException;
import facade.AdminFacade;
import facade.CompanyFacade;

/**
 * This class is a web service that is responsible for all the functionality of the company that
 *  login 
 * @author CHAIM
 *
 */
@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyService {


	
	@Context HttpServletRequest request;
	@Context	
    private HttpServletResponse response;
	CompanyFacade facade = null;
	Gson gson = new Gson();

	/**
	 * This method role is to provied the name of the company to the client usage.
	 * @return Response
	 * @throws IOException
	 */
	@GET
	@Path("/name")
	@Produces(MediaType.TEXT_PLAIN)
	public javax.ws.rs.core.Response getName() throws IOException {
		 String name = (String) request.getSession().getAttribute("name");
		
	

		return javax.ws.rs.core.Response.ok(name).status(200).build();
	}
	
	/**
	 * This method role is to create a coupon in the data base by using the {@link FakeCoupon}
	 *  class to make sure all fields are accepted without error.
	 * @param coupon
	 * @return Response
	 * @throws IOException
	 */
	@POST
	@Path("/createCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public javax.ws.rs.core.Response createCoupon( FakeCoupon coupon ) throws IOException {
	
			CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
			
				
					try {
						
						
						facade.createCoupon(coupon.generateCoupon());
					} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException
							| DuplicateCouponException | CouponNotFoundException | ParseException e) {
						e.printStackTrace();
						return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
					
					}
			
		
	return javax.ws.rs.core.Response.ok("The coupon "+coupon.getTitle()+" successfully created !").status(200).build();
	}
	
	/**
	 * This method role is to update coupon in the data base by using the {@link FakeCoupon}
	 *  class to make sure all fields are accepted without error.
	 * @param coupon
	 * @return Response
	 * @throws IOException
	 */
	@PUT
	@Path("/updateCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public javax.ws.rs.core.Response updateCoupon(FakeCoupon coupon) throws IOException {

		
			CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
			
				try {
					facade.updateCoupon(coupon.generateCoupon());
				} catch (ClassNotFoundException | SQLException | InterruptedException | CouponNotFoundException
						| SystemGoingDownException | ParseException e) {
					e.printStackTrace();
					return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
					
				}
			
				System.out.println("+!!! "+coupon.getId()+"  +++!!!");
		return javax.ws.rs.core.Response.ok("The coupon "+coupon.getTitle()+" successfully updated !").status(200).build();
	}
	
	/**
	 * This method role is to remove coupon from the data base by reciving an id and 
	 * match it to the specific coupon from the company coupons collection and than send it 
	 * to remove process.
	 * @param id
	 * @return Response
	 * @throws IOException
	 */
	@DELETE
	@Path("/removeCoupon/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public javax.ws.rs.core.Response removeCoupon(@PathParam("id")long id) throws IOException {

		CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
		ArrayList<Coupon>allCoupons = null;
		Coupon coupon = null;
		try {
			allCoupons = (ArrayList<Coupon>) facade.getAllCoupon();
			allCoupons.removeIf(c -> c.getId() != id);
			coupon = allCoupons.get(0);
			facade.removeCoupon(coupon); 
		} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException | CouponNotFoundException  e) {
			return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
		}

		return javax.ws.rs.core.Response.ok("The coupon "+coupon.getTitle()+" successfully removed !").status(200).build();
	}
	
	/**
	 * This method role is to return a coupon by match it's field
	 * @param id
	 * @return Response
	 * @throws IOException
	 */
	@GET
	@Path("/couponById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCoupon(@PathParam("id") long id) throws IOException {

		CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
	
		Collection<Coupon>allCoupons = null;
		try {
			allCoupons = facade.getAllCoupon();
			allCoupons.removeIf(c -> c.getId() != id);
			
			
		} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
		
			e.printStackTrace();
		}
		
		return javax.ws.rs.core.Response.ok(allCoupons).status(200).build();
	}
	
	/**
	 * This method role is to return all the coupons that the company owned
	 * @return Response
	 * @throws IOException
	 */
	@GET
	@Path("/getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCoupons() throws IOException {

		CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
		Collection<Coupon> coupon = null;
	
				try {
					System.out.println(facade);
					coupon =  facade.getAllCoupon();
					
				} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
					
					return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
				}
		
		
		return javax.ws.rs.core.Response.ok(coupon).status(200).build();
				
	}
	
	/**
	 * This method role is to return all the company coupons that match the 
	 * type that reciving from the client.
	 * @param type
	 * @return Response
	 */
	@GET
	@Path("/getAllCouponByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponByType(@PathParam("type") String type)  {

		CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
		System.out.println(CouponType.valueOf(type));
		Collection<Coupon> coupon = null;
			try {
				
				coupon = facade.getAllCoupon();
				coupon.removeIf(c -> !c.getType().equals(CouponType.valueOf(type)));
			} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			}
		
			return javax.ws.rs.core.Response.ok(coupon).status(200).build();
	}
	
	/**
	 * This method role is to return all the coupons that has a price up to the 
	 * price that recived from the client.
	 * @param price
	 * @return Response
	 * @throws IOException
	 */
	@GET
	@Path("/getAllCouponByPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponByPrice(@PathParam("price")double price) throws IOException {

		CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
		       
		Collection<Coupon> coupon = null;
			try {
				coupon = facade.getCouponByPrice(price);
			} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			}

			return javax.ws.rs.core.Response.ok(coupon).status(200).build();
	}
	
	/**
	 * This method role is to return a coupons up to the end date that recived from 
	 * the client using the {@link WebDate} class to convert the date so it could be 
	 * comparable.
	 * @param endDate
	 * @return Response
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/couponByEndDate")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponByEndDate(WebDate endDate) throws IOException, ParseException {
		
		java.sql.Date ed =  new Date(endDate.convertDate().getTime());
		CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
		Collection<Coupon> coupon = null;
			try {
				
				coupon = facade.getAllCoupon();
				coupon.removeIf(c -> c.getEndDate().after(ed));
			} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			}
			return javax.ws.rs.core.Response.ok(coupon).status(200).build();
			
	}
	

	/**
	 * This method role is to provide the company details to the client it using the 
	 * session to get the company name than returning the specific company by metch the name 
	 * to the company collection
	 * @return Response
	 */
	@GET
	@Path("/getCompanyDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getDetails(){
		
		Company company = null;
		
		CompanyFacade facade = (CompanyFacade) request.getSession().getAttribute("company");
		String name = (String) request.getSession().getAttribute("name");
		try {
			Collection<Company> companies = facade.getAllCompanies();
			companies.removeIf( c -> ! c.getCompName().equals(name));
			company = companies.iterator().next();
		} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
			
			return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
		}
		
		
		return javax.ws.rs.core.Response.ok(company).status(200).build();
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
		sess.setAttribute("company", null);
		
		System.out.println("im out...");
		
	}




}
