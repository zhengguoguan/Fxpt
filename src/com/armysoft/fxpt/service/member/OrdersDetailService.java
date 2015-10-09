package com.armysoft.fxpt.service.member;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.armysoft.core.Pagination;
import org.armysoft.ibatis.dao.BaseDao;
import org.armysoft.security.model.sys.SysUser;
import org.armysoft.security.service.sys.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.armysoft.fxpt.model.Cars;
import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.CdInformation;
import com.armysoft.fxpt.model.CdSmallclass;
import com.armysoft.fxpt.model.Orders;



@Service
public class OrdersDetailService extends BaseDao {
	@Resource
	private OrdersService ordersService;
	@Resource
	private CarsService carsService;
	
	private final String nameSpace = "OrdersDetailOpt";


	
	/**
	 * 批量添加数据
	 * @param question
	 */
	public void batchInsert(List<Map<String, Object>> list,Orders model) {
		if(list==null||list.size()<=0)
			return;
		for(Map<String, Object> m:list){
			m.put("orderId", model.getOrderId());
		}
		if(model.getPayType()==0){
			 ordersService.insert(model);
		}else if(model.getPayType()==1){
			 ordersService.insert2(model);
		}
		
		super.nativeInsert(nameSpace+".batchInsert", list);
		//同时删除相应的购物车记录
		carsService.deleteByOpenId(model.getOpenId());
		//super.defInsert(nameSpace, model);
	}
	public List<Map<String, Object>> getByPage(Map<String, Object> params, Pagination pager) {
		return super.getPageList(nameSpace, params, pager);
	}
	
	public List<Map<String, Object>> findAllByOrderId(String orderId) {
		
		return super.nativeList(nameSpace+".findAllByOrderId", orderId);
	}
}
