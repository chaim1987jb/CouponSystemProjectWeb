package org.jb.chaim.CouponSystemProjectWeb;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;

import basic_classes.Coupon;
import basic_classes.CouponType;

/**
 * This class role is to convert coupon that received from the client side to a coupon object
 *  and retutn it as a coupon object.
 * @author CHAIM
 *
 */
@XmlRootElement
public class FakeCoupon implements Serializable{
	
	private long id;
	private String title;
	private String startDate;
	private String endDate;
	private String amount;
	private String type;
	private String price;
	private String image;
	private String message;
	
	
	/**
	 * This is a no argument constructor
	 */
	public FakeCoupon(){
		
	}
	
	
	/**
	 * This is a constructor with all the fields
	 * @param id
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param price
	 * @param image
	 * @param message
	 */
	public FakeCoupon(long id, String title, String startDate, String endDate, String amount, String type, String price,
			String image, String message) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.price = price;
		this.image = image;
		this.message = message;
	}
	/**
	 * This is a getter for the id field
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * This is the id setter to set the id field with a new value
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * This is the title getter 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * This is the title setter to set a new value to the title field.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * This is the start date getter 
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * This is the start date setter usage to set a new value to the start date field.
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * This is the end date getter
	 * @return endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * This is the end date setter that set the end date field with a new value.
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * This is the amount getter
	 * @return
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * This is the amount setter to set a new value to amount field.
	 * @param amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * This is the type getter
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * This is the type setter to set a new value to the type field.
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * This is the price getter
	 * @return price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * This is the price setter to set a new value to the price field.
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * This is the image getter.
	 * @return image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * This is the image setter to set a new value to the image field.
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * This is the message getter
	 * @return message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * This is the message setter to set a new value to the message field.
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * This method role is the converting the coupon that received from the client and 
	 * return it as a generated coupon object.
	 * @return Coupon
	 * @throws ParseException
	 */
	public Coupon generateCoupon() throws ParseException{
		
		Coupon coupon = new Coupon();
		coupon.setId(id);
		coupon.setTitle(title);
		
		System.out.println(startDate);
		final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US);
		java.util.Date utilDate = dateFormat.parse(startDate);
		 Date sd =  new Date((utilDate).getTime());
		 
		 coupon.setStartDate(sd);
		 java.util.Date utilEndDate = dateFormat.parse(endDate);
		 java.sql.Date ed =  new Date(utilEndDate.getTime());
			 
			coupon.setEndDate(ed);
			coupon.setAmount(Integer.parseInt(amount));
			coupon.setType(CouponType.valueOf(type));
			coupon.setPrice(Double.valueOf(price));
			coupon.setImage(image);
			coupon.setMessage(message);
		
		return coupon;
	}
	
	
	


}
