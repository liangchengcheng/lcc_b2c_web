package com.lcc.action;

import com.lcc.bean.Product;
import com.lcc.service.ProductService;
import com.lcc.util.FenciUtil;
import com.lcc.util.WordFilter;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class FindAction extends ActionSupport {

	private ProductService productService;

	private String keyword;

	private List<Product> findlist;

	private int page;

	private int maxpage;

	private HttpServletRequest request;

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<Product> getFindlist() {
		return findlist;
	}

	public void setFindlist(List<Product> findlist) {
		this.findlist = findlist;
	}

	public String findproduct() {
		if (keyword == null) {
			return "restart";
		}

		List<String> keylist = new ArrayList<String>();
		Map<String, String> filtermap = new HashMap<>();
		Map<String, String> keymap = new HashMap<>();

		String word = null;
		String url = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/filter.dic");

		try {
			FenciUtil.fenci(keyword, keylist);
			for (int i = 0; i < keylist.size(); i++) {
				System.out.println(keylist.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		WordFilter.readType(filtermap, url);
		for (String key : filtermap.keySet()) {
			System.out.println("key" + key + "value" + filtermap.get(key));
		}

		for (int i = 0; i < keylist.size(); i++) {
			word = keylist.get(i);
			WordFilter.filterword(word, filtermap, keymap);
		}

		System.out.println("=====================");

		for (String key : keymap.keySet()) {
			System.out.println("key" + key + "value" + keymap.get(key));
		}

		this.request = ServletActionContext.getRequest();
		int maxpage = this.productService.findnum(keymap);
		int pageNo = 1;
		int pageSize = 5;

		if (page > 0) {
			pageNo = page;
		}

		findlist = this.productService.find(keymap, pageNo, pageSize);

		request.setAttribute("fpage", pageNo);

		request.setAttribute("fmaxpage", maxpage);

		return "success";

	}

}
