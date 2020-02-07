/**
 * 
 */
package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author t-Khader
 *
 */
public class MenuItemDaoSqlImpl {

	public List<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub

		Connection connection = ConnectionHandler.getConnection();

		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		ResultSet resultSet;
		boolean freeDeliveryFlg;
		boolean activeFlg;
		try {

			if (connection != null) {

				PreparedStatement prepareStatement = connection
						.prepareStatement("select me_id,me_name,me_price, me_active,me_date_of_launch,me_category,me_free_delivery  from menu_item ");
				resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {

					int menuItemId = resultSet.getInt("me_id");
					String name = resultSet.getString("me_name");
					float price = resultSet.getFloat("me_price");
					String active = resultSet.getString("me_active");
					Date dateOfLaunch = resultSet.getDate("me_date_of_launch");
					String category = resultSet.getString("me_category");
					String freeDelivery = resultSet
							.getString("me_free_delivery");
					if (freeDelivery != null && freeDelivery.equals("Yes")) {
						freeDeliveryFlg = true;
					} else {
						freeDeliveryFlg = false;
					}
					if (active != null && active.equals("Yes")) {
						activeFlg = true;
					} else {
						activeFlg = false;
					}
					MenuItem menuItem = new MenuItem(menuItemId, name, price,
							activeFlg, dateOfLaunch, category, freeDeliveryFlg);
					menuItemList.add(menuItem);

				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return menuItemList;
	}

	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub

		Connection connection = ConnectionHandler.getConnection();

		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		ResultSet resultSet;
		boolean freeDeliveryFlg;
		boolean activeFlg;
		try {

			if (connection != null) {

				PreparedStatement prepareStatement = connection
						.prepareStatement("select * from menu_item where me_date_of_launch <= now() and me_active ='Yes' ");

				resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {

					int menuItemId = resultSet.getInt(1);
					String name = resultSet.getString(2);
					float price = resultSet.getFloat(3);
					String active = resultSet.getString(4);
					Date dateOfLaunch = resultSet.getDate(5);
					String category = resultSet.getString(6);
					String freeDelivery = resultSet.getString(7);
					if (freeDelivery != null && freeDelivery.equals("Yes")) {
						freeDeliveryFlg = true;
					} else {
						freeDeliveryFlg = false;
					}
					if (active != null && active.equals("Yes")) {
						activeFlg = true;
					} else {
						activeFlg = false;
					}
					MenuItem menuItem = new MenuItem(menuItemId, name, price,
							activeFlg, dateOfLaunch, category, freeDeliveryFlg);
					menuItemList.add(menuItem);

				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return menuItemList;
	}

	public MenuItem getMenuItem(long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();

		ResultSet resultSet;
		boolean freeDeliveryFlg;
		boolean activeFlg;
		MenuItem menuItem = null;

		try {

			if (connection != null) {

				PreparedStatement prepareStatement = connection
						.prepareStatement("select * from menu_item where  me_id =? ");
				prepareStatement.setLong(1, menuItemId);
				resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {

					int menuitemId = resultSet.getInt(1);
					String name = resultSet.getString(2);
					float price = resultSet.getFloat(3);
					String active = resultSet.getString(4);
					Date dateOfLaunch = resultSet.getDate(5);
					String category = resultSet.getString(6);
					String freeDelivery = resultSet.getString(7);
					if (freeDelivery != null && freeDelivery.equals("Yes")) {
						freeDeliveryFlg = true;
					} else {
						freeDeliveryFlg = false;
					}
					if (active != null && active.equals("Yes")) {
						activeFlg = true;
					} else {
						activeFlg = false;
					}
					menuItem = new MenuItem(menuitemId, name, price, activeFlg,
							dateOfLaunch, category, freeDeliveryFlg);

				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return menuItem;

	}

	public void modifyMenuItem(MenuItem menuItem) {

		Connection connection = ConnectionHandler.getConnection();
		String sql = "update menu_item set me_name  = ? , me_price = ? , me_active = ? ,me_date_of_launch = ?,me_category = ?,me_free_delivery=? where me_id = ?";

		try {

			if (connection != null) {

				PreparedStatement prepareStatement = connection
						.prepareStatement(sql);
				prepareStatement.setString(1, menuItem.getName());
				prepareStatement.setFloat(2, menuItem.getPrice());
				if (menuItem.isActive())
					prepareStatement.setString(3, "Yes");
				else
					prepareStatement.setString(3, "No");
				prepareStatement.setDate(4, new java.sql.Date(menuItem
						.getDateOfLaunch().getTime()));
				prepareStatement.setString(5, menuItem.getCategory());
				if (menuItem.isFreeDelivery())
					prepareStatement.setString(6, "Yes");
				else
					prepareStatement.setString(6, "No");

				prepareStatement.setLong(7, menuItem.getId());
				prepareStatement.executeUpdate();
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
}
