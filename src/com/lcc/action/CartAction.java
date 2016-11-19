package com.lcc.action;

import com.lcc.bean.Cart;
import com.lcc.bean.Item;
import com.lcc.common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class CartAction extends ActionSupport {
	private Logger logger = Logger.getLogger(this.getClass());

	private HttpServletRequest request;

	private HttpSession session;

	public String CarIndex() {
		this.request = ServletActionContext.getRequest();
		this.session = this.request.getSession();
		Cart cart = (Cart) this.session.getAttribute(Constants.SESSION_CART);
		if (cart == null) {
			cart = new Cart();
			this.session.setAttribute(Constants.SESSION_CART, cart);
			;
		}
		return "cart";
	}

	public String modifyItemNumber() {
		this.request = ServletActionContext.getRequest();
		this.session = this.request.getSession();
		// 获取参数 productId
		String[] itemCheck = this.request.getParameterValues("itemCheck");

		if (itemCheck == null || itemCheck.length == 0) {
			return "cart";
		} else {
			Cart cart = (Cart) this.session.getAttribute(Constants.SESSION_CART);

			for (String productId : itemCheck) {
				// 获取number + productId
				String number = this.request.getParameter("number" + productId);
				// 修改数量
				cart.modifyNumberBYProductId(Long.valueOf(productId), Integer.valueOf(number));
			}

			for (Long id : cart.getCartItems().keySet()) {
				Item it = cart.getCartItems().get(id);
				System.out.println(it.getProduct().getName() + " " + it.getNumber());
			}
			return "cart";
		}
	}

	/**
	 * 删除 Item
	 * @return
	 */
	public String deleteItem() {
		this.request = ServletActionContext.getRequest();
		this.session = this.request.getSession();
		String[] itemCheck = this.request.getParameterValues("itemCheck");
		
		if (itemCheck == null || itemCheck.length == 0) {
			return "cart";
		} else {
			logger.info("Deleting item size=" + itemCheck.length);
			Long[] productIds = new Long[itemCheck.length];
			for (int i = 0; i < productIds.length; i++) {
				productIds[i] = Long.valueOf(itemCheck[i]);
			}

			Cart cart = (Cart) this.session.getAttribute(Constants.SESSION_CART);
			cart.deleteItemByProductId(productIds);
			for (Long id : cart.getCartItems().keySet()) {
				Item it = cart.getCartItems().get(id);
				System.out.println(it.getProduct().getName() + " " + it.getNumber());
			}

			return "cart";
		}
	}
	
	/**
	 * 清除 Item
	 * @return
	 */
	public String clear() {
		this.request = ServletActionContext.getRequest();
		this.session = this.request.getSession();
		logger.info("Cart is clearing...");
		Cart cart = (Cart) this.session.getAttribute(Constants.SESSION_CART);
		cart.clear();
		for (Long id : cart.getCartItems().keySet()) {
			Item it = cart.getCartItems().get(id);
			System.out.println(it.getProduct().getName() + " " + it.getNumber());
		}

		return "cart";
	}

}
