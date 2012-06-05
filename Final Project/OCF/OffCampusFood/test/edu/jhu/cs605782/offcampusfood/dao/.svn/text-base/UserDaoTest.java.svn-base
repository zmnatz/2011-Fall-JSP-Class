package edu.jhu.cs605782.offcampusfood.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.entities.Login;
import edu.jhu.cs605782.offcampusfood.entities.Order;
import edu.jhu.cs605782.offcampusfood.entities.User;

public class UserDaoTest {

	private UserDao userDao = TestDaoFactory.getInstance().getUserDao();
	private static long uniqueTestVal;
	private static User user;
	
	@BeforeClass
	public static void setUpValues() {
		uniqueTestVal = System.currentTimeMillis();
		user = new User();
		user.setName("Test Testie");
		user.setPhoneNumber("555-555-5555");
		
		Address address = new Address();
		address.setCity("Falls Church");
		address.setState("VA");
		address.setStreetAddr1("1920 Storm Dr");
		address.setZipCode(22043);
		user.setAddress(address);

		Login login = new Login();
		login.setEmail("j@j.com"+uniqueTestVal);
		login.setPassword("password");
		login.setLoginType("USER");
		user.setLogin(login);
		user.setOrders(new ArrayList<Order>());
	}
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testInsertUser() {
		user = userDao.insertUser(user);
		assertNotNull(user);
	}

	@Test
	public void testRetrieveUser() {
		User user2 = userDao.getUserByEmail(user.getLogin().getEmail());
		assertNotNull(user2);
		
		user2 = userDao.getUserByUserId(user.getUserId());
		assertNotNull(user2);
	}

	@Test
	public void updateUser() {
		user.setName("Updated NAME");
		user = userDao.updateUser(user);
		assertNotNull(user);
	}
}
