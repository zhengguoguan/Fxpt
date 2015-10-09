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
import com.armysoft.fxpt.model.MbMember;
import com.armysoft.fxpt.model.ProductCate;


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
public class ProductCateService extends BaseDao {

	private final String nameSpace = "ProductCateOpt";


	/**
	 * 条件分页查询题库
	 * @param params
	 * @param pager
	 * @return
	 */
	public List<ProductCate> getByPage(Map<String, Object> params, Pagination pager) {
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
	public ProductCate findByKey(Long id) {
		return super.nativeSelectOne(nameSpace + ".findById", id);
	}
	public List<ProductCate> getProductCate(Map<String, Object> params) {
		return super.nativeList(nameSpace + ".getProductCate", params);
	}
	public List<ProductCate> listByFatherId(Long id) {
		return super.nativeList(nameSpace + ".listByFatherId", id);
	}
	
	/**
	 * 添加数据
	 * @param question
	 */
	public void insert(ProductCate model) {
		super.defInsert(nameSpace, model);
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
	public void update(ProductCate model) {

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
