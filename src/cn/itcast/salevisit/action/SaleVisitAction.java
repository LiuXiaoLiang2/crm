package cn.itcast.salevisit.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.salevisit.domain.SaleVisit;
import cn.itcast.salevisit.service.SaleVisitService;
import cn.itcast.user.domain.User;
import cn.itcast.utils.PageBean;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{

	private SaleVisit saleVisit = new SaleVisit();
	public SaleVisit getModel() {
		return saleVisit;
	}

	private SaleVisitService saleVisitService;
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	
	/**
	 * 分页显示用户拜访表
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @return
	 */
	private Integer currentPage = 1;
	private Integer pageSize = 3;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页显示客户拜访表
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @return
	 */
	private Date startTime;
	private Date endTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		if(startTime != null){
			String format = sdf.format(startTime);
			return format;
		}
		return "";
	}
	public String getEndTime() {
		if(endTime != null){
			String format = sdf.format(endTime);
			return format;
		}
		return "";
	}
	public String findByPage(){
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		
		//条件查询
		if(saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != 0){
			//添加条件
			criteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}
		if(startTime != null){
			 criteria.add(Restrictions.ge("visit_time", startTime));
		}
		if(endTime != null){
			 criteria.add(Restrictions.ge("visit_nexttime", endTime));
		}
		//创建分页bean对象
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		
		
		//调用Service
		pageBean = saleVisitService.findByPage(pageBean,criteria);
		//将pageBean放到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}
	
	/**
	 * 
	 * <p>Title: initSave</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String initSave(){
		return "initSave";
	}
	
	/**
	 * 添加客户拜访记录
	 * <p>Title: add</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String add(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(user);
		saleVisitService.add(saleVisit);
		return "toListAction";
	}
}
