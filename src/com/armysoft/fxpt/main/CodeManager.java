package com.armysoft.fxpt.main;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.armysoft.fxpt.base.util.AdvancedUtil;
import com.armysoft.fxpt.base.util.CommonUtil;
import com.armysoft.fxpt.base.util.MenuUtil;
import com.armysoft.fxpt.menu.Button;
import com.armysoft.fxpt.menu.ClickButton;
import com.armysoft.fxpt.menu.ComplexButton;
import com.armysoft.fxpt.menu.Menu;
import com.armysoft.fxpt.menu.ViewButton;
import com.armysoft.fxpt.pojo.Token;
import com.armysoft.fxpt.pojo.WeixinMedia;
import com.armysoft.fxpt.pojo.WeixinUserInfo;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class CodeManager{
	private static Logger log = LoggerFactory.getLogger(CodeManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxf20c625997ab39c9";
		// 第三方用户唯一凭证密钥
		String appSecret = "9957bac23069424031dddfbc37fa79a7";
        String fromUserName="okETVtxhhU46yChDM4jM-xSCws08";
		// 调用接口获取凭证
		String token = CommonUtil.getToken(appId, appSecret).getAccessToken();
		System.out.println(token);
		String ticket=AdvancedUtil.createPermanentQRCode(token,1);
		String picUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
		System.out.println(ticket);
		System.out.println(picUrl);
		WeixinMedia weixinMedia = AdvancedUtil.uploadMedia(token, "image", picUrl);
		System.out.println(weixinMedia);
		String jsonMsg=AdvancedUtil.makeImageCustomMessage(fromUserName, weixinMedia.getMediaId());
		AdvancedUtil.sendCustomMessage(token, jsonMsg);
	}
}
