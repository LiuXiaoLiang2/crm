package cn.itcast.user.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserService;
import cn.itcast.utils.MD5Utils;

/**
 * 业务层
 * @author Administrator
 */
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 校验登录名是否存在
	 * <p>Title: validateUserCode</p>
	 * <p>Description: </p>
	 * @param user_code
	 * @return
	 * @see cn.itcast.user.service.UserService#validateUserCode(java.lang.String)
	 */
	public Boolean validateUserCode(String user_code) {
		User user = userDao.getUserByUserCode(user_code);
		if(user != null){
			return true;
		}
		return false;
	}

	/**
	 * 注册用户
	 * <p>Title: regist</p>
	 * <p>Description: </p>
	 * @param user
	 * @see cn.itcast.user.service.UserService#regist(cn.itcast.user.domain.User)
	 */
	public void regist(User user) {
		//密码加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		//封装数据
		user.setUser_state("1");//表示用户正常
		userDao.addUser(user);
	}

	/**
	 * 登陆
	 * <p>Title: login</p>
	 * <p>Description: </p>
	 * @param user
	 * @return
	 * @see cn.itcast.user.service.UserService#login(cn.itcast.user.domain.User)
	 */
	public User login(User user) {
		//MD5加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		User userLogin = userDao.getUserByUserCodeAndPassword(user);
		return userLogin;
	}

	/**
	 * 使用离线查询对象
	 * <p>Title: findUserByCriteria</p>
	 * <p>Description: </p>
	 * @param criteria
	 * @return
	 * @see cn.itcast.user.service.UserService#findUserByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<User> findUserByCriteria(DetachedCriteria criteria) {
		List<User> list = userDao.findUserByCriteria(criteria);
		return list;
	}

}
