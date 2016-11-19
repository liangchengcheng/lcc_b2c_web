package com.lcc.service;

import java.util.List;
import java.util.Map;
import com.lcc.bean.Product;
import com.lcc.dao.BaseDAO;

public class ProductServiceImpl implements ProductService {

	private BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	public Product getProduct(long productId) {
		return baseDAO.getEntityById(Product.class, productId);
	}

	@Override
	public List<Product> getALLProducts() {
		return baseDAO.getAllEntity("Product");
	}

	@Override
	public void addProduct(Product product) {
		this.baseDAO.saveEntity(product);
	}

	@Override
	public void deleteProduct(long productId) {
		this.baseDAO.deleteEntityById(Product.class, productId);
	}

	@Override
	public void UpdateProduct(Product product) {
		this.baseDAO.updateEntity(product);
	}

	@Override
	public List<Product> getByPage(int pageNo, int pageSize) {
		String hql = "from Product";
		int real = baseDAO.realPage(hql);
		return baseDAO.getbyPage(hql, pageNo, pageSize, real);
	}

	@Override
	public int maxSize() {
		String hql = "from Product";
		int count = 0;
		int maxpage = 0;

		count = baseDAO.realPage(hql);
		maxpage = (count + 4) / 5;
		return maxpage;
	}

	@Override
	public List<Product> find(Map<String, String> keymap, int pageNo, int pageSize) {
		String hql = "from Product as p where ";
		int num = 1;
		for (String key : keymap.keySet()) {
			if (num > 1) {
				hql += "and p.name like '%" + keymap.get(key) + "%'";
			} else {
				hql += " p.name like '%" + keymap.get(key) + "%'";
			}
			num++;
		}
		int real = baseDAO.realPage(hql);
		return baseDAO.getbyPage(hql, pageNo, pageSize, real);
	}

	@Override
	public int findnum(Map<String, String> keymap) {
		String hql = "from Product as p where ";
		int num = 1;
		for (String key : keymap.keySet()) {
			if (num > 1) {
				hql += " and p.name like '%" + keymap.get(key) + "%'";
			} else {
				hql += " p.name like '%" + keymap.get(key) + "%'";
			}
			num++;
		}
		int count = 0;
		int maxpage = 0;
		count = baseDAO.realPage(hql);
		maxpage = (count + 4) / 5;
		return maxpage;
	}

}
