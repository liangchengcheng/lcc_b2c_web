package com.lcc.dao;

import com.lcc.bean.User;

public interface UserDAO extends BaseDAO{
	
	public User loginCheck(String name,String password);
	
	public void save(User user);

}
