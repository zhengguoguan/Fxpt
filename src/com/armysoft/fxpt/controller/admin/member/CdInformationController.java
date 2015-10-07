package com.armysoft.fxpt.controller.admin.member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Case;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.armysoft.security.annotation.PermissionsAnno;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.armysoft.fxpt.base.util.CSVUtils;
import com.armysoft.fxpt.cache.AppCache;
import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.CdInformation;
import com.armysoft.fxpt.model.CdSmallclass;
import com.armysoft.fxpt.model.ProductCate;
import com.armysoft.fxpt.service.member.CdCategoriesService;
import com.armysoft.fxpt.service.member.CdInformationService;
import com.armysoft.fxpt.service.member.CdSmallclassService;
import com.armysoft.fxpt.service.member.ProductCateService;
import com.csvreader.CsvReader;






@Controller
@RequestMapping("admin/cdInformation")
public class  CdInformationController extends BaseController {

	@Resource
	private CdInformationService service;
	@Resource
	private CdCategoriesService dlservice;
	@Resource
	private CdSmallclassService xlservice;
	@Resource
	private ProductCateService pcservice;
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
	public String getByPage(@PathVariable Integer currentPage,String fcpbh,String fcdname,String fhymc,String frysjf,String frysje, String fhtqxf,String fhtqxe,String cyqy,String hylbNo,String hyzcNo,String ssq,String fzjgNo,Model model,
			CdCategories entity, HttpServletRequest request) {
		Pagination pager = initPage(currentPage);
		pager.setPageSize(10);
		Map<String, Object> params = new HashMap<String, Object>();
		if(fcpbh !="" && fcpbh!=null){
			params.put("fcpbh", fcpbh);
			request.setAttribute("fcpbh", fcpbh);
	    }
	    if(fcdname !="" && fcdname !=null){
				params.put("fcdname", fcdname);
				request.setAttribute("fcdname", fcdname);
		}
		model.addAttribute("list", service.getByPage(params, pager));
		model.addAttribute("page", pager);
		model.addAttribute("model", entity);
		return "admin/member/CdInformationQ";
	}
    
    
    @RequestMapping(value = "/Find.html")
	public String Find(HttpServletRequest request) {
		return "admin/member/CdInformationF";
	}
    public int getMyInt(int a,int b) {
    	return(((double)a/(double)b)>(a/b)?a/b+1:a/b);
    	}
    @RequestMapping(value = "/cdIndex.html")
	@ResponseBody
	public  String cdIndex(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	String idStr = request.getParameter("id");
    	String key = request.getParameter("key");
    	String pStr = request.getParameter("p");
    	int p = 1;
    	if (pStr!=null && pStr!="") {
    	      p = Integer.parseInt(pStr);
    	     }
    	 Pagination pager = initPage(p);
		 pager.setPageSize(10);
		 Map<String, Object> params = new HashMap<String, Object>();
		 if(idStr !="" && idStr!=null){
				params.put("idStr", idStr);
				request.setAttribute("idStr", idStr); 
		    }
		 if(key !="" && key!=null){
				params.put("key", key);
				request.setAttribute("key", key);
		    }
    	 List cdList= service.getByPages(params, pager);
		 JSONObject jsonObject = new JSONObject();
		int getPage=getMyInt(Integer.valueOf(cdList.size()),Integer.valueOf(10));
		System.out.println(getPage);
		  if (cdList.size() == 0)
    	      {
    	        	jsonObject.put("status", "0");
    	        	jsonObject.put("isNextPage", "0");
    	      }
    	      else {
    	    	  jsonObject.put("status", "1");
    	      if (getPage >= Integer.valueOf(pStr))
    	        {
    	    	  jsonObject.put("isNextPage", "1");
    	        }
    	        else {
    	        	jsonObject.put("isNextPage", "0");
    	        }
   	         JSONArray listJson = (JSONArray)JSONArray.toJSON(cdList);
    	     jsonObject.put("list", listJson);
    	      }
		 response.setContentType("text/html;charset=UTF-8");   
		 try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
    @RequestMapping(value = "/detail.html")
	public  String detail(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	String idStr =request.getParameter("id");
    	CdInformation cf=service.findByKey(Long.valueOf(idStr));
    	String[] cdpicture=cf.getCdpicture().split(",");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(cdpicture.length);
		for(int i=0;i<cdpicture.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cdpicture",  cdpicture[i]);
			list.add(map);
			}
    	System.out.println(cf.getCdpicturech());
    	request.setAttribute("Cdpicturech",list);
    	System.out.println(cf.getCdintroduction());
    	cf.setCdintroduction(cf.getCdintroduction().replace("<img", "<img width=80% " ).trim());
    	request.setAttribute("cf",cf);
    	return "index/detail";
	}

    @RequestMapping(value = "/Index.html")
	public  String Index(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	return "index/index";
	}
    
    
    @RequestMapping(value = "/inputExport.html")
	public String  OutPtqfqk(@RequestParam MultipartFile exlFile, HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException {
    	InputStream fis = exlFile.getInputStream();
    	 List<String> dataList=new ArrayList<String>();
    	  BufferedReader br=null;
    	  try {
           
          br = new BufferedReader(new InputStreamReader(fis,Charset.forName("Unicode")));
              
          br.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
          String line = "";
          String aline ="";
          while((line= br.readLine())!=null){
       	  if(!line.contains("o2o_bind_service") && !line.contains("关联线下服务")){
        			  aline=aline+line;
        	  }
           }
              String item[] = aline.split("\t");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
              System.out.println(item.length/62);
              int countSph=item.length/62;
              int countSpl=62;
              String[][] b=new String[countSph][countSpl];
              int num=-1;
              for(int i=0;i<countSph;i++){
            	  for(int j=0;j<countSpl;j++){
            		  num++;
            		  b[i][j]=item[num];
            	  }
              }
              for(int i=0;i<countSph;i++){
            	  CdInformation cf=new CdInformation();
            	  Map<String, Object> params = new HashMap<String, Object>();
            	  Integer countAll=service.getCount(params)+1;
            	  DecimalFormat countFormat=new DecimalFormat("0000000");
            	  cf.setCpbh("CP"+countFormat.format(countAll));
            	  for(int j=0;j<countSpl;j++){
            		  
            		  if(j==0){
            			  cf.setCdname(b[i][j].replace("\"", ""));
            		  }
            		  if(j==7){
            			  cf.setCdprice(b[i][j].replace("\"", ""));
            		  }
            		  if(j==20){
            			  System.out.println(b[i][j].replace("\"", ""));
            			  cf.setCdintroduction(b[i][j].replace("\"", ""));
            		  }
            		  if(j==28){
            			  String itemPic[] = b[i][j].substring(0, b[i][j].lastIndexOf(".jpg")).split("\\.jpg");
      					  String picUrl="";
      					  for(int p=0;p<itemPic.length;p++){
      					 picUrl=picUrl+itemPic[p].substring(itemPic[p].indexOf("|")+1, itemPic[p].length())+".jpg,";
      					if(p==0){
      						cf.setCdpicturech(itemPic[p].substring(itemPic[p].indexOf("|")+1, itemPic[p].length())+".jpg");
         			     }
      					 }
                          picUrl=picUrl.substring(0,picUrl.length()-1);
                          cf.setCdpicture(picUrl);
          			     
            		  }
            		  if(j==48){
            			  cf.setStockr(b[i][j].replace("\"", ""));
            		  }
            		  if(j==61){
            			  service.insert(cf);
            		  }
            		//  System.out.println(b[i][j]+"");
            	  }
            	  }
              //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
         } catch (Exception e) {
          e.printStackTrace();
      }

             request.setAttribute("exl", "ok");
	          return "admin/member/CdInformationF";
		
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
		return "admin/member/CdInformationV";
	}
    
	
	@RequestMapping(value = "/getCdsmallclass.html")
	@ResponseBody
	public  String getQyxx(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		String parm1 = URLDecoder.decode(request.getParameter("parm1"), "utf-8");
		List<CdSmallclass> cdSmallclassList=xlservice.getCdCategories(parm1);
		 JSONObject jsonObject = new JSONObject();
		 if(cdSmallclassList.get(0)!=null){
		jsonObject.put("cd_smallclass", cdSmallclassList);
		 }
		 response.setContentType("text/html;charset=UTF-8");   
		 try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * 准备添加
	 * @return
	 */

	@RequestMapping(value = ADD)
	public String toAdd(Long id,HttpServletRequest request,Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<CdCategories> cdCategoriesList=dlservice.getCdCategories(params);
		request.setAttribute("cd_categories",cdCategoriesList);
		for(int i=0;i<cdCategoriesList.size();i++)
		{
			CdCategories db=cdCategoriesList.get(i);
			List<CdSmallclass> cdSmallclassList=xlservice.getCdCategories(db.getCategoriesdm());
			request.setAttribute("cd_smallclass_"+db.getCategoriesdm(), cdSmallclassList);
		}
		Integer countAll=service.getCount(params)+1;
		CdInformation mb=service.findByKey(id);
		if(mb!=null){
			model.addAttribute("model", mb);
			if(mb.getCdpicturech()!=null){
			String[] cdpicture=mb.getCdpicturech().split(",");
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(cdpicture.length);
			// Build a map for the attributes
			
			for(int i=0;i<cdpicture.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cdpicture",  cdpicture[i]);
			list.add(map);
			}
			ProductCate productCate= pcservice.findByKey(Long.valueOf(mb.getCdcategories()));
			model.addAttribute("fatherName", productCate.getName());
			model.addAttribute("cdpicture", list);
			}
		}else{
			mb=new CdInformation();
			DecimalFormat countFormat=new DecimalFormat("0000000");
			mb.setCpbh("CP"+countFormat.format(countAll));
			model.addAttribute("model", mb);
		}
		
		Map<String, Object> params1 = new HashMap<String, Object>();
		List list=pcservice.getProductCate(params1);
		String zNodes = "";

		 for(int i=0;i<list.size();i++){
		 	ProductCate ProductCate=(ProductCate) list.get(i);
		 	zNodes = zNodes + "<li data-id='" + ProductCate.getId() + "' data-pid='" + ProductCate.getFatherid() + "' data-tabid='" + ProductCate.getId() + "'>" + ProductCate.getName() + "</li>";
		 }
		 request.setAttribute("zNodes", zNodes);
		return "admin/member/CdInformationV";
	}
	

	/**
	 * 保存
	 * @param entity
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = UPDATE)
	public String update(@PathVariable("id") Integer key,CdInformation entity, @RequestParam("files") MultipartFile[] files,String oldfiles,String oldchfiles,Model model, HttpServletRequest request) throws IllegalStateException, IOException {
		entity.setId(key);
		String fileName ;
		String FmPicture = "";
		String ChPicture = "";
		if(files!=null&&files.length>0){
			if(oldfiles !="" && oldfiles !=null){
				ChPicture=ChPicture+oldfiles+",";
			}
			if(oldchfiles !="" && oldchfiles !=null){
				FmPicture=FmPicture+oldchfiles+",";
			}
			for(int i = 0;i<files.length;i++){
				MultipartFile file = files[i];
				if (!file.isEmpty()) {
					String srcFileName= file.getOriginalFilename();
					String ext = srcFileName.substring(srcFileName.lastIndexOf("."));
					fileName = UUID.randomUUID() + ext;
					String strFilePath;
					String PROJECT_LOCAL_PATH;
					String NEWS_UPLOADPath ="D:/";
					String NEWS_IMAGE_FILE_ADDR = this.getClass().getClassLoader().getResource("/").getPath().replace("WEB-INF/classes/", "")+"upFile_Fxpt/news/";
					 
				    String DRIVER_UPLOADPath ="D:/";
					String DRIVER_IMAGE_FILE_ADDR = "upFile_Fxpt/driver/" + convertDate(new Date())+"/";
					PROJECT_LOCAL_PATH=getRealPath2();
					
					if (NEWS_UPLOADPath.equals("/")){
						strFilePath=PROJECT_LOCAL_PATH+ NEWS_IMAGE_FILE_ADDR+fileName;
					}else{
						 String rootPath  = "";
							rootPath  = NEWS_IMAGE_FILE_ADDR.substring(1,NEWS_IMAGE_FILE_ADDR.indexOf("upFile_Fxpt/news/"))+"upFile_Fxpt/news/";
							strFilePath=rootPath.replace("/", "\\");
							strFilePath=strFilePath.replaceAll("%20", " ")+fileName;
					}
					File newFile = new File(strFilePath);
					if(!newFile.getParentFile().exists()){
						newFile.getParentFile().mkdirs();
					}
					file.transferTo(newFile);
					
					FmPicture=FmPicture+fileName+",";
					ChPicture=ChPicture+srcFileName+",";
				}
			}
			if(FmPicture !=""){
				entity.setCdpicture(FmPicture.substring(0,FmPicture.length()-1));
			}
			if(ChPicture !=""){
				entity.setCdpicturech(ChPicture.substring(0,ChPicture.length()-1));
			}
		}
		service.update(entity);
		return "redirect:/admin/cdInformation/list/1.html";
	}
	@RequestMapping(value = SAVE)
	public String save(CdInformation entity, @RequestParam("files") MultipartFile[] files, Model model, HttpServletRequest request) throws IllegalStateException, IOException {
		String fileName ;
		String FmPicture = "";
		String ChPicture = "";
		if(files!=null&&files.length>0){
			for(int i = 0;i<files.length;i++){
				MultipartFile file = files[i];
		        if(!file.isEmpty()){
		        	String srcFileName= file.getOriginalFilename();
					String ext = srcFileName.substring(srcFileName.lastIndexOf("."));
					fileName = UUID.randomUUID() + ext;
					String strFilePath;
					String PROJECT_LOCAL_PATH;
					String NEWS_UPLOADPath ="D:/";
					String NEWS_IMAGE_FILE_ADDR = this.getClass().getClassLoader().getResource("/").getPath().replace("WEB-INF/classes/", "")+"upFile_Fxpt/news/";
					 
				    String DRIVER_UPLOADPath ="D:/";
					String DRIVER_IMAGE_FILE_ADDR = "upFile_Fxpt/driver/" + convertDate(new Date())+"/";
					PROJECT_LOCAL_PATH=getRealPath2();
					
					if (NEWS_UPLOADPath.equals("/")){
						strFilePath=PROJECT_LOCAL_PATH+ NEWS_IMAGE_FILE_ADDR+fileName;
					}else{
						 String rootPath  = "";
							rootPath  = NEWS_IMAGE_FILE_ADDR.substring(1,NEWS_IMAGE_FILE_ADDR.indexOf("upFile_Fxpt/news/"))+"upFile_Fxpt/news/";
							strFilePath=rootPath.replace("/", "\\");
							strFilePath=strFilePath.replaceAll("%20", " ")+fileName;
					}
					File newFile = new File(strFilePath);
					if(!newFile.getParentFile().exists()){
						newFile.getParentFile().mkdirs();
					}
					file.transferTo(newFile);
					FmPicture=FmPicture+fileName+",";
					ChPicture=ChPicture+srcFileName+",";
		}
		}
		}
		if (entity.getId() == null) {
			if(FmPicture !=""){
				entity.setCdpicture(FmPicture.substring(0,FmPicture.length()-1));
			}
			if(ChPicture !=""){
				entity.setCdpicturech(ChPicture.substring(0,ChPicture.length()-1));
			}
		    service.insert(entity);
		} else {
			service.update(entity);
		}
		return "redirect:/admin/cdInformation/list/1.html";
	}
	public static String convertDate(Date date) {
		SimpleDateFormat todayDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return todayDateFormatter.format(date);
	}
	 public static String getRealPath2(){
	    	try{
	    		String classPath = CdInformationController.class.getClassLoader().getResource("/").getPath();
	    		  String rootPath  = "";
	    		  //windows下
	    		  if("\\".equals(File.separator)){   
	    		   rootPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
	    		   rootPath = rootPath.replace("/", "\\");
	    		  }
	    		  //linux下
	    		  if("/".equals(File.separator)){   
	    		   rootPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
	    		   rootPath = rootPath.replace("\\", "/");
	    		  }
	    		  rootPath=rootPath.replaceAll("%20", " ");
	    		  return rootPath;
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    	}
	    	return "";
	    }
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	@RequestMapping(value = DELETE)
	public String delete(@PathVariable("id") Long key) {
		service.delete(key);
		return "redirect:/admin/cdInformation/list/1.html";
	}
	
	

}
