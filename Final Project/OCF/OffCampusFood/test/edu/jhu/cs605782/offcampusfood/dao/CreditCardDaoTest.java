package edu.jhu.cs605782.offcampusfood.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.entities.CreditCard;

public class CreditCardDaoTest {

	private CreditCardDao ccDao = TestDaoFactory.getInstance().getCreditCardDao();
	private static CreditCard creditCard;

	@BeforeClass
	public static void setUpValues() {
		creditCard = new CreditCard();
		creditCard.setUserId(120);
		Address address = new Address();
		address.setCity("Falls Church");
		address.setState("Virginia");
		address.setStreetAddr1("1920 Storm Dr");
		address.setZipCode(22043);
		creditCard.setAddress(address);
		creditCard.setCcNumber("4000000000001111");
		creditCard.setExpirationDate(new Date());
		creditCard.setName("Some Dude");
		creditCard.setPhoneNumber("555-555-5555");
	}

	@Test
	public void testInsertCC() {
		creditCard = ccDao.insertCreditCard(creditCard);
		assertNotNull(creditCard);
	}

	@Test
	public void testUpdateCC() {
		creditCard.setName("Some Dude Updated");
		creditCard = ccDao.updateCreditCard(creditCard);
		assertNotNull(creditCard);
	}

	@Test
	public void testGetCreditCardList() {
		List<CreditCard> list = ccDao.getCreditCardsByUserId(120);
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("Empty List of CCs");
		}
	}
}
