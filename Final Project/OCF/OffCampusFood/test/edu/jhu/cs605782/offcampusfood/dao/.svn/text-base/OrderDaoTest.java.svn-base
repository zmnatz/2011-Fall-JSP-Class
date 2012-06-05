package edu.jhu.cs605782.offcampusfood.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.jhu.cs605782.offcampusfood.entities.Item;
import edu.jhu.cs605782.offcampusfood.entities.Order;

public class OrderDaoTest {

	private OrderDao orderDao = TestDaoFactory.getInstance().getOrderDao();
	private static Order order;

	@BeforeClass
	public static void setUpValues() {
		order = new Order();
		order.setUserId(120);
		order.setRestaurantId(101);
		List<Item> items = new ArrayList<Item>();
		Item item = new Item();
		item.setItemId(102);
		items.add(item);
		item = new Item();
		item.setItemId(105);
		items.add(item);
		order.setItems(items);
		order.setOrderDate(new Date());
	}

	@Test
	public void testInsert() {
		order = orderDao.insertOrder(order);
		assertNotNull(order);
		assertNotNull(order.getOrderId());
	}

	@Test
	public void testUpdate() {
		order.setComplete(Boolean.TRUE);
		order = orderDao.updateOrder(order);
		assertNotNull(order);
	}

	@Test
	public void testGetOrdersByUserId() {
		List<Order> list = orderDao.getOrdersByUserId(120);
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("User does not have any orders");
		}
	}

	@Test
	public void testGetOrdersByRestaurantId() {
		List<Order> list = orderDao.getOrdersByRestaurantId(101);
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("Restaurant does not have any orders");
		}
	}
}
