package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.entities.CreditCard;

public class CreditCardDao extends SimpleJdbcDaoSupport {

	private static final CreditCardRowMapper ccMapper = new CreditCardRowMapper();
	private AddressDao addressDao;

	public List<CreditCard> getCreditCardsByUserId(Integer userId) {
		List<CreditCard> ccList = null;
		final String sql = "SELECT * FROM CREDITCARD cc JOIN ADDRESS addr on cc.address_id = addr.address_id WHERE USER_ID=:user_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("user_id", userId);
		try {
			ccList = getSimpleJdbcTemplate().query(sql, ccMapper, paramSource);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ccList;
	}

	public CreditCard insertCreditCard(CreditCard cc) {
		final String sql = "INSERT INTO CREDITCARD (NAME, CC_NUMBER, PHONE_NUMBER, ADDRESS_ID, EXP_DATE, USER_ID, CC_ID)" + 
							" VALUES (:name, :cc_number, :phone_number, :address_id, :exp_date, :user_id, :cc_id)";
		//Create new address specifically for CC
		Address address = cc.getAddress();
		address = addressDao.insertAddress(address);
		cc.setAddress(address);
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		cc.setCcId(getNextCCID());
		paramSource.addValue("name", cc.getName());
		paramSource.addValue("cc_number", cc.getCcNumber());
		paramSource.addValue("phone_number", cc.getPhoneNumber());
		paramSource.addValue("address_id", address.getAddressId());
		paramSource.addValue("exp_date", cc.getExpirationDate());
		paramSource.addValue("user_id", cc.getUserId());
		paramSource.addValue("cc_id", cc.getCcId());
		try {
			getSimpleJdbcTemplate().update(sql, paramSource);
			return cc;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CreditCard updateCreditCard(CreditCard cc) {
		final String sql = "UPDATE CREDITCARD SET NAME=:name, CC_NUMBER=:cc_number, PHONE_NUMBER=:phone_number, ADDRESS_ID=:address_id," +
							" EXP_DATE=:exp_date, USER_ID=:user_id, CC_ID=:cc_id WHERE CC_ID=:cc_id";
		Address address = cc.getAddress();
		if(address.getAddressId() == null) {
			address = addressDao.insertAddress(address);
			cc.setAddress(address);
		}
		else {
			address = addressDao.updateAddress(address);
			cc.setAddress(address);
		}
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", cc.getName());
		paramSource.addValue("cc_number", cc.getCcNumber());
		paramSource.addValue("phone_number", cc.getPhoneNumber());
		paramSource.addValue("address_id", address.getAddressId());
		paramSource.addValue("exp_date", cc.getExpirationDate());
		paramSource.addValue("user_id", cc.getUserId());
		paramSource.addValue("cc_id", cc.getCcId());
		try {
			getSimpleJdbcTemplate().update(sql, paramSource);
			return cc;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteCreditCard(Integer ccId) {
		final String sql = "DELETE FROM CREDITCARD WHERE CC_ID=:cc_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("cc_id", ccId);
		try {
			getSimpleJdbcTemplate().update(sql, paramSource);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Integer getNextCCID() {
		final String sql = "SELECT CC_ID_SEQ.NEXTVAL FROM DUAL";
		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}

	private static final class CreditCardRowMapper implements RowMapper<CreditCard> {

		@Override
		public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
			CreditCard cc = new CreditCard();
			// Will do a join on Address ID
			Address address = new Address();
			address.setAddressId(rs.getInt("ADDRESS_ID"));
			address.setStreetAddr1(rs.getString("STREET1"));
			address.setStreetAddr2(rs.getString("STREET2"));
			address.setCity(rs.getString("CITY"));
			address.setState(rs.getString("STATE"));
			address.setZipCode(rs.getInt("ZIP_CODE"));
			cc.setAddress(address);
			cc.setCcNumber(rs.getString("CC_NUMBER"));
			cc.setExpirationDate(rs.getDate("EXP_DATE"));
			cc.setName(rs.getString("NAME"));
			cc.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			cc.setUserId(rs.getInt("USER_ID"));
			cc.setCcId(rs.getInt("CC_ID"));
			return cc;
		}
		
	}
}
