package com.lcc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lcc.bean.Administrator;
import com.lcc.bean.User;
import com.lcc.common.Constants;
import com.lcc.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	private String username;
	
	private String password;
	
	private UserService userService;
	
	private HttpServletRequest request;
	
	private HttpSession httpSession;
	
	@Override
	public String execute() throws Exception {
		this.request = ServletActionContext.getRequest();
		this.httpSession= this.request.getSession();
		
		String ret = "";
		User user = this.userService.login(username, password);
		if (user == null) {
			this.request.setAttribute("msg", "用户已不在线");
			ret ="restart";
		}else {
			this.httpSession.setAttribute(Constants.SESSION_USER, user);
			if (user instanceof Administrator) {
				ret = "manager";
			}else {
				ret = "user";
			}
		}
		return ret;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
