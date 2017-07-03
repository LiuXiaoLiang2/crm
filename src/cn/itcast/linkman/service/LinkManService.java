package cn.itcast.linkman.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.linkman.domain.LinkMan;
import cn.itcast.utils.PageBean;

public interface LinkManService {

	void save(LinkMan linkMan);

	List<LinkMan> findByCriteria(DetachedCriteria criteria);

	PageBean<LinkMan> findByPage(PageBean<LinkMan> pageBean, DetachedCriteria criteria);

	void update(LinkMan linkMan);

}
