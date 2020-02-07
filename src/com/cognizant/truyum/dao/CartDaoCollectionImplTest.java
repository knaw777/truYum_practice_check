/**
 * 
 */
package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

/**
 * @author t-Khader
 *
 */
public class CartDaoCollectionImplTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CartEmptyException {
		// TODO Auto-generated method stub

		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();

	}

	static void testAddCartItem() throws CartEmptyException {

		CartDaoCollectionImpl cartDaoCollectionImpl = new CartDaoCollectionImpl();
		CartDao cartDao = cartDaoCollectionImpl;
		cartDao.addCartItem(1, 000004);
		cartDao.addCartItem(1, 000003);
		cartDao.addCartItem(2, 000003);
		System.out.println("HashMap Inside add cart Item:" + cartDaoCollectionImpl.getUserCarts());

	}

	static void testGetAllCartItems() throws CartEmptyException {

		CartDaoCollectionImpl cartDaoCollectionImpl = new CartDaoCollectionImpl();
		CartDao cartDao = cartDaoCollectionImpl;

		List<MenuItem> menuItemList = cartDao.getAllCartItems(1);
		System.out.println("MenuItem list :" + menuItemList);
		System.out.println("HashMap :" + cartDaoCollectionImpl.getUserCarts());
		
		

	}

	static void testRemoveCartItem() {

		CartDaoCollectionImpl cartDaoCollectionImpl = new CartDaoCollectionImpl();
		CartDao cartDao = cartDaoCollectionImpl;
		cartDao.removeCartItem(1, 000004);
		
		//System.out.println("MenuItem list :" + menuItemList);
		System.out.println("HashMap :" + cartDaoCollectionImpl.getUserCarts());

	}

}
