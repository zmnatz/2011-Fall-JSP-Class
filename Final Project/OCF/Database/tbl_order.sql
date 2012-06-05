create table FOOD.ORDERS
(
  ORDER_ID NUMBER NOT NULL,
  RESTAURANT_ID NUMBER NOT NULL,
  USER_ID NUMBER NOT NULL,
  STATUS NUMBER,
  RCRD_CRTD_DT TIMESTAMP(6) WITH TIME ZONE
)
/
ALTER TABLE FOOD.ORDERS
ADD CONSTRAINT ORDERS_ID_PK PRIMARY KEY(ORDER_ID)
/
ALTER TABLE FOOD.ORDERS
ADD CONSTRAINT ORDERS_RESTAURANT_ID_FK FOREIGN KEY(RESTAURANT_ID)
REFERENCES FOOD.RESTAURANT(RESTAURANT_ID)
/
ALTER TABLE FOOD.ORDERS
ADD CONSTRAINT ORDERS_USER_ID_FK FOREIGN KEY(USER_ID)
REFERENCES FOOD.USERS(USER_ID)
/