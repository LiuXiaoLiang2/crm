package cn.itcast.dict.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dict.domain.Dict;

public interface DictService {

	List<Dict> findDictByCriteria(DetachedCriteria criteria);

}
