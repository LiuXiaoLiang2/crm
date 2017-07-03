package cn.itcast.user.domain;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.salevisit.domain.SaleVisit;

public class User {
	
	// 用户主键
	private Long user_id;
	// 登录名
	private String user_code;
	// 用户的名称
	private String user_name;
	// 用户的密码，加密
	private String user_password;
	// 用户的状态 '1:正常,0:暂停',
	private String user_state;
	
	//配置外键,
	Set<SaleVisit> saleVisits = new HashSet<SaleVisit>();
	
	
	
	public Set<SaleVisit> getSaleVisits() {
		return saleVisits;
	}
	public void setSaleVisits(Set<SaleVisit> saleVisits) {
		this.saleVisits = saleVisits;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
}
