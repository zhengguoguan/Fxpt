package com.armysoft.fxpt.controller.admin.news;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.armysoft.core.Pagination;
import org.armysoft.security.annotation.PermissionsAnno;
import org.armysoft.springmvc.controller.BaseController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.armysoft.fxpt.base.common.Constants;
import com.armysoft.fxpt.model.MbMember;
import com.armysoft.fxpt.model.News;
import com.armysoft.fxpt.service.member.NewsService;




@Controller
@RequestMapping("/admin/news")
public class  NewsController extends BaseController {
//
	@Resource
	private NewsService newsService;
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  
	/**
	 * ������ҳ��ѯ
	 * @param currentPage
	 * @param model
	 * @param entity
	 * @param request
	 * @return
	 */
	@PermissionsAnno("news_list") 
    @RequestMapping(value = PAGE_LIST)
	public String getByPage(@PathVariable Integer currentPage,String cateCode,Model model, HttpServletRequest request) {
		if(cateCode.startsWith("park_")||cateCode.equals("contact_us")||cateCode.startsWith("service_inpark_")||cateCode.startsWith("service_outpark_")){
			model.addAttribute("cateCode", cateCode);			
			 model.addAttribute("entity", newsService.getByCateCode(cateCode));
			 model.addAttribute("category", newsService.getCategory(cateCode));
			return "/admin/news/newsA_U_D";
		}
		Pagination pager = initPage(currentPage);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cateCode", cateCode);
		model.addAttribute("cateCode", cateCode);
        model.addAttribute("list", newsService.getByPage(params, pager));
        model.addAttribute("category", newsService.getCategory(cateCode));
		model.addAttribute("page", pager);

		return "/admin/news/newsQ";
	}
	@RequestMapping("about.html")
	public String about(Model model,HttpServletRequest request) throws ParseException {
		model.addAttribute("entity", newsService.getByCateCode("park_intro"));
		return "/admin/news/about";
      }
	
	
	//@PermissionsAnno("news_add")
	@RequestMapping(value = ADD)
	public String toAdd(HttpServletRequest request,Model model,String cateCode) {
		model.addAttribute("type", "add");
		model.addAttribute("cateCode", cateCode);
		 model.addAttribute("category", newsService.getCategory(cateCode));
		return "/admin/news/newsA_U_D";
	}
	
	
	/**
	 * ����
	 * @param entity
	 * @param model
	 * @return
	 */

	@RequestMapping(value = UPDATE)
	public String update(@PathVariable("id") Long key,Model model,String cateCode) {
		model.addAttribute("cateCode", cateCode);
		model.addAttribute("entity", newsService.findByKey(key));
		 model.addAttribute("category", newsService.getCategory(cateCode));
		model.addAttribute("type", "update");
		return "/admin/news/newsA_U_D";
	}
	@RequestMapping(value = DETAIL)
	public String detail(@PathVariable("id") Long key,Model model,String cateCode) {
		model.addAttribute("cateCode", cateCode);
		Map<String, Object> entity=newsService.findByKey(key);
		model.addAttribute("entity", entity);
		if(entity.get("filePath")!=null&&cateCode.equals("train_file")){
			String fileName=entity.get("filePath").toString().replace("/userfiles/trainFile/", "");
			model.addAttribute("filePath",fileName.substring(fileName.indexOf("_")+1, fileName.length()));
		}
		 model.addAttribute("category", newsService.getCategory(cateCode));
		model.addAttribute("type", "detail");
		
		return "/admin/news/newsA_U_D";
	}
	//@PermissionsAnno("news_save")
	@RequestMapping(value = SAVE)
	public String save(HttpServletRequest request,News entity, Model model,String cateCode,String type,String flag) {
		if(flag!=null&&flag.equals("1")){
			//上传附件
			  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			  MultipartFile file = multipartRequest.getFile("file");  
			  if (!file.isEmpty()) {
				  String filePath="/userfiles/trainFile/"+new Date().getTime()+"_"+file.getOriginalFilename();
				  entity.setFilePath(filePath);
				  try {
						file.transferTo(new File(request.getSession().getServletContext().getRealPath(filePath)));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }
		}
		
		String key = super.getCookieValue(request,Constants.ADMIN_KEY);
		entity.setCreateUser(key);
		if(cateCode.startsWith("park_")||cateCode.equals("contact_us")||cateCode.startsWith("service_inpark_")||cateCode.startsWith("service_outpark_")){
			Map<String, Object> e=newsService.getByCateCode(cateCode);
			if(e!=null){
				newsService.update(entity);	
			}else{
				newsService.insert(entity);
			}
			model.addAttribute("msg", "保存成功");
			model.addAttribute("cateCode", cateCode);			
			 model.addAttribute("entity", newsService.getByCateCode(cateCode));
			 model.addAttribute("category", newsService.getCategory(cateCode));
			return "/admin/news/newsA_U_D";
		}
		
		if(type.equalsIgnoreCase("add")){
			newsService.insert(entity);
		}else if(type.equalsIgnoreCase("update")){
			newsService.update(entity);	
		}
		return "redirect:/admin/news/list/1.html?cateCode="+cateCode;
	}
	 @RequestMapping(value = "/downLoad/{id}.html")
	  public void downLoad(HttpServletResponse response,HttpServletRequest request,@PathVariable("id") Long id)
	  {
		  try
		    {
			  Map<String, Object> news=newsService.findByKey(id);
		      response.setContentType("application/x-download");
		      String realPath = request.getSession().getServletContext().getRealPath(news.get("filePath").toString());
		     String fileName=news.get("filePath").toString().substring(news.get("filePath").toString().lastIndexOf("/")+1);
		     // realPath = URLEncoder.encode(realPath, "UTF-8");
		      response.addHeader("Content-Disposition", "attachment;filename=" +fileName );
		      OutputStream out = response.getOutputStream();
		      InputStream inputStream = new FileInputStream(realPath);
		      byte[] buffer = new byte[1024];
		      int i = -1;
		      while ((i = inputStream.read(buffer)) != -1) {
		       out.write(buffer, 0, i);
		       }
		      out.flush();
		      out.close();
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }

	    
	  }
	/**
	 * ɾ��
	 * @param key
	 * @return
	 */

	@RequestMapping(value = DELETE)
	public String delete(@PathVariable("id") Long key,Model model,String cateCode) {
		newsService.delete(key);
		//model.addAttribute("cateCode", cateCode);
		return "redirect:/admin/news/list/1.html?cateCode="+cateCode;
	}
	
	


}
