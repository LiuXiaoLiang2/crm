package cn.itcast.dict.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dict.dao.DictDao;
import cn.itcast.dict.domain.Dict;
import cn.itcast.dict.service.DictService;
@Transactional
public class DictServiceImpl implements DictService {

	private DictDao dictDao;
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}
	@Override
	public List<Dict> findDictByCriteria(DetachedCriteria criteria) {
		return dictDao.findDictByCriteria(criteria);
	}
	
}
