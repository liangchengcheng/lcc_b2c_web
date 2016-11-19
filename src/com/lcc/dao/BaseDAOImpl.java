package com.lcc.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDAOImpl implements BaseDAO {

	private HibernateTemplate hibernateTemplate;

	protected Logger logger = Logger.getLogger(this.getClass());

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public <T> void saveEntity(T entity) {
		this.hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public <T> void deleteEntity(T entity) {
		this.hibernateTemplate.delete(entity);
	}

	@Override
	public <T> void deleteEntityById(Class<T> entityClass, Serializable id) {
		this.hibernateTemplate.delete( this.getEntityById(entityClass, id) );
	}

	@Override
	public <T> void updateEntity(T entity) {
		this.hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public <T> List<T> getAllEntity(String entityName) {
		return (List<T>)this.hibernateTemplate.find("from" +  " "+ entityName );
	}

	@Override
	public <T> T getEntityById(Class<T> entityClass, Serializable id) {
		return (T) this.hibernateTemplate.get(entityClass, id);
	}

	@Override
	public <T> List<T> getbyPage(String hql, int pageNo, int pageSize, int real) {
		List<T> list = new ArrayList<T>();
		int begin =  (pageNo-1 )*pageSize;
		int end =  (pageNo )*pageSize;
		if( end > real ){
			end = real;
		}
		list = this.hibernateTemplate.find(hql).subList( begin , end );
		
		return list;
	}

	@Override
	public <T> int realPage(String hql) {
		int count = 0;
		count = this.hibernateTemplate.find(hql).size();
		return count;
	}

}
