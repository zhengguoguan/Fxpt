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

import com.armysoft.fxpt.model.Address;
import com.armysoft.fxpt.model.Cars;
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
public class AddressService extends BaseDao {

	private final String nameSpace = "AddressOpt";


	/**
	 * 条件分页查询题库
	 * @param params
	 * @param pager
	 * @return
	 */
	public List<Address> getByPage(Map<String, Object> params, Pagination pager) {
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
	public Address findByKey(Long id) {
		return super.nativeSelectOne(nameSpace + ".findById", id);
	}
	
	public Address findByCheckOd(String openid) {
		return super.nativeSelectOne(nameSpace + ".findByCheckOd", openid);
	}
	
	public List<Address> findByOd(String openid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openid", openid);
		return super.nativeList(nameSpace + ".findByOd", params);
	}
	/**
	 * 添加数据
	 * @param question
	 */
	public void insert(Address model) {
		super.defInsert(nameSpace, model);
	}
	
	
	public void update(Address model) {

		super.defUpdate(nameSpace, model);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id) {
		super.defDelete(nameSpace, id);
	}

}
