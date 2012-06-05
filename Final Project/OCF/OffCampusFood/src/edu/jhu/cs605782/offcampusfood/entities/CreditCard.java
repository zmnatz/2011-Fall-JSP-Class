package edu.jhu.cs605782.offcampusfood.entities;

import java.util.Date;

public class CreditCard extends BasicInfo {

	protected String ccNumber;
	protected Address address;
	protected Date expirationDate;
	protected Integer userId;
	protected Integer ccId;

	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCcId() {
		return ccId;
	}
	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((ccId == null) ? 0 : ccId.hashCode());
		result = prime * result
				+ ((ccNumber == null) ? 0 : ccNumber.hashCode());
		result = prime * result
				+ ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (ccId == null) {
			if (other.ccId != null)
				return false;
		} else if (!ccId.equals(other.ccId))
			return false;
		if (ccNumber == null) {
			if (other.ccNumber != null)
				return false;
		} else if (!ccNumber.equals(other.ccNumber))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
