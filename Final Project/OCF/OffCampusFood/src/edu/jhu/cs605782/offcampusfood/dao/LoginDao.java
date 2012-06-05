package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import edu.jhu.cs605782.offcampusfood.entities.Login;

public class LoginDao extends SimpleJdbcDaoSupport {

	private static final LoginMapper mapper = new LoginMapper();

	public Login getLoginByEmail(String email) {
		final String sql = "SELECT * FROM LOGIN WHERE EMAIL=:email";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", email);
		try {
			Login login = (Login)getSimpleJdbcTemplate().queryForObject(sql, mapper, paramSource);
			return login;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Login insertLogin(Login login) {
		final String sql = "INSERT INTO LOGIN(EMAIL, PASSWORD, LOGIN_TYPE) VALUES(:email, :password, :login_type)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", login.getEmail());
		paramSource.addValue("password", login.getPassword());
		paramSource.addValue("login_type", login.getLoginType());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			return login;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Login updateLogin(Login login) {
		final String sql = "UPDATE LOGIN SET EMAIL=:email, PASSWORD=:password, LOGIN_TYPE=:login_type WHERE EMAIL=:email";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", login.getEmail());
		paramSource.addValue("password", login.getPassword());
		paramSource.addValue("login_type", login.getLoginType());
		try {
			this.getSimpleJdbcTemplate().update(sql, paramSource);
			return login;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final class LoginMapper implements RowMapper<Login> {

		@Override
		public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
			Login login = new Login();
			login.setEmail(rs.getString("EMAIL"));
			login.setPassword(rs.getString("PASSWORD"));
			login.setLoginType(rs.getString("LOGIN_TYPE"));
			return login;
		}
		
	}
}
