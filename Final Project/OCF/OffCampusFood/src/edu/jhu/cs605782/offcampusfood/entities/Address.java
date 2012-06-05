package edu.jhu.cs605782.offcampusfood.entities;

public class Address {

	protected Integer addressId;
	protected String streetAddr1;
	protected String streetAddr2;
	protected String city;
	protected String state;
	protected Integer zipCode;

	public Address(){
		addressId = null;
		streetAddr1 = "";
		streetAddr2 = "";
		city="";
		state="";
		zipCode=null;
	}

	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getStreetAddr1() {
		return streetAddr1;
	}
	public void setStreetAddr1(String streetAddr1) {
		this.streetAddr1 = streetAddr1;
	}
	public String getStreetAddr2() {
		return streetAddr2;
	}
	public void setStreetAddr2(String streetAddr2) {
		this.streetAddr2 = streetAddr2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((streetAddr1 == null) ? 0 : streetAddr1.hashCode());
		result = prime * result
				+ ((streetAddr2 == null) ? 0 : streetAddr2.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddr1 == null) {
			if (other.streetAddr1 != null)
				return false;
		} else if (!streetAddr1.equals(other.streetAddr1))
			return false;
		if (streetAddr2 == null) {
			if (other.streetAddr2 != null)
				return false;
		} else if (!streetAddr2.equals(other.streetAddr2))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
}
