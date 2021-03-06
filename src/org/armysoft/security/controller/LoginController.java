package org.armysoft.security.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.armysoft.security.model.sys.SysUser;
import org.armysoft.security.service.sys.SysUserService;
import org.armysoft.springmvc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.armysoft.fxpt.base.common.Constants;


/**
 * 登录Controller
 * @author wei
 */
@Controller("adminLoginController")
@RequestMapping("admin")
public class LoginController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	/**
	 * 用户登录
	 * @param userNo
	 * @param password
	 * @param vcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="userLogin",method = RequestMethod.POST)
	public String userLogin(String userNo, String password, String vcode,
			HttpServletRequest request,HttpServletResponse response,@CookieValue(value=Constants.ADMIN_KEY,required=false) String key) {
		try {
			//String key = super.getCookieValue(request, Constants.ADMIN_KEY);
			if(StringUtils.hasText(key)){//已经登录
				return "redirect:/admin/index.html";
			}
			Object code = request.getSession().getAttribute(Constants.VERIFY_CODE);
			if(code != null){
				String oldCode = (String) code;
				if (oldCode.equalsIgnoreCase(vcode)) {//
					SysUser user = sysUserService.getByUserNo(userNo);
					if (user != null && DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPwd()) && user.getStatus() == 1) {
						HttpSession sessionOld = request.getSession(false);
						if(sessionOld != null){
							sessionOld.invalidate();
						}
						request.getSession(true);
						super.setCookie(response, Constants.ADMIN_KEY, user.getUserNo());
						return "redirect:/admin/index.html";
					} else {
						request.setAttribute("msg", "用户名或密码不正确!");
					}
				} else {
					request.setAttribute("msg", "验证码不正确!");
				}
			}else{
				request.setAttribute("msg", "验证码超时!");
			}
			request.setAttribute("userNo", userNo);
			return "admin/base/login";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 注销
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cks = request.getCookies();
		for(Cookie ck : cks){
			Cookie cookie = new Cookie(ck.getName(), "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		request.getSession().invalidate();
		return "redirect:/admin/login.html";
	}
	
}