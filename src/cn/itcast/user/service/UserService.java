package cn.itcast.user.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.user.domain.User;

public interface UserService {

	Boolean validateUserCode(String user_code);

	void regist(User user);

	User login(User user);

	List<User> findUserByCriteria(DetachedCriteria criteria);
}
