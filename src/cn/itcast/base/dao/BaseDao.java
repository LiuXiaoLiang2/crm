package cn.itcast.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.customer.domain.Customer;
import cn.itcast.utils.PageBean;

public interface BaseDao<T> {
	//增
	public void save(T t);
	//删
	public void update(T t);
	//改
	public void delete(T t);
	//查单个
	public T findById(Serializable id);
	//聚合查询
	public Integer findCount(DetachedCriteria detachedCriteria);
	//QBC分页查询
	public PageBean<T> findByPage(PageBean<T> pageBean, DetachedCriteria criteria);
	//查询所有
	public List<T> findAll();
	//QBC条件查询
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
