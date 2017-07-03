package cn.itcast.customer.dao.impl;

import cn.itcast.base.dao.impl.BaseDaoImpl;
import cn.itcast.customer.dao.CustomerDao;
import cn.itcast.customer.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	//cn.itcast.customer.dao.impl.CustomerDaoImpl
	//cn.itcast.customer.domain.Customer
	//分页查询
	/*public PageBean<Customer> findByPage(PageBean<Customer> pageBean, DetachedCriteria criteria) {

		//实际上就是封装pageBean
		
		 * 需要从数据库封装的数据有
		 * 		totalCount
		 * 		list
		 
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
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		//封装属性
		pageBean.setList(list);
		return pageBean;
	}*/

	/*@Override
	public List<Customer> findByCriteria(DetachedCriteria criteria) {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	@Override
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}*/

}
