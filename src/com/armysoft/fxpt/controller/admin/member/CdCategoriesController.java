package com.armysoft.fxpt.controller.admin.member;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.armysoft.core.Pagination;
import org.armysoft.springmvc.controller.BaseController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.armysoft.security.annotation.PermissionsAnno;

import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.service.member.CdCategoriesService;




@Controller
@RequestMapping("admin/cdCategories")
public class  CdCategoriesController extends BaseController {

	@Resource
	private CdCategoriesService service;
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  
	/**
	 * 条件分页查询
	 * @param currentPage
	 * @param model
	 * @param entity
	 * @param request
	 * @return
	 */
    @RequestMapping(value = PAGE_LIST)
	public String getByPage(@PathVariable Integer currentPage,String fzt,String fsfjjyb,String fhymc,String frysjf,String frysje, String fhtqxf,String fhtqxe,String cyqy,String hylbNo,String hyzcNo,String ssq,String fzjgNo,Model model,
			CdCategories entity, HttpServletRequest request) {
		Pagination pager = initPage(currentPage);
		pager.setPageSize(10);
		Map<String, Object> params = new HashMap<String, Object>();
		model.addAttribute("list", service.getByPage(params, pager));
		model.addAttribute("page", pager);
		model.addAttribute("model", entity);
		return "admin/member/CdCategoriesQ";
	}

	/**
	 * 详情/准备修改
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = DETAIL)
	public String detail(@PathVariable("id") Long key, Model model,HttpServletRequest request) {
		model.addAttribute("model", service.findByKey(key));
		return "admin/member/CdCategoriesV";
	}

	/**
	 * 准备添加
	 * @return
	 */

	@RequestMapping(value = ADD)
	public String toAdd(Long id,HttpServletRequest request,Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer countAll=service.getCount(params)+1;
		CdCategories mb=service.findByKey(id);
		if(mb!=null){
			model.addAttribute("model", mb);
		}else{
			mb=new CdCategories();
			DecimalFormat countFormat=new DecimalFormat("000");
			mb.setCategoriesdm("DL"+countFormat.format(countAll));
			model.addAttribute("model", mb);
		}
		return "admin/member/CdCategoriesV";
	}
	

	/**
	 * 保存
	 * @param entity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = UPDATE)
	public String update(@PathVariable("id") Integer key,CdCategories entity, Model model, HttpServletRequest request) {
		entity.setId(key);
		service.update(entity);
		return "redirect:/admin/cdCategories/list/1.html";
	}
	@RequestMapping(value = SAVE)
	public String save(CdCategories entity, Model model, HttpServletRequest request) {
		if (entity.getId() == null) {
		    service.insert(entity);
		} else {
			service.update(entity);
		}
		return "redirect:/admin/cdCategories/list/1.html";
	}
	
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	@RequestMapping(value = DELETE)
	public String delete(@PathVariable("id") Long key) {
		service.delete(key);
		return "redirect:/admin/cdCategories/list/1.html";
	}
	
	

}
