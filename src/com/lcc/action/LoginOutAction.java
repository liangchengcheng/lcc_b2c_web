package com.lcc.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lcc.bean.User;
import com.lcc.common.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class LoginOutAction extends ActionSupport{

	private HttpServletRequest request;
	
	private HttpSession session;
	
	private ServletContext application;
	
	@Override
	public String execute() throws Exception {
		this.request = ServletActionContext.getRequest();
		
		this.session = this.request.getSession();
		User user = (User) this.session.getAttribute(Constants.SESSION_USER);
		this.session.removeAttribute(Constants.SESSION_USER);;
		return "out";
	}
}
