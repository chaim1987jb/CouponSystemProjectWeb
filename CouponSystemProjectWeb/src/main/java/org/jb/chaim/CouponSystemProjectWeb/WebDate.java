package org.jb.chaim.CouponSystemProjectWeb;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * This class role is to convert a date that received from the client to 
 * a java date.
 * @author CHAIM
 *
 */
@XmlRootElement
public class WebDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String stringDate;
	private Date javaDate;
	
	/**
	 * This is a no argument constructors.
	 */
	public WebDate(){
		
	}
	
	/**
	 * This constructor with fields. 
	 * @param stringDate
	 */
	public WebDate(String stringDate) {
		super();
		this.stringDate = stringDate;
	}
	/**
	 * This is the getter for the string date field.
	 * @return stringDate
	 */
	public String getStringDate() {
		return stringDate;
	}
	/**
	 * This is the setter for the sting date field to set a new value .
	 * @param stringDate
	 */
	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}
	/**
	 * This is the getter fore the java date field.
	 * @return javaDate
	 */
	public Date getJavaDate() {
		return javaDate;
	}
	/**
	 * This is the setter for the java date field to set a new value.
	 * @param javaDate
	 */
	public void setJavaDate(Date javaDate) {
		this.javaDate = javaDate;
	}
	/**
	 * This is the method that converting the date from string to java date.
	 * @return
	 */
	public Date convertDate(){
		
		final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault());
		
		try {
			 javaDate = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return javaDate;
	}

}
