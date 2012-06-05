package edu.jhu.cs605782.offcampusfood.entities;

import java.util.Date;
import java.util.List;

public class Order {

	protected Integer orderId;
	protected Integer restaurantId;
	protected Integer userId;
	protected Boolean complete;
	protected Date orderDate;
	protected List<Item> items;
	protected User user;

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((complete == null) ? 0 : complete.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result
				+ ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Order other = (Order) obj;
		if (complete == null) {
			if (other.complete != null)
				return false;
		} else if (!complete.equals(other.complete))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
