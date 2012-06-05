package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.entities.Item;
import edu.jhu.cs605782.offcampusfood.entities.Login;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;

public class RestaurantDao extends SimpleJdbcDaoSupport {

	private static final RestaurantMapper mapper = new RestaurantMapper();
	private AddressDao addressDao = null;
	private LoginDao loginDao = null;
	private ItemDao itemDao = null;
	private OrderDao orderDao = null;

	public Restaurant insertOrUpdate(Restaurant restaurant) {
		if(restaurant==null)
			return null;
		else if(restaurant.getRestaurantId()==null)
			return insertRestaurant(restaurant);
		else
			return updateRestaurant(restaurant);
	}
	
	public Restaurant insertRestaurant(Restaurant r) {
		final String sql = "INSERT INTO RESTAURANT(RESTAURANT_ID, NAME, OPEN_TIME, CLOSE_TIME, ADDRESS_ID, EMAIL, PHONE_NUMBER) " +
							"VALUES(:restaurant_id, :name, :open_time, :close_time, :address_id, :email, :phone_number)";
		r.setRestaurantId(getNextRestaurantId());
		//Address
		Address address = r.getAddress();
		address = addressDao.insertAddress(address);
		r.setAddress(address);
		//Login
		Login login = r.getLogin();
		login = loginDao.insertLogin(login);
		r.setLogin(login);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("restaurant_id", r.getRestaurantId());
		paramSource.addValue("name", r.getName());
		paramSource.addValue("open_time", r.getOpen());
		paramSource.addValue("close_time", r.getClose());
		paramSource.addValue("address_id", r.getAddress().getAddressId());
		paramSource.addValue("email", r.getLogin().getEmail());
		paramSource.addValue("phone_number", r.getPhoneNumber());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			if(r.getMenu() != null) {
				for(Item item:r.getMenu()) {
					itemDao.insertItem(item, r.getRestaurantId());
				}
			}
			return r;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Restaurant updateRestaurant(Restaurant r) {
		final String sql = "UPDATE RESTAURANT SET RESTAURANT_ID=:restaurant_id, NAME=:name, OPEN_TIME=:open_time, CLOSE_TIME=:close_time, " +
							"ADDRESS_ID=:address_id, EMAIL=:email, PHONE_NUMBER=:phone_number WHERE RESTAURANT_ID=:restaurant_id";
		//Address
		Address address = r.getAddress();
		address = addressDao.updateAddress(address);
		r.setAddress(address);
		//Login
		Login login = r.getLogin();
		login = loginDao.updateLogin(login);
		r.setLogin(login);
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("restaurant_id", r.getRestaurantId());
		paramSource.addValue("name", r.getName());
		paramSource.addValue("open_time", r.getOpen());
		paramSource.addValue("close_time", r.getClose());
		paramSource.addValue("address_id", r.getAddress().getAddressId());
		paramSource.addValue("email", r.getLogin().getEmail());
		paramSource.addValue("phone_number", r.getPhoneNumber());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			if(r.getMenu() != null) {
				for(Item item:r.getMenu()) {
					if(item.getItemId() == null) {
						itemDao.insertItem(item, r.getRestaurantId());
					}
					else {
						itemDao.updateItem(item);
					}
				}
			}
			return r;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Restaurant getRestaurantById(Integer id) {
		final String sql = "SELECT * FROM RESTAURANT rest JOIN ADDRESS addr on rest.address_id = addr.address_id " +
							"JOIN LOGIN log on rest.email = log.email WHERE RESTAURANT_ID=:restaurant_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("restaurant_id", id);
		try {
			Restaurant rest = (Restaurant)this.getSimpleJdbcTemplate().queryForObject(sql, mapper, paramSource);
			rest.setMenu(itemDao.getItemsByRestaurantId(rest.getRestaurantId()));
			rest.setOrders(orderDao.getOrdersByRestaurantId(rest.getRestaurantId()));
			return rest;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Restaurant getRestaurantByEmail(String email) {
		final String sql = "SELECT * FROM RESTAURANT rest JOIN ADDRESS addr on rest.address_id = addr.address_id " +
							"JOIN LOGIN log on rest.email = log.email WHERE EMAIL=:email";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", email);
		try {
			Restaurant rest = (Restaurant)this.getSimpleJdbcTemplate().queryForObject(sql, mapper, paramSource);
			rest.setMenu(itemDao.getItemsByRestaurantId(rest.getRestaurantId()));
			rest.setOrders(orderDao.getOrdersByRestaurantId(rest.getRestaurantId()));
			return rest;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Restaurant> getAllRestaurants() {
		final String sql = "SELECT * FROM RESTAURANT rest JOIN ADDRESS addr on rest.address_id = addr.address_id " +
							"JOIN LOGIN log on rest.email = log.email";
		try {
			List<Restaurant> list = this.getSimpleJdbcTemplate().query(sql, mapper);
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Integer getNextRestaurantId() {
		final String sql = "SELECT RESTAURANT_SEQ.NEXTVAL FROM DUAL";
		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	private static final class RestaurantMapper implements RowMapper<Restaurant> {

		@Override
		public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
			Restaurant restaurant = new Restaurant();
			//Address
			Address address = new Address();
			address.setAddressId(rs.getInt("ADDRESS_ID"));
			address.setStreetAddr1(rs.getString("STREET1"));
			address.setStreetAddr2(rs.getString("STREET2"));
			address.setCity(rs.getString("CITY"));
			address.setState(rs.getString("STATE"));
			address.setZipCode(rs.getInt("ZIP_CODE"));
			restaurant.setAddress(address);
			//Login
			Login login = new Login();
			login.setEmail(rs.getString("EMAIL"));
			login.setPassword(rs.getString("PASSWORD"));
			login.setLoginType(rs.getString("LOGIN_TYPE"));
			restaurant.setLogin(login);
			
			restaurant.setClose(rs.getTimestamp("CLOSE_TIME"));
			restaurant.setOpen(rs.getTimestamp("OPEN_TIME"));
			restaurant.setName(rs.getString("NAME"));
			restaurant.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			restaurant.setRestaurantId(rs.getInt("RESTAURANT_ID"));
			return restaurant;
		}
		
	}
}
