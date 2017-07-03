package cn.itcast.customer.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.customer.domain.Customer;
import cn.itcast.linkman.domain.LinkMan;
import cn.itcast.utils.PageBean;

public interface CustomerService {

	PageBean<Customer> findByPage(PageBean<Customer> pageBean, DetachedCriteria criteria);

	List<Customer> findByCriteria(DetachedCriteria criteria);

	void save(Customer customer, File upload, String uploadFileName) throws IOException;

	void update(Customer customer, File upload, String uploadFileName) throws IOException;

	void delete(Customer customer);

	List<Customer> findAll();

}
