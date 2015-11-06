package com.armysoft.fxpt.service.member;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
public class FareCarryModeService extends BaseDao {

	private final String nameSpace = "FareCarryModeOpt";

	@SuppressWarnings("unchecked")
	public List<FareCarryMode> buildModelList(HttpServletRequest request,String carryString,Integer templateId) {
		Integer tempCarryWay=carryString.equals("email")?1:(carryString.equals("express")?2:3);
		  Enumeration<String> paraNames=request.getParameterNames();
		  Integer valuationModel=Integer.parseInt(request.getParameter("valuationModel").toString());
		  Float mailDefalutFirst=Float.parseFloat(request.getParameter(carryString+"DefalutFirst").toString());
		  Float mailDefalutSecond=Float.parseFloat(request.getParameter(carryString+"DefalutSecond").toString());
		  Float mailDefalutFirstAmount=Float.parseFloat(request.getParameter(carryString+"DefalutFirstAmount").toString());
		  Float mailDefalutSecondAmount=Float.parseFloat(request.getParameter(carryString+"DefalutSecondAmount").toString());
		  //默认运费
		  FareCarryMode fc=new FareCarryMode(templateId,"全国",valuationModel,mailDefalutFirst,
				  mailDefalutSecond,mailDefalutFirstAmount,
				  mailDefalutSecondAmount,1,1);
		  //指定地区运费
		  List<FareCarryMode> list=new ArrayList<FareCarryMode>();
		  for(Enumeration e=paraNames;e.hasMoreElements();){
		         String thisName=e.nextElement().toString();
		         if(thisName.startsWith(carryString+"Region")){
		        	 String index=thisName.replace(carryString+"Region", "");
		        	 String tempRegion=request.getParameter(thisName).toString();
		        	 Float tempMailFirst=Float.parseFloat(request.getParameter(carryString+"First"+index).toString());
		        	 Float tempMailSecond=Float.parseFloat(request.getParameter(carryString+"Second"+index).toString());
		        	 Float tempMailFirstAmount=Float.parseFloat(request.getParameter(carryString+"FirstAmount"+index).toString());
		        	 Float tempMailSecondAmount=Float.parseFloat(request.getParameter(carryString+"SecondAmount"+index).toString());
		        	 FareCarryMode tempfc=new FareCarryMode(templateId,tempRegion,valuationModel,tempMailFirst,
		        			 tempMailSecond,tempMailFirstAmount,
		        			 tempMailSecondAmount,tempCarryWay,2);
		        	 list.add(tempfc);
		         }
		  }
		  list.add(fc);
		return list;
	}
	
	/**
	 * 所有订单
	 * @param params
	 * @param pager
	 * @return
	 */
	public List<Map<String, Object>> getByPage(Map<String, Object> params, Pagination pager) {
		return super.getPageList(nameSpace, params, pager);
	}
	
	public void batchInsert( List<FareCarryMode> list) {
		super.nativeInsert(nameSpace+".batchInsert", list);
		
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
