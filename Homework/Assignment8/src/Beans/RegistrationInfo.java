package Beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class RegistrationInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String status;
	private Set<String> course = new TreeSet<String>();
	private Boolean hotel;
	private Boolean parking;
	
	public static final Map<String,Short> STATUS_FEE = getStatusFees();
	private static Map<String,Short> getStatusFees(){
		Map<String,Short> s = new HashMap<String,Short>();
		s.put("JHU Employee",Short.valueOf((short)850));
		s.put("JHU Student",Short.valueOf((short)1000));
		s.put("Speaker",Short.valueOf((short)0));
		s.put("Other",Short.valueOf((short)1350));
		return Collections.unmodifiableMap(s);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the courses
	 */
	public Set<String> getCourse() {
		return course;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourse(Set<String> courses) {
		this.course = courses;
	}
	/**
	 * @return the hotel
	 */
	public Boolean isHotel() {
		return hotel;
	}
	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Boolean hotel) {
		this.hotel = hotel;
	}
	/**
	 * @return the parking
	 */
	public Boolean isParking() {
		return parking;
	}
	/**
	 * @param parking the parking to set
	 */
	public void setParking(Boolean parking) {
		this.parking = parking;
	}
	
	
	
}
