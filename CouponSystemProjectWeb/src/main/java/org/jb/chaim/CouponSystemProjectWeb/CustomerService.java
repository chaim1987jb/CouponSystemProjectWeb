package org.jb.chaim.CouponSystemProjectWeb;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import basic_classes.*;
import exceptions.CouponNotFoundException;
import exceptions.SystemGoingDownException;
import facade.*;

/**
 * This class is a web service that is responsible for all the functionality of the customer that
 *  login 
 * @author CHAIM
 *
 */
@Path("/customer")
public class CustomerService {
	
	
	@Context HttpServletRequest req;
	
	/**
	 * This method role is to provied the name of the customer to the client usage.
	 * @return Response
	 * @throws IOException
	 */
	@GET
	@Path("/name")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getName(){
		
		HttpSession sess =  req.getSession();
		String name = (String) sess.getAttribute("name");
		System.out.println(name);
		
		return  javax.ws.rs.core.Response.ok(name).status(200).build();
		
	}
	
	/**
	 * This mthod role is to return all the coupons in the system to the client.
	 * @return Response
	 */ 
	@GET
	@Path("/coupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allCoupons(){
		
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		Collection<Coupon>allCoupons = new ArrayList<Coupon>();
		

		try {
			allCoupons = customer.getPurchasedCoupons();
		
		} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
			return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
		}
		
		return javax.ws.rs.core.Response.ok(allCoupons).status(200).build();
	
	}

	/**
	 * This method allows you to buy a coupon by receiving a name and comparing it to 
	 * the coupons collection and send the specific coupon to the data base to the purchase 
	 * process.
	 * @param name
	 * @return Response
	 */
	@PUT
	@Path("/purchaseCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response purchaseCoupon(String name){
		
		Coupon coupon = null;
	
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		if(customer==null)
		{
			return javax.ws.rs.core.Response.ok("Please go back to the login.").status(500).build();
		}
		else
		{
			try {
				ArrayList<Coupon> coupons =  (ArrayList<Coupon>) customer.getAllAvalibleCoupons();
				
				coupons.removeIf(c-> !c.getTitle().equals(name));
				coupon = coupons.get(0);
				
				customer.purchaseCoupon(coupon);
			} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException
					| CouponNotFoundException  e) {
				
				return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			}

	
    	return javax.ws.rs.core.Response.ok("Congratulations you have purchased the coupon : "+coupon.getTitle()+"!!!").status(200).build(); 
		}
	}

	
	/**
	 * This method role is to return the customer coupons filtered up to the price that recived from
	 * the client.
	 * @param price
	 * @return Response
	 */
	@GET
	@Path("/getPurchasedCouponsByPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response couponsByPrice (@PathParam("price")double price){
		
		Collection<Coupon> coupons = null;
		
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		
		try{
			coupons = customer.getAllPurchasedCouponsByPrice(price);
		
			coupons.removeIf(c -> c.getPrice() > price);
		} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
		
			return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(coupons).status(200).build(); 
	}

	/**
	 * This method role is to return the customer coupons filtered according the type that recived from
	 * the client.
	 * @param type
	 * @return Response
	 */
	@GET
	@Path("/getPurchasedCouponsByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response couponsByType (@PathParam("type")String type){
		
		ArrayList<Coupon>coupons = null;
		
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		
		try {
			coupons = (ArrayList<Coupon>) customer.getAllPurchasedCouponsByType(CouponType.valueOf(type));
			
			coupons.removeIf(c -> ! c.getType().equals(CouponType.valueOf(type)));
			
		
			
		} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
			
			return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(coupons).status(200).build();
	}

	
	/**
	 * This method role is to provide all the coupons in the system to the client
	 * @return Response
	 */
	@GET
	@Path("/allTheCouponsInTheSystem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allTheCouponsInTheSystem(){
		
		Collection<Coupon>coupons = null;
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		
		try {
			coupons = customer.getAllAvalibleCoupons();
				
		} catch (ClassNotFoundException | SQLException | InterruptedException | SystemGoingDownException e) {
			return javax.ws.rs.core.Response.ok(e.getMessage()).status(500).build();
			
		}
		return  javax.ws.rs.core.Response.ok(coupons).status(200).build();
		
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
		
		HttpSession sess =  req.getSession();
		sess.setAttribute("customer", null);
		
		System.out.println("im out...");
		
	}


    
}
