package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.entities.Login;
import edu.jhu.cs605782.offcampusfood.entities.Order;
import edu.jhu.cs605782.offcampusfood.entities.User;

public class UserDao extends SimpleJdbcDaoSupport {

	private static final UserMapper mapper = new UserMapper();
	private AddressDao addressDao = null;
	private LoginDao loginDao = null;
	private OrderDao orderDao = null;
	private CreditCardDao creditCardDao = null;

	/**
	 * @return the creditCardDao
	 */
	public CreditCardDao getCreditCardDao() {
		return creditCardDao;
	}

	/**
	 * @param creditCardDao the creditCardDao to set
	 */
	public void setCreditCardDao(CreditCardDao creditCardDao) {
		this.creditCardDao = creditCardDao;
	}

	private Integer getNextUserId() {
		final String sql = "SELECT USER_SEQ.NEXTVAL FROM DUAL";
		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	public User insertOrUpdate(User user){
		if(user==null)
			return null;
		else if(user.getUserId()==null)
			return insertUser(user);
		else
			return updateUser(user);
	}
	
	public User insertUser(User user) {
		final String sql = "INSERT INTO USERS(USER_ID, NAME, PHONE_NUMBER, ADDRESS_ID, EMAIL) " + 
							"VALUES (:user_id, :name, :phone_number, :address_id, :email)";
		user.setUserId(getNextUserId());
		//Address
		Address address = user.getAddress();
		address = addressDao.insertAddress(address);
		user.setAddress(address);
		//Login
		Login login = user.getLogin();
		login = loginDao.insertLogin(login);
		user.setLogin(login);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("user_id", user.getUserId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("phone_number", user.getPhoneNumber());
		paramSource.addValue("address_id", user.getAddress().getAddressId());
		paramSource.addValue("email", user.getLogin().getEmail());
		try {
			getSimpleJdbcTemplate().update(sql, paramSource);
			return user;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User updateUser(User user) {
		final String sql = "UPDATE USERS SET USER_ID=:user_id, NAME=:name, PHONE_NUMBER=:phone_number, " + 
						"ADDRESS_ID=:address_id, EMAIL=:email WHERE USER_ID=:user_id";
		//Address
		Address address = user.getAddress();
		address = addressDao.updateAddress(address);
		user.setAddress(address);
		//Login
		Login login = user.getLogin();
		login = loginDao.updateLogin(login);
		user.setLogin(login);
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("user_id", user.getUserId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("phone_number", user.getPhoneNumber());
		paramSource.addValue("address_id", user.getAddress().getAddressId());
		paramSource.addValue("email", user.getLogin().getEmail());
		try {
			getSimpleJdbcTemplate().update(sql, paramSource);
			return user;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByUserId(Integer id) {
		final String sql = "SELECT * FROM USERS usr JOIN ADDRESS addr on usr.address_id = addr.address_id " + 
							"JOIN LOGIN log on usr.email = log.email WHERE USER_ID=:user_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("user_id", id);
		try {
			User user = (User)getSimpleJdbcTemplate().queryForObject(sql, mapper, paramSource);
			List<Order> orderList = orderDao.getOrdersByUserId(user.getUserId());
			user.setOrders(orderList);
			return user;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByEmail(String email) {
		final String sql = "SELECT * FROM USERS usr JOIN ADDRESS addr on usr.address_id = addr.address_id " + 
							"JOIN LOGIN log on usr.email = log.email WHERE EMAIL=:email";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", email);
		try {
			User user = (User)getSimpleJdbcTemplate().queryForObject(sql, mapper, paramSource);
			List<Order> orderList = orderDao.getOrdersByUserId(user.getUserId());
			user.setOrders(orderList);
			user.setCreditCards(creditCardDao.getCreditCardsByUserId(user.getUserId()));
			return user;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	private static final class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			//Address
			Address address = new Address();
			address.setAddressId(rs.getInt("ADDRESS_ID"));
			address.setStreetAddr1(rs.getString("STREET1"));
			address.setStreetAddr2(rs.getString("STREET2"));
			address.setCity(rs.getString("CITY"));
			address.setState(rs.getString("STATE"));
			address.setZipCode(rs.getInt("ZIP_CODE"));
			user.setAddress(address);
			//Login
			Login login = new Login();
			login.setEmail(rs.getString("EMAIL"));
			login.setPassword(rs.getString("PASSWORD"));
			login.setLoginType(rs.getString("LOGIN_TYPE"));
			user.setLogin(login);

			user.setName(rs.getString("NAME"));
			user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			user.setUserId(rs.getInt("USER_ID"));
			return user;
		}
		
	}
}
