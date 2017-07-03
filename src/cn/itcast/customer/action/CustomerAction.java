package cn.itcast.customer.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import cn.itcast.base.action.BaseAction;
import cn.itcast.customer.domain.Customer;
import cn.itcast.customer.service.CustomerService;
import cn.itcast.dict.domain.Dict;
import cn.itcast.dict.service.DictService;
import cn.itcast.utils.PageBean;

public class CustomerAction extends BaseAction implements ModelDriven<Customer>{
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	/**
	 * 注意就是封装数据
	 * 分页显示用户
	 * 		有哪些数据是前台传递封装的
	 * 			currentPage,pageSize	---使用属性驱动
	 * 		有哪些数据是需要从数据库查询封装的
	 * 			totalCount,list			--使用离线查询对象查询
	 * <p>Title: pageList</p>
	 * <p>Description: </p>
	 * @return
	 */
	/*private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}*/



	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}


	public String findByPage(){
		
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		//创建离线查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		if(customer.getCust_name() != null && !"".equals(customer.getCust_name())){
			//添加条件查询---完成筛选功能
			criteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		Dict source = customer.getSource();
		if(source != null && !"".equals(source.getDict_id())){
			//说明用户要求查询所属行业
			//添加查询条件
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		Dict industry = customer.getIndustry();
		if(industry != null && !"".equals(industry.getDict_id())){
			//说明用户要求查询所属行业
			//添加查询条件
			criteria.add(Restrictions.eq("industry.dict_id", industry.getDict_id()));
		}
		
		Dict level = customer.getLevel();
		if(level != null && !"".equals(level.getDict_id())){
			//说明用户要求查询所属行业
			//添加查询条件
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		
		//调用Service
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrentPage(this.getCurrentPage());
		pageBean.setPageSize(this.getPageSize());
		pageBean = customerService.findByPage(pageBean,criteria);
		//将pageBean,放到值栈中
		valueStack.push(pageBean);
		
		//------------------为了可以显示筛选框
		DetachedCriteria criteriaSource = DetachedCriteria.forClass(Dict.class);
		criteriaSource.add(Restrictions.eq("dict_type_code", "002"));
		List<Dict> dictSourceList = dictService.findDictByCriteria(criteriaSource);
		valueStack.set("dictSourceList", dictSourceList);
		
		
		DetachedCriteria criteriaIndustry = DetachedCriteria.forClass(Dict.class);
		criteriaIndustry.add(Restrictions.eq("dict_type_code", "001"));
		List<Dict> dictIndustryList = dictService.findDictByCriteria(criteriaIndustry);
		valueStack.set("dictIndustryList", dictIndustryList);
		
		
		DetachedCriteria criteriaLevel = DetachedCriteria.forClass(Dict.class);
		criteriaLevel.add(Restrictions.eq("dict_type_code", "006"));
		List<Dict> criteriaLevelList = dictService.findDictByCriteria(criteriaLevel);
		valueStack.set("criteriaLevelList", criteriaLevelList);
		
		return "list";
	}
	
	
	public String findByCustName() throws UnsupportedEncodingException{
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		String cust_name = customer.getCust_name();
		cust_name = new String(cust_name.getBytes("iso-8859-1"), "utf-8");
		criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		//调用service
		List<Customer> list = customerService.findByCriteria(criteria);
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
	
	public String initSave(){
		return "toAdd";
	}
	
	/**
	 * 增加用户
	 * <p>Title: add</p>
	 * <p>Description: </p>
	 * @return
	 */
	//上传项
	//上传文件
	
	private File upload;
	private String uploadContentType ;//上传文件的mime类型
	private String uploadFileName;//文件名(包括后缀名)
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String add() throws IOException{
		
		customerService.save(customer,upload, uploadFileName);
		return "toListAction";
	}
	
	/**
	 * 跳转到编辑页面,需要查询对象放到值栈
	 * <p>Title: toEdit</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String toEdit(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//添加条件
		criteria.add(Restrictions.eq("cust_id", customer.getCust_id()));
		//调用service
		List<Customer> list = customerService.findByCriteria(criteria);
		//放入值栈
		ActionContext.getContext().getValueStack().push(list.get(0));
		return "toEdit";
	}
	
	/**
	 * 更新用户
	 * <p>Title: update</p>
	 * <p>Description: </p>
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException{
		customerService.update(customer,upload,uploadFileName);
		return "toListAction";
	}
	
	/**
	 * 删除客户
	 * <p>Title: delete</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String delete(){
		customerService.delete(customer);
		return "toListAction";
	}
	
	public void findAll() throws IOException{
		List<Customer> list = customerService.findAll();
		
		final List<String> paramsList = new ArrayList<String>();
		
		paramsList.add("linkMans");
		paramsList.add("saleVisits");
		
		PropertyPreFilter filter = new PropertyPreFilter() {
			/**
			 * 如果哪个属性不进行转换，返回false，正常转换，就返回true
			 */
			public boolean apply(JSONSerializer arg0, Object arg1, String arg2) {
				if(paramsList.contains(arg2)){
					// 现在正要把group属性转换成json，不想转换
					return false;
				}
				return true;
			}
		};
		String json = JSON.toJSONString(list,filter,SerializerFeature.DisableCircularReferenceDetect);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
	}
	
	/**
	 * 根据id查询用户
	 * <p>Title: findByCritera</p>
	 * <p>Description: </p>
	 * @throws IOException 
	 */
	public void findByCritera() throws IOException{
		 DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		 criteria.add(Restrictions.eq("cust_id", customer.getCust_id()));
		 List<Customer> list = customerService.findByCriteria(criteria);
		 System.out.println(list.toArray());
		 
		 
		if(list != null && list.size() > 0){
			 
			 final List<String> listParam = new ArrayList<>();
			 listParam.add("linkMans");
			 listParam.add("source");
			 listParam.add("industry");
			 listParam.add("level");
			 
			 //设置哪些不转
			 PropertyPreFilter preFilter = new PropertyPreFilter() {
				
				@Override
				public boolean apply(JSONSerializer arg0, Object arg1, String arg2) {
					if(listParam.contains(arg2)){
						return false;
					}
					return true;
				}
			};
			 
			 //转换成json
			 String json = JSON.toJSONString(list.get(0),preFilter);
			 ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			 ServletActionContext.getResponse().getWriter().write(json);
			 System.out.println(json);
		 }
	}
}
