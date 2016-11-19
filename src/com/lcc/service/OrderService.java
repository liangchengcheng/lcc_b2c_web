package com.lcc.service;

import java.util.List;

import com.lcc.bean.Order;

public interface OrderService {
	
	public String generateOrder( Order order );
	
	public List<Order> getAllOrder( );
	
	public Order getOrderById( long id );
	
	public void nextOrderStatus( long id );
	
}
