package cn.itcast.dict.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dict.dao.DictDao;
import cn.itcast.dict.domain.Dict;

public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	public List<Dict> findDictByCriteria(DetachedCriteria criteria) {
		List<Dict> list = (List<Dict>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

}
