package cn.itcast.salevisit.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.salevisit.domain.SaleVisit;
import cn.itcast.utils.PageBean;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(PageBean<SaleVisit> pageBean, DetachedCriteria criteria);

	void add(SaleVisit saleVisit);

}
