package com.lcc.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lcc.bean.User;
import com.lcc.dto.UserDTO;
import com.lcc.service.UserService;
import com.lcc.util.EmailAttachmentSender;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends ActionSupport implements ModelDriven {

	private UserDTO userDTO = new UserDTO();
	private User user = new User();
	private UserService userService;

	private Map<String, Object> session;
	private EmailAttachmentSender email = new EmailAttachmentSender();
	private String username;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * 用户注册的服务
	 * @return
	 */
	public String userRegister() {
		session = (Map) ActionContext.getContext().getSession();
		String rand = (String) request.getSession().getAttribute("rand");
		String ret = "";
		if ((userDTO.getPassword1() != userDTO.getPassword2())) {
			ret = "reset";
		} else if ((!rand.equals(userDTO.getRand())) || userDTO.getRand() == null) {
			ret = "reset";
		} else if ((userDTO.getEmail() != null) && (userDTO.getName() != null)) {
			user.set(userDTO);
			session.put(user.getName(), user);
			email.send(user.getEmail(), user.getName());
			ret = "verify";
		} else {
			ret = "reset"; 
		}
		return ret;
	}

	@Override
	public Object getModel() {
		return userDTO;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
