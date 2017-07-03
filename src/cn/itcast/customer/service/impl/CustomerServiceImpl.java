package cn.itcast.customer.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.customer.dao.CustomerDao;
import cn.itcast.customer.domain.Customer;
import cn.itcast.customer.service.CustomerService;
import cn.itcast.utils.PageBean;
import cn.itcast.utils.UploadUtils;
//开启事务
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	/**
	 * 分页显示客户
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param pageBean
	 * @param criteria
	 * @return
	 * @see cn.itcast.customer.service.CustomerService#findByPage(cn.itcast.utils.PageBean, org.hibernate.criterion.DetachedCriteria)
	 */
	public PageBean<Customer> findByPage(PageBean<Customer> pageBean, DetachedCriteria criteria) {
		
		return customerDao.findByPage(pageBean,criteria);
	}

	
	/**
	 * 查询用户
	 * <p>Title: findByCriteria</p>
	 * <p>Description: </p>
	 * @param criteria
	 * @return
	 * @see cn.itcast.customer.service.CustomerService#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<Customer> findByCriteria(DetachedCriteria criteria) {
		return customerDao.findByCriteria(criteria);
	}


	/**
	 * 保存用户
	 * <p>Title: save</p>
	 * <p>Description: </p>
	 * @param customer
	 * @param upload
	 * @param uploadFileName
	 * @throws IOException 
	 * @see cn.itcast.customer.service.CustomerService#save(cn.itcast.customer.domain.Customer, java.io.File, java.lang.String)
	 */
	@Override
	public void save(Customer customer, File upload, String uploadFileName) throws IOException {
		upload(customer, upload, uploadFileName);
		//调用Dao保存
		customerDao.save(customer);
	}

	/**
	 * 更新用户
	 * <p>Title: update</p>
	 * <p>Description: </p>
	 * @param customer
	 * @param upload
	 * @param uploadFileName
	 * @throws IOException 
	 * @see cn.itcast.customer.service.CustomerService#update(cn.itcast.customer.domain.Customer, java.io.File, java.lang.String)
	 */
	@Override
	public void update(Customer customer, File upload, String uploadFileName) throws IOException {
		if(uploadFileName != null && !"".equals(uploadFileName)){
			//说明用户重新上传了文件
			/*
			 * 1、需要删除之前的文件
			 * 2、上传新文件
			 */
			//删除之前文件
			//之前文件的路径：已经封装到customer中了
			File file = new File(customer.getFile_path());
			if(file != null){
				//删除文件
				file.delete();
			}
			//上传新文件
			upload(customer, upload, uploadFileName);
		}
		//调用DAO更新
		customerDao.update(customer);
	}
	
	//专门用来封装上传数据
	public void upload(Customer customer, File upload, String uploadFileName) throws IOException{
		if(uploadFileName != null && !"".equals(uploadFileName)){
			String basePath = "D:/Tomcat/apache-tomcat-7.0.52-windows-x86/apache-tomcat-7.0.52/upload";
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String realPath = basePath+"/"+uuidName;
			File destFile = new File(realPath);
			//说明用户要求上传文件
			//下面保存文件
			FileUtils.copyFile(upload, destFile);
			//设置真实名称
			customer.setFile_name(uploadFileName);
			//存储路径
			customer.setFile_path(realPath);
		}
	}

	
	public void delete(Customer customer) {
		//首先查询,再删除
		Customer customer2 = customerDao.findById(customer.getCust_id());
		customerDao.delete(customer2);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
