package org.armysoft.security.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.armysoft.fxpt.base.common.Constants;
import com.armysoft.fxpt.base.common.CookieUtil;



/**
 * 登录拦截
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

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
		String url = request.getServletPath().toString();
		for(String str : uncheckUrls){
			if(url.indexOf(str) != -1){
				return true;
			}
		}
		
		String userNo = CookieUtil.getUserCookieValue(request,Constants.ADMIN_KEY);
		if(StringUtils.hasText(userNo)){
			if(url.equals("/admin/login.html")){
				response.sendRedirect(request.getContextPath() + "/admin/index.html");
				return false;
			}
		}else{
			if(!url.equals("/admin/login.html")){
				String contextPath = request.getContextPath();//上下文
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head><title>redirecting...</title></head>");
				out.println("<body>");
				out.println("<script language='javascript'>");
				out.println("top.location.href='" + contextPath + "/admin/login.html'");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				out.flush();
				out.close();
				return false;
			}
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
