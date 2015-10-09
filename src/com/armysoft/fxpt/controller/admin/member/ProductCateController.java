package com.armysoft.fxpt.controller.admin.member;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import org.json.JSONException;
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


import com.armysoft.fxpt.base.util.BjuiJson;
import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.MbMember;
import com.armysoft.fxpt.model.ProductCate;
import com.armysoft.fxpt.service.member.ProductCateService;





@Controller
@RequestMapping("admin/productCate")
public class  ProductCateController extends BaseController {

	@Resource
	private ProductCateService service;
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  
	/**
	 * 鏉′欢鍒嗛〉鏌ヨ
	 * @param currentPage
	 * @param model
	 * @param entity
	 * @param request
	 * @return
	 */
    @RequestMapping(value = PAGE_LIST)
	public String getByPage(@PathVariable Integer currentPage,String fzt,String fsfjjyb,String fhymc,String frysjf,String frysje, String fhtqxf,String fhtqxe,String cyqy,String hylbNo,String hyzcNo,String ssq,String fzjgNo,Model model,
			ProductCate entity, HttpServletRequest request) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	List list=service.getProductCate(params);
    	String zNodes = "";
        for(int i=0;i<list.size();i++){
        	ProductCate ProductCate=(ProductCate) list.get(i);
        	zNodes = zNodes + "<li data-id='" + ProductCate.getId() + "' data-pid='" + ProductCate.getFatherid() + "' data-tabid='" + ProductCate.getId() + "'>" + ProductCate.getName() + "[ID:" + ProductCate.getId() + "]</li>";
        }
    	request.setAttribute("zNodes", zNodes);
    	request.setAttribute("list", list);
		return "admin/member/ProductCateList";
	}

	/**
	 * 璇︽儏/鍑嗗淇敼
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = DETAIL)
	public String detail(@PathVariable("id") Long key, Model model,HttpServletRequest request) {
		model.addAttribute("model", service.findByKey(key));
		return "admin/member/ProductCateV";
	}

	/**
	 * 鍑嗗娣诲姞
	 * @return
	 */

	@RequestMapping(value = ADD)
	public String toAdd(Long id,HttpServletRequest request,Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		List list=service.getProductCate(params);
		String zNodes = "<li data-id='0' data-pid='0' data-tabid='0'>顶级分类</li>";

		 for(int i=0;i<list.size();i++){
		 	ProductCate ProductCate=(ProductCate) list.get(i);
		 	zNodes = zNodes + "<li data-id='" + ProductCate.getId() + "' data-pid='" + ProductCate.getFatherid() + "' data-tabid='" + ProductCate.getId() + "'>" + ProductCate.getName() + "</li>";
		 }
		 ProductCate mb=new ProductCate();
		 model.addAttribute("model", mb);
		 request.setAttribute("zNodes", zNodes);
		return "admin/member/ProductCateAdd";
	}
	

	/**
	 * 淇濆瓨
	 * @param entity
	 * @param model
	 * @return
	 */
	 @RequestMapping("ProductCateEdit.html")
	public String update(ProductCate entity, Model model, HttpServletRequest request) {
		 String key=request.getParameter("id");
		 ProductCate findProductCate = service.findByKey(Long.valueOf(key));
		 String zNodes="";
		 if (findProductCate != null)
		  {
			 
		 
		    Map<String, Object> params = new HashMap<String, Object>(); 
		    List list = service.getProductCate(params);
		    zNodes = "<li data-id='0' data-pid='0' data-tabid='0'>顶级分类</li>";
		 
		 for(int i=0;i<list.size();i++){
		 	ProductCate ProductCate=(ProductCate) list.get(i);
		 	 zNodes = zNodes + "<li data-id='" + ProductCate.getId() + "' data-pid='" + ProductCate.getFatherid() + "' data-tabid='" + ProductCate.getId() + "'>" + ProductCate.getName() + "</li>";
		 }
		  }
		 String fatherName = "";
		  if (findProductCate.getFatherid() != 0) {
		           ProductCate fatherProductCate = service.findByKey(findProductCate.getFatherid());
		           if (fatherProductCate != null)
		              fatherName = service.findByKey(findProductCate.getFatherid()).getName();
		             else
		            	 fatherName = "上级分类不存在";
		         }
		           else {
		               fatherName = "顶级分类";
		           }
		 
		  request.setAttribute("name",findProductCate.getName());
		  request.setAttribute("fatherid", findProductCate.getFatherid());
		  request.setAttribute("id", findProductCate.getId());
		  request.setAttribute("zNodes", zNodes);
		  request.setAttribute("fatherName", fatherName);
		return "admin/member/ProductCateEdit";
	}
	
	 @RequestMapping(value = SAVE)
		public void save(ProductCate entity, Model model, HttpServletRequest request,HttpServletResponse response) {
			String callbackData = "";
			entity.setDeleted("false");
			entity.setCreatedate(new Date());
			     service.insert(entity);
			     try {
			    	  callbackData = BjuiJson.json("200", "添加成功", "", "", "", "true", "", "");
			    	}
			    	catch (JSONException e) {
			    	e.printStackTrace();
			    	}
			    	PrintWriter out = null;
			    	try {
			    	response.setCharacterEncoding("UTF-8");
			    	out = response.getWriter();
			    	} catch (IOException e) {
			    	e.printStackTrace();
			    	}
			    	out.print(callbackData);
			    	out.flush();
			    	out.close();
		}
	 
	 
	 @RequestMapping("ProductCateUpdate.html")
	 public void update(ProductCate entity, Model model, HttpServletRequest request,HttpServletResponse response)
	   {
	    PrintWriter out = null;
	      try {
	       response.setCharacterEncoding("UTF-8");
	       out = response.getWriter();
	      } catch (IOException e) {
	     e.printStackTrace();
	     }
	  String callbackData = "";
	    try {
	   if (entity == null) {
	        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
	       }
	      else if (entity.getFatherid() == entity.getId()) {
	      callbackData = BjuiJson.json("300", "上级分类不能选择当前修改的分类", "", "", "", "", "", "");
	       } else {
	    	  
	         ProductCate findProductCate =  service.findByKey(entity.getId());
	         findProductCate.setFatherid(entity.getFatherid());
	         findProductCate.setName(entity.getName());
	         service.update(findProductCate);
	           callbackData = BjuiJson.json("200", "修改成功", "ProductCateList", "", "", "true", "", "");
	       
	      }
	      }
	    catch (JSONException e)
	     {
	       e.printStackTrace();
	    }
	    out.print(callbackData);
	    out.flush();
	    out.close();
	    }
	/**
	 * 鍒犻櫎
	 * @param key
	 * @return
	 */
	@RequestMapping("ProductCateDelete.html")
	public void delete(ProductCate entity, Model model, HttpServletRequest request,HttpServletResponse response) {
	     String idStr = request.getParameter("id");
		 String callbackData = "";
		      PrintWriter out = null;
		     try {
		    	 response.setCharacterEncoding("UTF-8");
		        out = response.getWriter();
		
		       if ((idStr == null) || ("".equals(idStr))) {
		        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
		       } else {
		       int id = 0;
		      try {
		       id = Integer.parseInt(idStr);
		     }
		    catch (Exception e) {
		         callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
		        }                                	
		      ProductCate findProductCate = service.findByKey(Long.valueOf(idStr));
		       if (findProductCate == null)
		       {
		     callbackData = BjuiJson.json("300", "分类不存在", "", "", "", "", "", "");
		      }
		       else {
		       List sanList = service.listByFatherId(Long.valueOf(id));
		           if (sanList.size() != 0) {
		             callbackData = BjuiJson.json("300", "该分类存在下级分类，请先删除下级分类", "", "", "", "", "", "");
		           } else {
		         service.delete(findProductCate.getId());
		           callbackData = BjuiJson.json("200", "删除成功", "ProductCateList", "", "", "true", "", "");
		           }
		       }
		      }
		     }
		  catch (IOException e) {
		  e.printStackTrace();
		  } catch (JSONException e) {
		      e.printStackTrace();
		  }
		  out.print(callbackData);
		    out.flush();
		    out.close();
		  }
	}
	
	


