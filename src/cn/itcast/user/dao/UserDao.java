package cn.itcast.user.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.user.domain.User;

public interface UserDao {

	User getUserByUserCode(String user_code);

	void addUser(User user);

	User getUserByUserCodeAndPassword(User user);
	
	List<User> findUserByCriteria(DetachedCriteria criteria);
	

}
