package cn.itcast.salevisit.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.salevisit.dao.SaleVisitDao;
import cn.itcast.salevisit.domain.SaleVisit;
import cn.itcast.salevisit.service.SaleVisitService;
import cn.itcast.utils.PageBean;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	private SaleVisitDao saleVisitDao;
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}
	
	/**
	 * 分页查询用户拜访表
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param pageBean
	 * @param criteria
	 * @return
	 * @see cn.itcast.salevisit.service.SaleVisitService#findByPage(cn.itcast.utils.PageBean, org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public PageBean<SaleVisit> findByPage(PageBean<SaleVisit> pageBean, DetachedCriteria criteria) {
		pageBean = saleVisitDao.findByPage(pageBean, criteria);
		return pageBean;
	}

	/**
	 * 添加用户
	 * <p>Title: add</p>
	 * <p>Description: </p>
	 * @param saleVisit
	 * @see cn.itcast.salevisit.service.SaleVisitService#add(cn.itcast.salevisit.domain.SaleVisit)
	 */
	@Override
	public void add(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}
	
}
