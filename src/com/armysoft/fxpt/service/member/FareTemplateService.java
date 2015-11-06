package com.armysoft.fxpt.service.member;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.armysoft.core.Pagination;
import org.armysoft.ibatis.dao.BaseDao;
import org.armysoft.security.model.sys.SysUser;
import org.armysoft.security.service.sys.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.CdInformation;
import com.armysoft.fxpt.model.CdSmallclass;
import com.armysoft.fxpt.model.FareCarryMode;
import com.armysoft.fxpt.model.FareTemplate;
import com.armysoft.fxpt.model.Orders;



@Service
public class FareTemplateService extends BaseDao {

	private final String nameSpace = "FareTemplateOpt";
	@Resource
	private FareCarryModeService fareCarryModeService;

	/**
	 * 所有订单
	 * @param params
	 * @param pager
	 * @return
	 */
	public List<Map<String, Object>> getByPage(Map<String, Object> params, Pagination pager) {
		return super.getPageList(nameSpace, params, pager);
	}
	
	public void batchInsert(HttpServletRequest request, FareTemplate fareTemplate,String mailCheckbox,String expressCheckbox,String emsCheckbox) {
		super.defInsert(nameSpace, fareTemplate);
		  List<FareCarryMode> list=new ArrayList<FareCarryMode>();
			
		  //平邮运费方式
		  if(mailCheckbox!=null&&mailCheckbox.equals("on")){
			  List<FareCarryMode>  mailModelList= fareCarryModeService.buildModelList(request, "mail", fareTemplate.getId());
			  if(mailModelList!=null&&mailModelList.size()>0)
				  list.addAll(mailModelList);
		  }
		  //快递运费方式
		  if(expressCheckbox!=null&&expressCheckbox.equals("on")){
			  List<FareCarryMode>  expressModelList= fareCarryModeService.buildModelList(request, "express", fareTemplate.getId());
			  if(expressModelList!=null&&expressModelList.size()>0)
				  list.addAll(expressModelList);
		  }
		  //EMS运费方式
		  if(emsCheckbox!=null&&emsCheckbox.equals("on")){
			  List<FareCarryMode>  emsModelList= fareCarryModeService.buildModelList(request, "express", fareTemplate.getId());
			  if(emsModelList!=null&&emsModelList.size()>0)
				  list.addAll(emsModelList);
		  }
		fareCarryModeService.batchInsert(list);
	}

	public Map<String, Object> findByKey(Long id) {
		return super.nativeSelectOne(nameSpace + ".findById", id);
	}
	/**
	 * 添加数据
	 * @param question
	 */
	public void insert(FareTemplate model) {
		super.defInsert(nameSpace, model);
	}
	
	public void delete(Long id) {
		super.defDelete(nameSpace, id);
	}
}
