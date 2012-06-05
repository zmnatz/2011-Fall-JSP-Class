package edu.jhu.cs605782.offcampusfood.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.entities.Item;
import edu.jhu.cs605782.offcampusfood.entities.Login;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;

public class RestaurantDaoTest {

	private RestaurantDao restaurantDao = TestDaoFactory.getInstance().getRestaurantDao();
	private static Restaurant restaurant;
	private static long uniqueTestVal;

	@BeforeClass
	public static void setUpValues() {
		uniqueTestVal = System.currentTimeMillis();
		restaurant = new Restaurant();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		restaurant.setClose(cal.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 9);
		restaurant.setOpen(cal.getTime());

		restaurant.setName("A Restaurant");
		restaurant.setPhoneNumber("555-555-5556");
		Login login = new Login();
		login.setEmail("r@r.com"+uniqueTestVal);
		login.setPassword("password");
		login.setLoginType("RESTAURANT");
		restaurant.setLogin(login);
		
		Address address = new Address();
		address.setCity("Gaithersburg");
		address.setState("MD");
		address.setStreetAddr1("Street Address 1");
		address.setStreetAddr2("2");
		address.setZipCode(20877);
		restaurant.setAddress(address);
	}

	@Test
	public void testInsertRestaurant() {
		restaurant = restaurantDao.insertRestaurant(restaurant);
		assertNotNull(restaurant);
	}

	@Test
	public void testUpdateRestaurant() {
		List<Item> menu = new ArrayList<Item>();
		Item item = new Item();
		item.setDescription("Item 1 Desc");
		item.setName("Item 1");
		item.setPrice(new BigDecimal(12.00));
		menu.add(item);
		
		item = new Item();
		item.setDescription("Item 2 Desc");
		item.setName("Item 2");
		item.setPrice(new BigDecimal(15.00));
		menu.add(item);
		restaurant.setMenu(menu);
		restaurant = restaurantDao.updateRestaurant(restaurant);
		assertNotNull(restaurant);
		if(restaurant.getMenu().size() != 2) {
			fail("There should be 2 Items in List");
		}
	}

	@Test
	public void testGetRestaurantByEmail() {
		Restaurant rest = restaurantDao.getRestaurantByEmail(restaurant.getLogin().getEmail());
		assertNotNull(rest);
	}

	@Test
	public void testGetRestaurantById() {
		Restaurant rest = restaurantDao.getRestaurantById(restaurant.getRestaurantId());
		assertNotNull(rest);
	}

	@Test
	public void testGetRestaurantList() {
		List<Restaurant> list = restaurantDao.getAllRestaurants();
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("Restaurant List is empty!");
		}
	}
}
