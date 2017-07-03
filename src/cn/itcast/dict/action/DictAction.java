package cn.itcast.dict.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.DISCARDING;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.dict.domain.Dict;
import cn.itcast.dict.service.DictService;
import net.sf.json.JSONArray;

public class DictAction extends ActionSupport implements ModelDriven<Dict>{

	private Dict dict = new Dict();
	public Dict getModel() {
		return dict;
	}
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
	public String init() throws IOException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Dict.class);
		criteria.add(Restrictions.eq("dict_type_code", dict.getDict_type_code()));
		List<Dict> list = dictService.findDictByCriteria(criteria);
		JSONArray jsonArray = JSONArray.fromObject(list);
		String json = jsonArray.toString();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}
}
