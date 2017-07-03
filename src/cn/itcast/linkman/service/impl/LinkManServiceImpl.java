package cn.itcast.linkman.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.linkman.dao.LinkManDao;
import cn.itcast.linkman.domain.LinkMan;
import cn.itcast.linkman.service.LinkManService;
import cn.itcast.utils.PageBean;

@Transactional
public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	/**
	 * 添加用户
	 * <p>Title: save</p>
	 * <p>Description: </p>
	 * @param linkMan
	 * @see cn.itcast.linkman.service.LinkManService#save(cn.itcast.linkman.domain.LinkMan)
	 */
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public List<LinkMan> findByCriteria(DetachedCriteria criteria) {
		return linkManDao.findByCriteria(criteria);
	}

	@Override
	public PageBean<LinkMan> findByPage(PageBean<LinkMan> pageBean, DetachedCriteria criteria) {
		return linkManDao.findByPage(pageBean, criteria);
	}

	/**
	 * 更新用户
	 * <p>Title: update</p>
	 * <p>Description: </p>
	 * @param linkMan
	 * @see cn.itcast.linkman.service.LinkManService#update(cn.itcast.linkman.domain.LinkMan)
	 */
	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}
}
