package edu.jhu.cs605782.offcampusfood.entities;

import java.util.Date;
import java.util.List;

public class Restaurant extends BasicInfo {

	protected Integer restaurantId;
	protected Date open;
	protected Date close;
	protected List<Item> menu;
	protected List<Order> orders;

	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Date getOpen() {
		return open;
	}
	public void setOpen(Date open) {
		this.open = open;
	}
	public Date getClose() {
		return close;
	}
	public void setClose(Date close) {
		this.close = close;
	}
	public List<Item> getMenu() {
		return menu;
	}
	public void setMenu(List<Item> menu) {
		this.menu = menu;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((close == null) ? 0 : close.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result
				+ ((restaurantId == null) ? 0 : restaurantId.hashCode());
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
		Restaurant other = (Restaurant) obj;
		if (close == null) {
			if (other.close != null)
				return false;
		} else if (!close.equals(other.close))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		if (open == null) {
			if (other.open != null)
				return false;
		} else if (!open.equals(other.open))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		return true;
	}
}
