package cn.itcast.linkman.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.customer.domain.Customer;
import cn.itcast.linkman.domain.LinkMan;
import cn.itcast.linkman.service.LinkManService;
import cn.itcast.utils.PageBean;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}

	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	/**
	 * 跳转至add.jsp
	 * <p>Title: initAdd</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String initAdd(){
		return "toAdd";
	}
	
	/**
	 * 添加用户
	 * <p>Title: add</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String add(){
		linkManService.save(linkMan);
		return "success";
	}
	
	/**
	 * 查询满足要求的联系人（根据cust_id）
	 * <p>Title: findByCustId</p>
	 * <p>Description: </p>
	 * @return
	 * @throws IOException 
	 */
	public void findByCustId() throws IOException{
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		//添加条件
		criteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		//调用Service查询
		List<LinkMan> list = linkManService.findByCriteria(criteria);
		
		//指定哪个属性不再转换
		PropertyPreFilter propertyPreFilter = new PropertyPreFilter() {
			
			@Override
			public boolean apply(JSONSerializer arg0, Object arg1, String arg2) {
				if("customer".equals(arg2)){
					return false;
				}
				return true;
			}
		};
		String json = JSON.toJSONString(list, propertyPreFilter);
		//System.out.println(json);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
	}
	
	
	//封装一些属性
	private Integer currentPage = 1;//默认值是第一页
	private Integer pageSize = 3;//默认显示3页
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页显示联系人
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		//添加条件查询
		if(linkMan.getLkm_name() != null && !"".equals(linkMan.getLkm_name().trim())){
			//说明需要条件分页查询
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		
		if(linkMan.getCustomer() != null && linkMan.getCustomer().getCust_id() !=  0L){
			//说明需要根据用户查询
			criteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		
		//----------------
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		pageBean = linkManService.findByPage(pageBean,criteria);
		//将pageBean放到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}
	
	public String toEdit(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		criteria.add(Restrictions.eq("lkm_id", linkMan.getLkm_id()));
		List<LinkMan> list = linkManService.findByCriteria(criteria);
		if(list != null && list.size() > 0){
			//放入值栈
			ActionContext.getContext().getValueStack().push(list.get(0));
		}
		return "toEdit";
	}
	
	public String update(){
		//更新的时候，需要传递id，因为修改语句，需要id
		linkManService.update(linkMan);
		return "toListAction";
	}
}
