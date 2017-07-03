package cn.itcast.user.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserService;
import cn.itcast.utils.MD5Utils;

/**
 * 用户的模块，现在采用都是配置文件，后面演示注解
 * @author Administrator
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 7888231414250333725L;
	
	private User user = new User();
	
	public User getModel() {
		return user;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 校验登陆名是否存在，最开始的方法，不便于后面抽取
	 * <p>Title: validate</p>
	 * <p>Description: </p>
	 * @return
	 * @throws IOException 
	 */
	public String validateUserCodeBefore() throws IOException{
		//调用service
		Boolean flag = userService.validateUserCode(user.getUser_code());
		if(flag){
			//说明登录名已经存在,不能注册
			//发送1表示不能注册
			ServletActionContext.getResponse().getWriter().write("1");
		} else {
			//发送0表示可以注册
			ServletActionContext.getResponse().getWriter().write("0");
		}
		return NONE;
	}
	/**
	 * 在action层创建离线查询对象,直接将离线查询对象传递到service层
	 * <p>Title: validateUserCode</p>
	 * <p>Description: </p>
	 * @return
	 * @throws IOException 
	 */
	public String validateUserCode() throws IOException{
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		//添加条件
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		//将离线对象传递给service层
		List<User> list = userService.findUserByCriteria(criteria);
		if(list != null && list.size() >0 ){
			//说明登录名已经存在,不能注册
			//发送1表示不能注册
			ServletActionContext.getResponse().getWriter().write("1");
		} else{
			ServletActionContext.getResponse().getWriter().write("0");
		}
		return NONE;
	}
	
	/**
	 * 注册功能
	 * <p>Title: regist</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String regist(){
		userService.regist(user);
		return "login";
	}
	
	/**
	 * 登陆功能
	 * <p>Title: login</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String loginBefore(){
		ActionContext actionContext = ActionContext.getContext();
		User userLogin = userService.login(user);
		if(userLogin!=null){
			//用户登陆成功
			//将对象放到session域中
			actionContext.getSession().put("user", userLogin);
			return "index";
		} else {
			//说明登陆失败
			//添加错误信息
			addFieldError("error", "用户名或密码错误");
			return "login";
		}
	}
	/**
	 * 使用离线对象,便于后续扩展
	 * <p>Title: login</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String login(){
		ActionContext actionContext = ActionContext.getContext();
		
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		//添加条件
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password",MD5Utils.md5(user.getUser_password())));
		//将离线对象传递到service层
		List<User> list = userService.findUserByCriteria(criteria);
		if(list != null && list.size() >0 ){
			//说明用户登陆成功
			//将user对象放到session域中
			actionContext.getSession().put("user", list.get(0));
			return "index";
		} else{
			//将错误信息放到值栈中
			actionContext.getValueStack().set("error", "用户名或密码错误");
			return "login";
		}
	}
	
	/**
	 * 用户注销
	 * <p>Title: logout</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
		return "login";
	}
}
