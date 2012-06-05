package edu.jhu.cs605782.offcampusfood.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import edu.jhu.cs605782.offcampusfood.entities.Item;

public class ItemDao extends SimpleJdbcDaoSupport {

	private static final ItemRowMapper itemMapper = new ItemRowMapper();

	public Item getItemById(Integer id) {
		final String sql = "SELECT * FROM ITEM WHERE ITEM_ID=:item_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("item_id", id);
		return getSimpleJdbcTemplate().queryForObject(sql, itemMapper, paramSource);
	}

	private Integer getNextItemID() {
		final String sql = "SELECT ITEM_SEQ.NEXTVAL FROM DUAL";
		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	public Item insertItem(Item item, Integer restaurantId) {
		final String sql = "INSERT INTO ITEM (ITEM_ID, NAME, DESCRIPTION, PRICE, ACTIVE)" + 
						" VALUES (:item_id, :name, :description, :price, :active)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		item.setItemId(this.getNextItemID());
		paramSource.addValue("item_id", item.getItemId());
		paramSource.addValue("name", item.getName());
		paramSource.addValue("description", item.getDescription());
		paramSource.addValue("price", item.getPrice());
		paramSource.addValue("active", item.getActive());
		try {
			getSimpleJdbcTemplate().update(sql, paramSource);
			createRestaurantItemXref(restaurantId, item.getItemId());
			return item;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Item> getItemsByOrderId(Integer orderId) {
		final String sql = "SELECT * FROM ORDER_ITEM_XREF xref JOIN ITEM it on xref.item_id = it.item_id" +
							" WHERE ORDER_ID=:order_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("order_id", orderId);
		try {
			List<Item> list = getSimpleJdbcTemplate().query(sql, itemMapper, paramSource);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Item> getItemsByRestaurantId(Integer restaurantId) {
		final String sql = "SELECT * FROM RESTAURANT_ITEM_XREF xref JOIN ITEM it on xref.item_id = it.item_id" +
							" WHERE RESTAURANT_ID=:restaurant_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("restaurant_id", restaurantId);
		try {
			List<Item> list = getSimpleJdbcTemplate().query(sql, itemMapper, paramSource);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Item updateItem(Item item) {
		final String sql = "UPDATE ITEM SET ITEM_ID=:item_id, NAME=:name, DESCRIPTION=:description, PRICE=:price, ACTIVE=:active " +
						"WHERE ITEM_ID=:item_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("item_id", item.getItemId());
		paramSource.addValue("name", item.getName());
		paramSource.addValue("description", item.getDescription());
		paramSource.addValue("price", item.getPrice());
		paramSource.addValue("active", item.getActive());
		try {
			getSimpleJdbcTemplate().update(sql, paramSource);
			return item;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createOrderItemXref(List<Item> items, Integer orderId) throws Exception {
		final String sql = "INSERT INTO ORDER_ITEM_XREF(ORDER_ID, ITEM_ID) VALUES(:order_id, :item_id)";
		for(Item item:items) {
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("order_id", orderId);
			paramSource.addValue("item_id", item.getItemId());
			this.getSimpleJdbcTemplate().update(sql, paramSource);
		}
	}

	public void updateOrderItemXref(List<Item> items, Integer orderId) throws Exception {
		//Delete all items
		final String sql = "DELETE FROM ORDER_ITEM_XREF WHERE ORDER_ID=:order_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("order_id", orderId);
		this.getSimpleJdbcTemplate().update(sql, paramSource);
		
		//Add all items
		this.createOrderItemXref(items, orderId);
	}

	private void createRestaurantItemXref(Integer restId, Integer itemId) {
		final String sql = "INSERT INTO RESTAURANT_ITEM_XREF(RESTAURANT_ID, ITEM_ID) VALUES(:restaurant_id, :item_id)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("restaurant_id", restId);
		paramSource.addValue("item_id", itemId);
		this.getSimpleJdbcTemplate().update(sql, paramSource);
	}

	private static final class ItemRowMapper implements RowMapper<Item> {

		@Override
		public Item mapRow(ResultSet rs, int rownum) throws SQLException {
			Item item = new Item();
			item.setItemId(rs.getInt("ITEM_ID"));
			item.setDescription(rs.getString("DESCRIPTION"));
			item.setName(rs.getString("NAME"));
			item.setPrice(rs.getBigDecimal("PRICE"));
			item.setActive(rs.getBoolean("ACTIVE"));
			return item;
		}
		
	}
}
