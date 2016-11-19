package com.lcc.dao;

import java.util.List;

import com.lcc.bean.User;

public class UserDAOImpl extends BaseDAOImpl implements UserDAO{

	@Override
	public User loginCheck(String name, String password) {
		String hql = "from com.lcc.bean.User u where u.name = "+"'"+name+"'"+" "+"and u.password =" + " " + "'"+password +"'";
		List<User> list = this.getHibernateTemplate().find(hql);
		return null;
	}

	@Override
	public void save(User user) {
		super.saveEntity(user);
	}

}
