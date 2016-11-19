package com.lcc.common;

public class Constants {
	// order status
	public static final int ORDER_STATUS_POST = 1; 
	public static final int ORDER_STATUS_SEND = 2;
	public static final int ORDER_STATUS_RECEIVE = 3;
	public static final int ORDER_STATUS_END = 4; 

	// session attribute name
	public static final String SESSION_CART = "cart"; 
	public static final String SESSION_USER = "user"; 

	// request attribute name
	public static final String REQ_PRODUCTS = "products"; 
	public static final String REQ_ORDER_NUMBER = "orderNum";
	public static final String REQ_ORDERS = "orders"; 

	public static String OrderStatus(int status) {
		String text = null;
		switch (status) {
		case Constants.ORDER_STATUS_POST:
			text = "ORDER_STATUS_POST";
			break;
			
		case Constants.ORDER_STATUS_SEND:
			text = "ORDER_STATUS_SEND";
			break;

		case Constants.ORDER_STATUS_RECEIVE:
			text = "ORDER_STATUS_RECEIVE";
			break;

		case Constants.ORDER_STATUS_END:
			text = "ORDER_STATUS_END";
			break;
			
		default:
			break;
		}

		return text;
	}
}
