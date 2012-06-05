package edu.jhu.cs605782.offcampusfood.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.jhu.cs605782.offcampusfood.entities.Login;

public class LoginDaoTest {

	private LoginDao loginDao = TestDaoFactory.getInstance().getLoginDao();
	private static Login login;
	private static long uniqueTestVal;
	
	@BeforeClass
	public static void setupValues() {
		uniqueTestVal = System.currentTimeMillis();
		login = new Login();
		login.setEmail("l@l.com"+uniqueTestVal);
		login.setPassword("pass");
		login.setLoginType("USER");
	}

	@Test
	public void testInsert() {
		login = loginDao.insertLogin(login);
		assertNotNull(login);
	}

	@Test
	public void testUpdate() {
		login.setLoginType("RESTAURANT");
		login = loginDao.updateLogin(login);
		assertNotNull(login);
	}

	@Test
	public void testGetLogin() {
		Login log = loginDao.getLoginByEmail(login.getEmail());
		assertNotNull(log);
	}
}
