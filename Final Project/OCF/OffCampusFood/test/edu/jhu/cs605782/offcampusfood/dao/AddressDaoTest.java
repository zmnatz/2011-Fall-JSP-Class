package edu.jhu.cs605782.offcampusfood.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.jhu.cs605782.offcampusfood.entities.Address;

public class AddressDaoTest {
	private AddressDao addressDao = TestDaoFactory.getInstance().getAddressDao();
	private static Address address;
	
	@BeforeClass
	public static void setUpValues() {
		address = new Address();
		address.setCity("Mission");
		address.setState("Kansas");
		address.setStreetAddr1("A Street");
		address.setStreetAddr2("Street 2");
		address.setZipCode(66065);
	}

	@Test
	public void testInsert() {
		address = addressDao.insertAddress(address);
		assertNotNull(address);
	}

	@Test
	public void testUpdate() {
		address.setStreetAddr2("Street 2 Updated");
		address = addressDao.updateAddress(address);
		assertNotNull(address);
	}
}
