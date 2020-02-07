/**
 * 
 */
package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

/**
 * @author t-Khader
 *
 */
public class CartDaoSqlImpl implements CartDao{

	public void addCartItem(long userid, long menuItemId) {

		PreparedStatement prepareStatement;
		Connection conn = null;
		try {
			conn = ConnectionHandler.getConnection();
			if (conn != null) {
				prepareStatement = conn
						.prepareStatement("insert into cart values(default,?,?)");

				prepareStatement.setInt(1, (int) userid);
				prepareStatement.setInt(2, (int) menuItemId);

				prepareStatement.executeUpdate();
				System.out.println("Record inserted successfully...");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public List<MenuItem> getAllCartItems(long userid)
			throws CartEmptyException {

		PreparedStatement prepareStatement;
		Connection conn = null;
		ResultSet resultSet;
		boolean freeDeliveryFlg;
		boolean activeFlg;
		MenuItem menuItem;

		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		try {
			conn = ConnectionHandler.getConnection();
			if (conn != null) {

				Cart cart = new Cart(menuItemList, 0);

				StringBuffer sqlBuffer = new StringBuffer();

				sqlBuffer
						.append(" select mi.me_id ,mi.me_name,mi.me_price,mi.me_active,mi.me_date_of_launch , ")
						.append(" mi.me_category ,mi.me_free_delivery  from menu_item mi inner ")
						.append(" join cart ct on ")
						.append(" mi.me_id = ct.ct_pr_id ")
						.append(" and ct.ct_us_id = ? ");
				System.out.println("SqlString .." + sqlBuffer.toString());
				prepareStatement = conn.prepareStatement(sqlBuffer.toString());
				prepareStatement.setInt(1, (int) userid);
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
					menuItemList.add(menuItem);

				}
				cart.setMenuItemList(menuItemList);
				cart.setTotal(getTotalPrice(userid, conn));

				System.out.println("Records fetched successfully...");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return menuItemList;

	}

	private double getTotalPrice(long userid, Connection conn) {

		PreparedStatement prepareStatement;

		ResultSet resultSet;

		double total = 0;

		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		try {

			if (conn != null) {

			

				StringBuffer sqlBuffer = new StringBuffer();

				sqlBuffer
						.append(" select SUM(me_price) from menu_item mi inner")
						.append(" join cart ct on ")
						.append(" mi.me_id = ct.ct_pr_id ")
						.append(" and ct.ct_us_id = ? ");
				System.out.println("SqlString .." + sqlBuffer.toString());
				prepareStatement = conn.prepareStatement(sqlBuffer.toString());
				prepareStatement.setInt(1, (int) userid);
				resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {

					total = resultSet.getDouble(1);
				}

				System.out.println("Record fetched successfully...");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

		}
		System.out.println("total.." + total);
		return total;

	}

	public void removeCartItem(long userId, long menuitemid) {
		PreparedStatement prepareStatement;
		Connection conn = null;
		try {
			conn = ConnectionHandler.getConnection();
			if (conn != null) {
				prepareStatement = conn
						.prepareStatement("delete from cart where ct_us_id=? and ct_pr_id = ?");

				prepareStatement.setInt(1, (int) userId);
				prepareStatement.setInt(2, (int) menuitemid);

				prepareStatement.executeUpdate();
				System.out.println("Record deleted successfully...");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
