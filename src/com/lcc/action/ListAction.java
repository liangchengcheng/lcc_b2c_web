package com.lcc.action;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.lcc.bean.Cart;
import com.lcc.bean.Product;
import com.lcc.common.Constants;
import com.lcc.service.ProductService;
import com.lcc.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class ListAction extends ActionSupport {

	private ProductService productService;

	private List<Product> productlist;

	private Long productId;

	private UserService userService;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private HttpSession session;

	private ServletContext application;

	private int page;

	private int maxpage;

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	/**
	 * 获取list
	 * @return
	 */
	private String list(){
		this.request = ServletActionContext.getRequest();
		int maxpage =this.productService.maxSize();
		int pageNo = 1;
		int pageSize = 5;
		if (pageNo > 0) {
			pageNo = page;
		}
		
		productlist = this.productService.getByPage(pageNo, pageSize);
		request.setAttribute("page", pageNo);
		request.setAttribute("maxpage", maxpage);
		return "list";
	}
	
	public String addItem(){
		String ret ="add";
		Product product = this.productService.getProduct(productId);
		
		this.request = ServletActionContext.getRequest();
		this.session = this.request.getSession();
		
		Cart cart = (Cart) this.session.getAttribute(Constants.SESSION_CART);
		if (cart == null) {
			cart = new Cart();
			this.session.setAttribute(Constants.SESSION_CART, cart);
		}
		cart.addItem(product, 1);
		if( type == 1 ){
			ret ="find";
			type = 0;
		}
		
		return ret;
	}

}
