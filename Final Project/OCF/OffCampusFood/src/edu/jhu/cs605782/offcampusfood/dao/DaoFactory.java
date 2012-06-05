package edu.jhu.cs605782.offcampusfood.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Singleton class that will be used to instantiate 
 * and set up the various DAOs
 * @author Jay Modi
 *
 */
public class DaoFactory {

        private static DaoFactory daoFactory = new DaoFactory();
        private DataSource dataSource = null;
        private AddressDao addressDao = null;
        private ItemDao itemDao = null;
        private CreditCardDao creditCardDao = null;
        private OrderDao orderDao = null;
        private LoginDao loginDao = null;
        private UserDao userDao = null;
        private RestaurantDao restaurantDao = null;

        protected DaoFactory() {}
        public static DaoFactory getInstance() {
                return daoFactory;
        }

        public AddressDao getAddressDao() {
                if(addressDao == null) {
                        if(dataSource == null) {
                                this.getDataSource();
                        }
                        addressDao = new AddressDao();
                        addressDao.setDataSource(dataSource);
                }
                return addressDao;
        }

        public ItemDao getItemDao() {
                if(itemDao == null) {
                        if(dataSource == null) {
                                this.getDataSource();
                        }
                        itemDao = new ItemDao();
                        itemDao.setDataSource(dataSource);
                }
                return itemDao;
        }

        public CreditCardDao getCreditCardDao() {
                if(creditCardDao == null) {
                        if(dataSource == null) {
                                this.getDataSource();
                        }
                        creditCardDao = new CreditCardDao();
                        creditCardDao.setDataSource(dataSource);
                        creditCardDao.setAddressDao(this.getAddressDao());
                }
                return creditCardDao;
        }

        public OrderDao getOrderDao() {
                if(orderDao == null) {
                        if(dataSource == null) {
                                this.getDataSource();
                        }
                        orderDao = new OrderDao();
                        orderDao.setDataSource(dataSource);
                        orderDao.setUserDao(this.getUserDao());
                        orderDao.setItemDao(this.getItemDao());
                }
                return orderDao;
        }

        public LoginDao getLoginDao() {
                if(loginDao == null) {
                        if(dataSource == null) {
                                this.getDataSource();
                        }
                        loginDao = new LoginDao();
                        loginDao.setDataSource(dataSource);
                }
                return loginDao;
        }

        public UserDao getUserDao() {
                if(userDao == null) {
                        if(dataSource == null) {
                                this.getDataSource();
                        }
                        userDao = new UserDao();
                        userDao.setDataSource(dataSource);
                        userDao.setAddressDao(getAddressDao());
                        userDao.setLoginDao(getLoginDao());
                        userDao.setOrderDao(getOrderDao());
                        userDao.setCreditCardDao(getCreditCardDao());
                }
                return userDao;
        }

        public RestaurantDao getRestaurantDao() {
                if(restaurantDao == null) {
                        if(dataSource == null) {
                                this.getDataSource();
                        }
                        restaurantDao = new RestaurantDao();
                        restaurantDao.setDataSource(dataSource);
                        restaurantDao.setAddressDao(getAddressDao());
                        restaurantDao.setItemDao(getItemDao());
                        restaurantDao.setLoginDao(getLoginDao());
                        restaurantDao.setOrderDao(getOrderDao());
                }
                return restaurantDao;
        }

        public void getDataSource() {
                try {
                        InitialContext initialContext = new InitialContext();
                        Context context = (Context) initialContext.lookup("java:comp/env");
                        dataSource = (DataSource) context.lookup("dbPool");
                } catch (NamingException e) {
                        e.printStackTrace();
                }
        }

        protected void setDataSource(DataSource ds) {
                this.dataSource = ds;
        }
}