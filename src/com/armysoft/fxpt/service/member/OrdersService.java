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

import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.CdInformation;
import com.armysoft.fxpt.model.CdSmallclass;
import com.armysoft.fxpt.model.Orders;


/***
 * 
 * 
 * 项目名称：Jpzx
 * 类名称：MemberBasic
 * 类描述：
 * 创建人：Java autoCoder 
 * 创建时间：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 *
 */
@Service
public class OrdersService extends BaseDao {

	private final String nameSpace = "OrdersOpt";


	/**
	 * 所有订单
	 * @param params
	 * @param pager
	 * @return
	 */
	public List<Map<String, Object>> getByPage(Map<String, Object> params, Pagination pager) {
		return super.getPageList(nameSpace, params, pager);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Integer getCount(Map<String, Object> params){
		return super.nativeSelectOne(nameSpace + ".sumCount", params);
	}
	public Map<String, Object> findByKey(Long id) {
		return super.nativeSelectOne(nameSpace + ".findById", id);
	}
	public Map<String, Object> findByOrderId(String orderId) {
		return super.nativeSelectOne(nameSpace + ".findByOrderId", orderId);
	}


	/**
	 * 添加数据
	 * @param question
	 */
	public void insert(Orders model) {
		super.defInsert(nameSpace, model);
	}
	public void insert2(Orders model) {
		super.nativeInsert(nameSpace+".insert2", model);
	}
	/**
	 * 添加数据
	 * @param question
	 */

	/**
	 * 批量添加
	 * @param quests
	 */

	/**
	 * 修改
	 * @param question
	 */
	public void updateByOrderId(String out_trade_no,String randCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", out_trade_no);
		params.put("randCode", randCode);
		super.nativeUpdate(nameSpace+".updateByOrderId", params);
		//super.defUpdate(nameSpace, model);
	}
	
	

	/**
	 * 删除
	 * @param id
	 */
	public void updateTag(Map<String, Object> params) {
		super.nativeUpdate(nameSpace+".updateTag", params);
	}

	public void delete(Long id) {
		super.defDelete(nameSpace, id);
	}
}
