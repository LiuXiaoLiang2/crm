package cn.itcast.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.user.domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//判断session中是否有user对象
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user == null){
			//说明用户没有登陆
			ActionContext.getContext().getValueStack().set("error", "您还没有登陆");
			return "login";
		}
		//直接放行至下一个拦截器
		return invocation.invoke();
	}



}
