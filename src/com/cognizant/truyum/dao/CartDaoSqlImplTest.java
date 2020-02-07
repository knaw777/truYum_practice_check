/**
 * 
 */
package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

/**
 * @author t-Khader
 *
 */
public class CartDaoSqlImplTest {
	
	public static void main(String args[]){
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
		//cartDaoSqlImpl.addCartItem(2,3);
		//System.out.println("Data added successfully into Cart table");
		/*try {
		List<MenuItem> menuItemList = cartDaoSqlImpl.getAllCartItems(2);
		System.out.println("MenuItemList ..="+menuItemList);
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
		
		
		
		//cartDaoSqlImpl.addCartItem(2,5);
		cartDaoSqlImpl.removeCartItem(2, 3); 
		
		
		
	}

}
