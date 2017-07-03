package cn.itcast.dict.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dict.domain.Dict;

public interface DictDao {

	List<Dict> findDictByCriteria(DetachedCriteria criteria);

}
