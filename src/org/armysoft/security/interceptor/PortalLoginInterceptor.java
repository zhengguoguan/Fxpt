package org.armysoft.security.interceptor;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.armysoft.fxpt.base.common.Constants;
import com.armysoft.fxpt.base.common.CookieUtil;


/**
 * 前台登录拦截
 * @author Administrator
 *
 */
public class PortalLoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}
	
	/** 
	* 登入免检查地址 
	*/ 
	private List<String> uncheckUrls;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//request.setAttribute("cssFile", Constants.cssFile);
		request.setAttribute("dateStr", new Date().getTime());
		String url = request.getServletPath().toString();
		System.out.println("url:" + url);
		for(String str : uncheckUrls){
			if(url.indexOf(str) != -1){
				return true;
			}
		}
		
		String studentNo = CookieUtil.getUserCookieValue(request,Constants.FRONT_KEY);
		if(StringUtils.hasText(studentNo)){
			if(url.equals("/portal/login/loginPage.html")||url.equals("/portal/login/login.html")){
				response.sendRedirect(request.getContextPath() + "/portal/userInfo/userCenter.html");
				return false;
			}
		}else{
			//if(!url.equals("/front/index/login.html")){
				String contextPath = request.getContextPath();//上下文
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head><title>redirecting...</title></head>");
				out.println("<body>");
				out.println("<script language='javascript'>");
				out.println("top.location.href='" + contextPath + "/portal/login/loginPage.html'");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				out.flush();
				out.close();
				return false;
			//}
		}
		return true;
	}

	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	
}
