package cn.itcast.salevisit.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.itcast.customer.domain.Customer;
import cn.itcast.linkman.domain.LinkMan;
import cn.itcast.user.domain.User;

/*
  `visit_id` varchar(32) NOT NULL,
  `visit_cust_id` bigint(32) DEFAULT NULL COMMENT '客户id',
  `visit_user_id` bigint(32) DEFAULT NULL COMMENT '业务员id',
  `visit_time` date DEFAULT NULL COMMENT '拜访时间',
  `visit_interviewee` varchar(32) DEFAULT NULL COMMENT '被拜访人',
  `visit_addr` varchar(128) DEFAULT NULL COMMENT '拜访地点',
  `visit_detail` varchar(256) DEFAULT NULL COMMENT '拜访详情',
  `visit_nexttime` date DEFAULT NULL COMMENT '下次拜访时间',
 */
public class SaleVisit {
	
	private String visit_id;
	private Date visit_time;
	private String visit_addr;//被拜访人的姓名
	private String visit_detail;
	private Date visit_nexttime;
	
	//配置外键
	private User user;
	private Customer customer;
	private LinkMan linkMan;
	
	//格式转换
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	public String getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(String visit_id) {
		this.visit_id = visit_id;
	}
	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}
	
	
	
	public String getVisit_addr() {
		return visit_addr;
	}
	public void setVisit_addr(String visit_addr) {
		this.visit_addr = visit_addr;
	}
	public LinkMan getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}
	public String getVisit_detail() {
		return visit_detail;
	}
	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}
	public void setVisit_nexttime(Date visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
	}
	
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public Date getVisit_time() {
		return visit_time;
	}
	public Date getVisit_nexttime() {
		return visit_nexttime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	//获取格式化后的日期
	public String getVisit_timeString(){
		if(visit_time != null){
			
			String format = sdf.format(visit_time);
			return format;
		}
		return "";
	}
	
	public String getVisit_nexttimeString(){
		if(visit_nexttime != null){
			
			String format = sdf.format(visit_nexttime);
			return format;
		}
		return "";
	}
}
