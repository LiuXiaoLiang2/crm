package cn.itcast.utils;
//分页实体类

import java.util.ArrayList;
import java.util.List;

public class PageBean <T> {

	//当前页
	private Integer currentPage;
	//总数量
	private Integer totalCount;
	//每页显示的数量
	private Integer pageSize;
	//总页数
	private Integer totalPage;
	//每页对应的对象集合
	List<T> list = new ArrayList<T>();
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		System.out.println(this);
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		System.out.println(this);
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		System.out.println(this);
		this.pageSize = pageSize;
	}
	
	
	public Integer getTotalPage() {
		
		if(totalCount % pageSize == 0){
			//如果能被整除
			return totalCount/pageSize;
		} else {
			return (totalCount/pageSize)+1;
		}
	}
	//注意totalPage是计算出来的,并不需要set方法
	/*public void setTotaPagel(Integer totaPage) {
		this.totaPagel = totaPage;
	}*/
	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
