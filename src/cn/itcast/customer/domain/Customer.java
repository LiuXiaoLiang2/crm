package cn.itcast.customer.domain;

import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.dict.domain.Dict;
import cn.itcast.linkman.domain.LinkMan;
import cn.itcast.salevisit.domain.SaleVisit;

/*
 * 	
 *`cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_user_id` bigint(32) DEFAULT NULL COMMENT '负责人id',
  `cust_create_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `cust_linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
  */
public class Customer {
	private Long cust_id;
	private String cust_name;
	private String cust_user_id;
	private String cust_create_id;
	
	//于数据字典表构成多对一关系
	//也就是多个客户可能属于同一个行业级别，同一个客户级别，同一个信息来源
	//private String cust_source;
	//private String cust_industry;
	//private String cust_level;
	
	//既然数据字典表对应3个表.所以每个对象可以表示不同的含义
	private Dict source;//信息来源
	private Dict industry;//所属行业
	private Dict level;//客户级别
	
	private String cust_linkman;
	private String cust_phone;
	private String cust_mobile;
	
	//文件上传相关属性
	private String file_name;
	private String file_path;
	
	//Customer与LinkMan类之间的一对多关系
	Set<LinkMan> linkMans = new HashSet<LinkMan>();
	
	//配置外键,
	Set<SaleVisit> saleVisits = new HashSet<SaleVisit>();
	
	
	
	public Set<SaleVisit> getSaleVisits() {
		return saleVisits;
	}
	public void setSaleVisits(Set<SaleVisit> saleVisits) {
		this.saleVisits = saleVisits;
	}
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Dict getSource() {
		return source;
	}
	public void setSource(Dict source) {
		this.source = source;
	}
	public Dict getIndustry() {
		return industry;
	}
	public void setIndustry(Dict industry) {
		this.industry = industry;
	}
	public Dict getLevel() {
		return level;
	}
	public void setLevel(Dict level) {
		this.level = level;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(String cust_user_id) {
		this.cust_user_id = cust_user_id;
	}
	public String getCust_create_id() {
		return cust_create_id;
	}
	public void setCust_create_id(String cust_create_id) {
		this.cust_create_id = cust_create_id;
	}
	public String getCust_linkman() {
		return cust_linkman;
	}
	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_user_id=" + cust_user_id
				+ ", cust_create_id=" + cust_create_id + ", source=" + source + ", industry=" + industry + ", level="
				+ level + ", cust_linkman=" + cust_linkman + ", cust_phone=" + cust_phone + ", cust_mobile="
				+ cust_mobile + "]";
	}
}

