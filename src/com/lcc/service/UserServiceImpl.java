package com.lcc.service;

import com.lcc.bean.User;
import com.lcc.dao.UserDAO;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public User login(String name, String password) {
		return userDAO.loginCheck(name, password);
	}

	@Override
	public void add(User user) {
		this.userDAO.save(user);
	}

}
