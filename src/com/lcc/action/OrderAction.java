package com.lcc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lcc.bean.Cart;
import com.lcc.bean.Order;
import com.lcc.bean.User;
import com.lcc.common.Constants;
import com.lcc.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {
	private OrderService orderService;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private HttpServletRequest request ;

	private HttpSession session ;
	
	public String index(){
		String ret = "";
		this.request = ServletActionContext.getRequest();
		this.session =  this.request.getSession();
		User user = (User) this.session.getAttribute(Constants.SESSION_USER);
		if (user == null) {
			ret ="login";
		}else {
			ret ="order";
		}
		return ret;
	}
	
	public String postOrder(){
		this.request = ServletActionContext.getRequest();
		this.session = this.request.getSession();
		User user = (User) this.session.getAttribute(Constants.SESSION_USER);
		Cart cart = (Cart) this.session.getAttribute(Constants.SESSION_CART);
		
		Order order = new Order();
		order.setUser(user);
		order.setItems(cart.getItems());
		order.setStatus(Constants.ORDER_STATUS_POST);
		
		String orderNum = orderService.generateOrder(order);
		this.request.setAttribute(Constants.REQ_ORDER_NUMBER, orderNum);
		return "success";
	}
}
