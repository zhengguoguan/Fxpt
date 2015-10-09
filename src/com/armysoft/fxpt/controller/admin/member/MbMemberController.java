package com.armysoft.fxpt.controller.admin.member;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
import javax.servlet.ServletOutputStream;
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

import com.armysoft.fxpt.base.util.AdvancedUtil;
import com.armysoft.fxpt.base.util.CommonUtil;
import com.armysoft.fxpt.base.util.HcPic;
import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.MbMember;
import com.armysoft.fxpt.pojo.WeixinUserInfo;
import com.armysoft.fxpt.service.member.CdCategoriesService;
import com.armysoft.fxpt.service.member.MbMemberService;




@Controller
@RequestMapping("admin/mbMember")
public class  MbMemberController extends BaseController {

	@Resource
	private MbMemberService service;
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
	public String getByPage(@PathVariable Integer currentPage,String fmbname,String fwxname,String fhymc,String frysjf,String frysje, String fhtqxf,String fhtqxe,String cyqy,String hylbNo,String hyzcNo,String ssq,String fzjgNo,Model model,
			CdCategories entity, HttpServletRequest request) {
		Pagination pager = initPage(currentPage);
		pager.setPageSize(10);
		Map<String, Object> params = new HashMap<String, Object>();
		if(fmbname !="" && fmbname !=null){
			params.put("fmbname", fmbname);
			request.setAttribute("fmbname", fmbname);
	    }
	    if(fwxname !="" && fwxname !=null){
				params.put("fwxname", fwxname);
				request.setAttribute("fwxname", fwxname);
		}
		model.addAttribute("list", service.getByPage(params, pager));
		model.addAttribute("page", pager);
		model.addAttribute("model", entity);
		return "admin/member/MbMemberQ";
	}
    @RequestMapping("Zyjfx.html")
	@ResponseBody
	public String Zyjfx(String ids,String examineTime,HttpServletRequest request) throws ParseException {
		String[] idArr = ids.split(",");
		
		for(int id=0;id<idArr.length;id++){
			MbMember mdd= service.findByKey(Long.valueOf(idArr[id]));
			mdd.setMbtype("一级分销");
			service.update(mdd);
		}
		
		request.setAttribute("exl", "ok");
		String exl="ok";
		return exl;
	}
    @RequestMapping("Zejfx.html")
	@ResponseBody
	public String Zejfx(String ids,String examineTime,HttpServletRequest request) throws ParseException {
		String[] idArr = ids.split(",");
		
		for(int id=0;id<idArr.length;id++){
			MbMember mdd= service.findByKey(Long.valueOf(idArr[id]));
			mdd.setMbtype("二级分销");
			service.update(mdd);
		}
		
		request.setAttribute("exl", "ok");
		String exl="ok";
		return exl;
	}
    @RequestMapping("Zsjfx.html")
	@ResponseBody
	public String Zsjfx(String ids,String examineTime,HttpServletRequest request) throws ParseException {
		String[] idArr = ids.split(",");
		
		for(int id=0;id<idArr.length;id++){
			MbMember mdd= service.findByKey(Long.valueOf(idArr[id]));
			mdd.setMbtype("三级分销");
			service.update(mdd);
		}
		
		request.setAttribute("exl", "ok");
		String exl="ok";
		return exl;
	}
    
    @RequestMapping("Schct.html")
	@ResponseBody
	public void Schct(String id,String examineTime,HttpServletRequest request,HttpServletResponse response) throws ParseException {
    	
		 MbMember mb=service.findByKey(Long.valueOf(id));
		 String name=mb.getMbname();
		 String address="地址:"+mb.getDz();
		 String tel="电话:"+mb.getTel();
		 String phone="手机:"+mb.getTel();
		 String emil="Email:"+mb.getEmail();
		 String zw=mb.getZw();
		 String appId = "wxf20c625997ab39c9";
			// 第三方用户唯一凭证密钥
			String appSecret = "9957bac23069424031dddfbc37fa79a7";
      
			// 调用接口获取凭证
			String token = CommonUtil.getToken(appId, appSecret).getAccessToken();
			WeixinUserInfo user = AdvancedUtil.getUserInfo(token, mb.getOpenid());
			MbMember mc=service.findByOpenid(mb.getOpenid());
			Integer ticketId;
			if(mc.getMbtype().equals("三级分销")){
				ticketId=Integer.valueOf(mc.getMid());
			}else{
				ticketId=mc.getId();
			}
			/*textMessage.setContent("二维码生成中，请稍候...");
			respXml = MessageUtil.messageToXml(textMessage);*/
			//<xml><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[]]></Content></xml>
			 
			String ticket=AdvancedUtil.createPermanentQRCode(token,ticketId);
			String picUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
			String filePath=AdvancedUtil.getQRCode(ticket, picUrl, request); 
			String mpPath="\\QRCodeFile\\mp.jpg";
			String newPath="\\QRCodeFile\\new.jpg";
			String realfilePath=request.getSession().getServletContext().getRealPath(filePath);
			String realmpPath=request.getSession().getServletContext().getRealPath(mpPath);
			String realnewPath=request.getSession().getServletContext().getRealPath(newPath);
			 HcPic tt = new HcPic();      
			 BufferedImage d = tt.loadImageLocal(realfilePath);    
			 BufferedImage b = tt .loadImageLocal(realmpPath);       
			
					 //往图片上写文件            
					 tt.writeImageLocal(realnewPath, tt.modifyImagetogeter(d, b)); 
			 BufferedImage e = tt .loadImageLocal(realnewPath);
		 if(name.length()==3){
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,name,445,330,50)); 
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,zw,622,331,40));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,address,340,530,30));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,tel,340,570,30));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,phone,340,610,30));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,emil,340,650,30));
		 }else{
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,name,455,330,50)); 
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,zw,562,331,40));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,address,340,530,30));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,tel,340,570,30));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,phone,340,610,30));  
			 tt.writeImageLocal(realnewPath,tt.modifyImage(e,emil,340,650,30));
			 
		 }
		 //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
		       response.setContentType("multipart/form-data");  
		        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
		        response.setHeader("Content-Disposition", "attachment;fileName="+"mp.jpg");  
		        ServletOutputStream out;  
	     //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
		        File file = new File(realnewPath);  
		        try {  
		        	            FileInputStream inputStream = new FileInputStream(file);  
		        	             //3.通过response获取ServletOutputStream对象(out)  
		        	          out = response.getOutputStream();  
		        	
		        	         int bi = 0;  
		        	  
		        	          byte[] buffer = new byte[512];  
		        	          while (bi != -1){  
		        	              bi = inputStream.read(buffer);  
		        	              //4.写到输出流(out)中  
		        	              out.write(buffer,0,bi);  
		                  }  
		        	          inputStream.close();  
		        	           out.close();  
		        	           out.flush();  
		        	   } catch (IOException ex) {  
		        	           ex.printStackTrace();  
		              }  

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
		return "admin/member/MbMemberV";
	}

	/**
	 * 准备添加
	 * @return
	 */

	@RequestMapping(value = ADD)
	public String toAdd(Long id,HttpServletRequest request,Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer countAll=service.getCount(params)+1;
		MbMember mb=service.findByKey(id);
		if(mb!=null){
			model.addAttribute("model", mb);
		}else{
			mb=new MbMember();
			mb.setMbtype("三级分销");
			model.addAttribute("model", mb);
		}
		return "admin/member/MbMemberV";
	}
	

	/**
	 * 保存
	 * @param entity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = UPDATE)
	public String update(@PathVariable("id") Integer key,MbMember entity, Model model, HttpServletRequest request) {
		entity.setId(key);
		service.update(entity);
		return "redirect:/admin/mbMember/list/1.html";
	}
	@RequestMapping(value = SAVE)
	public String save(MbMember entity, Model model, HttpServletRequest request) {
		if (entity.getId() == null) {
		    service.insert(entity);
		} else {
			service.update(entity);
		}
		return "redirect:/admin/mbMember/list/1.html";
	}
	
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	@RequestMapping(value = DELETE)
	public String delete(@PathVariable("id") Long key) {
		service.delete(key);
		return "redirect:/admin/mbMember/list/1.html";
	}
	
	

}
