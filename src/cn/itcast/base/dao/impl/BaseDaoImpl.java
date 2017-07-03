package cn.itcast.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;
	private PageBean<T> pageBean;
	
	//在启动的时候就会执行无参构造,因为启动的时候Spring就会创建相应对象
	public BaseDaoImpl(){
		//获取当前对象的class对象
		Class clazzCurrent = this.getClass();
		//获取其继承的父类的接口
		ParameterizedType type = (ParameterizedType) clazzCurrent.getGenericSuperclass();
		//获取其中的泛型,可能是多个
		Type[] types = type.getActualTypeArguments();
		//获取其中第一个
		clazz = (Class) types[0];
	}
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public T findById(Serializable id) {
		T t = (T) this.getHibernateTemplate().get(clazz, id);
		return t;
	}
	public Integer findCount(DetachedCriteria criteria) {
		List<Number> count = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(count != null && count.size() > 0){
			//封装属性
			return count.get(0).intValue();
		}
		return null;
	}

	public PageBean<T> findByPage(PageBean pageBean, DetachedCriteria criteria) {

		//实际上就是封装pageBean
		/*
		 * 需要从数据库封装的数据有
		 * 		totalCount
		 * 		list
		 */
		//添加离线查询条件
		criteria.setProjection(Projections.rowCount());
		//查询数量
		// number类型是所有基本类型的父类
		List<Number> count = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(count != null && count.size() > 0){
			//封装属性
			pageBean.setTotalCount(count.get(0).intValue());
		}
		
		//查询集合
		//首先要清空查询条件
		criteria.setProjection(null);
		int firstResult = (pageBean.getCurrentPage() - 1)*pageBean.getPageSize();
		int maxResults = pageBean.getPageSize();
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		//封装属性
		pageBean.setList(list);
		return pageBean;
	}

	public List findAll() {
		//clazz.getSimpleName(),直接获取类名
		List<T> list = (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
		return list;
	}

	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return list;
	}
	
}
