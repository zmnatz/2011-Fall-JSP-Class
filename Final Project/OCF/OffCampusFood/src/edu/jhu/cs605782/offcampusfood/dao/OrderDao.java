package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import edu.jhu.cs605782.offcampusfood.entities.Item;
import edu.jhu.cs605782.offcampusfood.entities.Order;

public class OrderDao extends SimpleJdbcDaoSupport {

	private static final OrderMapper mapper = new OrderMapper();
	private ItemDao itemDao = null;
	private UserDao userDao = null;

	private Integer getNextOrderId() {
		final String sql = "SELECT ORDER_SEQ.NEXTVAL FROM DUAL";
		return this.getSimpleJdbcTemplate().queryForInt(sql);
	}

	public List<Order> getOrdersByUserId(Integer id) {
		final String sql = "SELECT * FROM ORDERS WHERE USER_ID=:user_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("user_id", id);
		try {
			List<Order> orderList = getSimpleJdbcTemplate().query(sql, mapper, paramSource);
			for(Order order:orderList) {
				List<Item> items = itemDao.getItemsByOrderId(order.getOrderId());
				order.setItems(items);
			}
			return orderList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Order> getOrdersByRestaurantId(Integer id) {
		final String sql = "SELECT * FROM ORDERS WHERE RESTAURANT_ID=:restaurant_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("restaurant_id", id);
		try {
			List<Order> orderList = getSimpleJdbcTemplate().query(sql, mapper, paramSource);
			for(Order order:orderList) {
				List<Item> items = itemDao.getItemsByOrderId(order.getOrderId());
				order.setItems(items);
				order.setUser(userDao.getUserByUserId(order.getUserId()));
			}
			return orderList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Order insertOrder(Order o) {
		final String sql = "INSERT INTO ORDERS(ORDER_ID, RESTAURANT_ID, USER_ID, STATUS) " +
							"VALUES(:order_id, :restaurant_id, :user_id, :status)";
		o.setOrderId(getNextOrderId());
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("order_id", o.getOrderId());
		paramSource.addValue("restaurant_id", o.getRestaurantId());
		paramSource.addValue("user_id", o.getUserId());
		paramSource.addValue("status", o.getComplete());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			itemDao.createOrderItemXref(o.getItems(), o.getOrderId());
			return o;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Order updateOrder(Order o) {
		final String sql = "UPDATE ORDERS SET ORDER_ID=:order_id, RESTAURANT_ID=:restaurant_id, " +
							"USER_ID=:user_id, STATUS=:status WHERE ORDER_ID=:order_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("order_id", o.getOrderId());
		paramSource.addValue("restaurant_id", o.getRestaurantId());
		paramSource.addValue("user_id", o.getUserId());
		paramSource.addValue("status", o.getComplete());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			itemDao.updateOrderItemXref(o.getItems(), o.getOrderId());
			return o;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final class OrderMapper implements RowMapper<Order> {

		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setComplete(rs.getBoolean("STATUS"));
			order.setOrderDate(rs.getTimestamp("RCRD_CRTD_DT"));
			order.setOrderId(rs.getInt("ORDER_ID"));
			order.setRestaurantId(rs.getInt("RESTAURANT_ID"));
			order.setUserId(rs.getInt("USER_ID"));
			return order;
		}
		
	}

	public ItemDao getItemDao() {
		return itemDao;
	}
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<Order> getRecentOrders() {
		final String sql = "SELECT * FROM ORDERS where rownum <= 2 ORDER BY ORDER_ID DESC";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		try {
			List<Order> orderList = getSimpleJdbcTemplate().query(sql, mapper, paramSource);
			for(Order order:orderList) {
				List<Item> items = itemDao.getItemsByOrderId(order.getOrderId());
				order.setItems(items);
				order.setUser(userDao.getUserByUserId(order.getUserId()));
			}
			return orderList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Order>();
	}
}
