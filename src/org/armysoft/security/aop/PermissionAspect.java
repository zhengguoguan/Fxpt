package org.armysoft.security.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.armysoft.security.annotation.PermissionsAnno;
import org.armysoft.security.exception.NoPermissionExp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.armysoft.fxpt.base.common.Constants;
import com.armysoft.fxpt.base.common.CookieUtil;



@Aspect
public class PermissionAspect {
	
	/** 调用controller方法之前  */
	@Before("execution(* com.armysoft.*.controller.admin.*.*.*(..)) || execution(* org.armysoft.security.controller.*.*.*(..))")
	public void invokeBefore(JoinPoint jp){
		Method method = ((MethodSignature) jp.getSignature()).getMethod(); 
		if(method.isAnnotationPresent(PermissionsAnno.class)){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String userNo = CookieUtil.getUserCookieValue(request,Constants.ADMIN_KEY);
			if(!"admin".equalsIgnoreCase(userNo)){//默认admin是超级管理员，不作拦截
				String permValue = userNo + Constants.SEPARATOR + method.getAnnotation(PermissionsAnno.class).value();
		        if(!Constants.getResourcesMap().contains(permValue)){
		        	throw new NoPermissionExp("用户权限不足！");
		        	}
			}
		}
	}
}
