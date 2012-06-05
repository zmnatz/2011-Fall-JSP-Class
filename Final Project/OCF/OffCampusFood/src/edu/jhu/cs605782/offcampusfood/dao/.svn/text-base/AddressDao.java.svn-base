package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import edu.jhu.cs605782.offcampusfood.entities.Address;

public class AddressDao extends SimpleJdbcDaoSupport {

	private static final AddressRowMapper addressMapper = new AddressRowMapper();

	public Address getAddressById(Integer id) {
		final String sql = "SELECT * FROM ADDRESS WHERE ADDRESS_ID=:address_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("address_id", id);
		return getSimpleJdbcTemplate().queryForObject(sql, addressMapper, paramSource);
	}

	private Integer getNextAddressId() {
		final String sql = "SELECT ADDRESS_SEQ.NEXTVAL FROM DUAL";
		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	public Address insertAddress(Address address) {
		final String sql = "INSERT INTO ADDRESS (ADDRESS_ID, STREET1, STREET2, CITY, STATE, ZIP_CODE)" + 
							" VALUES (:address_id, :street1, :street2, :city, :state, :zip)";
		address.setAddressId(this.getNextAddressId());
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("address_id", address.getAddressId());
		paramSource.addValue("street1", address.getStreetAddr1());
		paramSource.addValue("street2", address.getStreetAddr2());
		paramSource.addValue("city", address.getCity());
		paramSource.addValue("state", address.getState());
		paramSource.addValue("zip", address.getZipCode());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			return address;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Address updateAddress(Address address) {
		final String sql = "UPDATE ADDRESS SET ADDRESS_ID=:address_id, STREET1=:street1, STREET2=:street2," +
							" CITY=:city, STATE=:state, ZIP_CODE=:zip WHERE ADDRESS_ID=:address_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("address_id", address.getAddressId());
		paramSource.addValue("street1", address.getStreetAddr1());
		paramSource.addValue("street2", address.getStreetAddr2());
		paramSource.addValue("city", address.getCity());
		paramSource.addValue("state", address.getState());
		paramSource.addValue("zip", address.getZipCode());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			return address;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private static final class AddressRowMapper implements RowMapper<Address> {

		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			Address address = new Address();
			address.setAddressId(rs.getInt("ADDRESS_ID"));
			address.setStreetAddr1(rs.getString("STREET1"));
			address.setStreetAddr2(rs.getString("STREET2"));
			address.setCity(rs.getString("CITY"));
			address.setState(rs.getString("STATE"));
			address.setZipCode(rs.getInt("ZIP_CODE"));
			return address;
		}
		
	}
}
