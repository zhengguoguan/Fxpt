package org.armysoft.security.controller.sys;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.armysoft.core.Pagination;
import org.armysoft.security.InitResourcesMap;
import org.armysoft.security.annotation.PermissionsAnno;
import org.armysoft.security.model.sys.SysRole;
import org.armysoft.security.model.sys.SysUser;
import org.armysoft.security.service.sys.SysRoleService;
import org.armysoft.security.service.sys.SysUserService;
import org.armysoft.springmvc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.armysoft.fxpt.base.common.Constants;
import com.armysoft.fxpt.base.common.CookieUtil;
import com.gzjr.fxpt.util.mail.SendEmailThread;
import com.gzjr.fxpt.util.mail.UpdatepMailSender;

@Controller
@RequestMapping("admin/sysUser")
public class SysUserController extends BaseController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private InitResourcesMap initResourcesMap;
	
	/**
	 * 条件分页查询用户
	 * 
	 * @param currentPage
	 * @param user
	 * @return
	 */
	@PermissionsAnno("us_list")
	@RequestMapping(value = PAGE_LIST)
	public ModelAndView getByPage(@PathVariable int currentPage, SysUser user,HttpServletRequest request) {
		String userNo = CookieUtil.getUserCookieValue(request,Constants.ADMIN_KEY);
		//if(!"admin".equalsIgnoreCase(userNo)){
			if(userNo.substring(0, 4).equals("4401")){
			ModelAndView mv = new ModelAndView("admin/sys/SysUserCenter");
			mv.addObject("user", sysUserService.getByUserNo(userNo));
			
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("admin/sys/SysUserQ");
		// 初始化分页实体
		Pagination pager = initPage(currentPage);
		pager.setPageSize(10);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phone", user.getPhone());
		params.put("userName", user.getUserName());
		if(user.getStatus() != null && user.getStatus() != -1){
			params.put("status", user.getStatus());
		}
		mv.addObject("users", sysUserService.getByPage(params, pager));
		mv.addObject("page", pager);
		mv.addObject("tempUser", user);
		return mv;
	}
	@PermissionsAnno("us_updt")
	@RequestMapping(value = "updateUserCenter")
	public String updateUserCenter( SysUser user,HttpServletRequest request,Model model) {
		String userNo = CookieUtil.getUserCookieValue(request,Constants.ADMIN_KEY);
		user.setUserNo(userNo);
		sysUserService.updateUserCenter(user);
		model.addAttribute("user", sysUserService.getByUserNo(userNo));
		model.addAttribute("msg", "保存成功");
		return "admin/sys/SysUserCenter";
	}
	
	@RequestMapping("ZShtg.html")
	@ResponseBody
	public String ZShtg(String ids,String examineTime,HttpServletRequest request) throws ParseException {
		String[] idArr = ids.split(",");
		
		for(int id=0;id<idArr.length;id++){
			SysUser mdd=sysUserService.findByKey(Long.valueOf(idArr[id]));
			String newPwd="888888";
			mdd.setPwd(DigestUtils.md5DigestAsHex(newPwd.getBytes()));
			sysUserService.updatepassword(mdd);
			new UpdatepMailSender(mdd.getEmail(), mdd.getUserNo(), Constants.DEFAULT_PASSWORD).start();
		}
		
		request.setAttribute("exl", "ok");
		String exl="ok";
		return exl;
	}

	/**
	 * 准备添加或修改用户
	 * 
	 * @param userNo
	 * @return
	 */
	@RequestMapping(value = ADD)
	public ModelAndView toAddOrUpdate(String userNo) {
		ModelAndView mv = new ModelAndView();
		List<SysRole> allRoles = sysRoleService.getAll();
		List<SysRole> userRoles = null;
		if (StringUtils.hasText(userNo)){// 修改
			userRoles = sysRoleService.getByUserNo(userNo);
			mv.addObject("update", "1");
			mv.addObject("user", sysUserService.getByUserNo(userNo));
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (SysRole role : allRoles) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("roleNo", role.getRoleNo());
			map.put("roleName", role.getRoleName());
			map.put("checked", "");
			if (StringUtils.hasText(userNo)) {// 修改
				if(userRoles != null){
					for (SysRole r : userRoles) {
						if(r != null)
						if (role.getRoleNo().equals(r.getRoleNo())) {
							map.put("checked", "checked");
							break;
						}
					}
				}
			}
			result.add(map);
		}
			
		mv.addObject("allRoles", result);
		mv.addObject("viewType", "add");
		mv.setViewName("admin/sys/SysUserA_U_D");
		return mv;
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@PermissionsAnno("us_save")
	@RequestMapping(value = SAVE)
	public String save(SysUser user, HttpServletRequest request) {
		user.setCreateDate(new Date());
		user.setCreater(sysUserService.getByUserNo(super.getCookieValue(request, Constants.ADMIN_KEY)));
		user.setStatus(1);
		user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
		String[] roleNos = request.getParameterValues("ck_roleNo");
		List<String> addRoles = new ArrayList<String>();
		if(roleNos != null)
			for (String role : roleNos) {
				addRoles.add(role);
			}
		sysUserService.insert(user, addRoles);
		//更新用户权限
		initResourcesMap.updateResourcesMap(user.getUserNo(), null, addRoles);
		return "redirect:/admin/sysUser/list/1.html";
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	@PermissionsAnno("us_updt")
	@RequestMapping(value = UPDATE)
	public String update(@PathVariable("id") String userNo, SysUser user,
			HttpServletRequest request) {
		user.setUserNo(userNo);
		String[] roleNos = request.getParameterValues("ck_roleNo");
		List<SysRole> roles = sysRoleService.getByUserNo(userNo);

		List<String> delRoles = new ArrayList<String>();
		List<String> addRoles = new ArrayList<String>();
		if (roles != null && !roles.isEmpty()) {
			for (SysRole role : roles) {
				boolean flag = true;
				if(roleNos != null){
					for (String rn : roleNos) {
						if (role.getRoleNo().equals(rn)) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					delRoles.add(role.getRoleNo());
				}
			}
		}
		if(roleNos != null){
			for (String rn : roleNos) {
				boolean flag = true;
				if (roles != null){
					for (SysRole role : roles) {
						if (role.getRoleNo().equals(rn)) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					addRoles.add(rn);
				}
			}
		}
		sysUserService.update(user, delRoles, addRoles);
		//更新用户权限
		initResourcesMap.updateResourcesMap(user.getUserNo(), delRoles, addRoles);
		return "redirect:/admin/sysUser/list/1.html";
	}

	/**
	 * 删除用户
	 * 
	 * @param userNo
	 * @return
	 */
	@PermissionsAnno("us_del")
	@RequestMapping(value = DELETE)
	public String delete(@PathVariable("id") String userNo) {
		if(!"admin".equalsIgnoreCase(userNo)){
			sysUserService.delete(userNo);
			//删除用户权限
			initResourcesMap.cleanResourcesMap(userNo);
		}
		return "redirect:/admin/sysUser/list/1.html";
	}

	/**
	 * 激活/冻结用户
	 * 
	 * @param userNo
	 * @param status
	 * @return
	 */
	@PermissionsAnno("us_chasta")
	@RequestMapping("changeStatus")
	@ResponseBody
	public String changeStatus(String userNo, Integer status,String toDo) {
		if(!"admin".equalsIgnoreCase(userNo)){
			sysUserService.updateStatus(userNo, status);
			if(status == Constants.USER_ACTIVE){
				List<SysRole> roles = sysRoleService.getByUserNo(userNo);
				List<String> addRoles = new ArrayList<String>();
				if (roles != null)
					for(SysRole role : roles){
						addRoles.add(role.getRoleNo());
					}
				initResourcesMap.updateResourcesMap(userNo, null, addRoles);//更新用户权限
				if(StringUtils.hasText(toDo)){
					SysUser user = sysUserService.getByUserNo(userNo);
					new SendEmailThread(user.getEmail(), userNo, Constants.DEFAULT_PASSWORD).start();
				}
			}else{
				initResourcesMap.cleanResourcesMap(userNo);//清除用户权限
			}
		}
		JSONObject json = new JSONObject();
		json.put("flag", 1);
		return json.toString();
	}

	/**
	 * 异步验证用户编号是否已存在
	 * 
	 * @param userNo
	 * @return
	 */
	@RequestMapping("validUserNo")
	@ResponseBody
	public String validUserNo(String userNo) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("isFlag", sysUserService.getByUserNo(userNo) == null);
		return jsonObject.toString();
	}
	
	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("toModifyPwd")
	public String toModifyPwd() {
		return "admin/sys/ModifyPwd";
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @param request
	 * @return
	 */
	@RequestMapping("modifyPwd")
	public String modifyPwd(String oldPwd, String newPwd,
			HttpServletRequest request) {
		String userNo = super.getCookieValue(request, Constants.ADMIN_KEY);
		SysUser user = sysUserService.getByUserNo(userNo);
		if (user.getPwd().equals(DigestUtils.md5DigestAsHex(oldPwd.getBytes()))) {
			user.setPwd(DigestUtils.md5DigestAsHex(newPwd.getBytes()));
			sysUserService.updatePwd(user);
			request.setAttribute("msg", "密码修改成功！");
		} else {
			request.setAttribute("msg", "修改失败，旧密码不正确！");
		}
		return "admin/sys/ModifyPwd";
	}
}
