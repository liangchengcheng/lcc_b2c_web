package com.lcc.service;

import com.lcc.bean.User;

public interface UserService {
	public User login(String name,String password);
	
	public void add(User user);
}
