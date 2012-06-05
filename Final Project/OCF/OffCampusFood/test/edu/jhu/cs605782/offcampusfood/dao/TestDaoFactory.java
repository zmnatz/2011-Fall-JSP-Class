package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class TestDaoFactory extends DaoFactory {

	private static TestDaoFactory factory = new TestDaoFactory();
	private TestDaoFactory() {}

	public static TestDaoFactory getInstance() {
		return factory;
	}

	@Override
	public void getDataSource() {
		try {
			OracleDataSource ds = new OracleDataSource();
			ds.setDriverType("thin");
			ds.setServerName("localhost");
			ds.setDatabaseName("xe");
			ds.setPortNumber(1521);
			ds.setUser("FOOD");
			ds.setPassword("FOOD");
			this.setDataSource(ds);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
