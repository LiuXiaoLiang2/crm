package cn.itcast.customer.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.customer.domain.Customer;
import cn.itcast.utils.PageBean;

public interface CustomerDao extends BaseDao<Customer> {

	PageBean<Customer> findByPage(PageBean<Customer> pageBean, DetachedCriteria criteria);

	List<Customer> findByCriteria(DetachedCriteria criteria);

	void save(Customer customer);

	void update(Customer customer);

	void delete(Customer customer);

}
