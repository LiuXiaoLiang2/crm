package cn.itcast.user.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;

/**
 * 用户持久层
 * @author Administrator
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * 根据登录名获取User对象
	 * <p>Title: getUserByUserCode</p>
	 * <p>Description: </p>
	 * @param user_code
	 * @return
	 * @see cn.itcast.user.dao.UserDao#getUserByUserCode(java.lang.String)
	 */
	public User getUserByUserCode(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=?", user_code);
		if(list.size() != 0 ){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 添加用户
	 * <p>Title: addUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @see cn.itcast.user.dao.UserDao#addUser(cn.itcast.user.domain.User)
	 */
	public void addUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User getUserByUserCodeAndPassword(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size() != 0 ){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 使用离线查询对象,便于扩展
	 * <p>Title: findUserByCriteria</p>
	 * <p>Description: </p>
	 * @param criteria
	 * @return
	 * @see cn.itcast.user.dao.UserDao#findUserByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */

	@Override
	public List<User> findUserByCriteria(DetachedCriteria criteria) {
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

}
