package org.armysoft.security.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;

import com.armysoft.fxpt.base.common.Constants;
import com.armysoft.fxpt.base.common.CookieUtil;

public class PermissionTag extends TagSupport {

	private String permValue;
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
		if(StringUtils.hasText(permValue)){
			String userNo = CookieUtil.getUserCookieValue(request,Constants.ADMIN_KEY);
			if("admin".equalsIgnoreCase(userNo)){//Ĭ��admin�ǳ�������Ա����������
				return EVAL_BODY_INCLUDE;
			}else{
				if(Constants.getResourcesMap().contains(userNo + Constants.SEPARATOR + permValue)){
					return EVAL_BODY_INCLUDE;
				}
			}
		}
		return SKIP_BODY;
	}
	public String getPermValue() {
		return permValue;
	}
	public void setPermValue(String permValue) {
		this.permValue = permValue;
	}
}
