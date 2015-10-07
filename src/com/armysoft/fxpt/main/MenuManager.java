package com.armysoft.fxpt.main;


import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.armysoft.fxpt.base.util.CommonUtil;
import com.armysoft.fxpt.base.util.MenuUtil;
import com.armysoft.fxpt.menu.Button;
import com.armysoft.fxpt.menu.ClickButton;
import com.armysoft.fxpt.menu.ComplexButton;
import com.armysoft.fxpt.menu.Menu;
import com.armysoft.fxpt.menu.ViewButton;
import com.armysoft.fxpt.pojo.Token;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static String urlEncodeUTF8(String source){
		String result=source;
		try{
			result=java.net.URLEncoder.encode(source,"utf-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return result;
	}
	private static Menu getMenu() {
		ViewButton btn11 = new ViewButton();
		btn11.setName("学生用品");
		btn11.setType("view");     
		

        String Turl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf20c625997ab39c9&redirect_uri=http%3A%2F%2Fmas10086.cn%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		
        String Url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_url=REDIRECT_URL&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String requestUrl = Url.replace("APPID","wxf20c625997ab39c9").replace("REDIRECT_URL", urlEncodeUTF8("http://mas10086.cn/oauthServlet")).replace("SCOPE","snsapi_userinfo").replace("STATE","1");
		System.out.println(requestUrl);
		btn11.setUrl(Turl);
		ViewButton btn12 = new ViewButton();
		btn12.setName("办公用品");
		btn12.setType("view");
		btn12.setUrl("http://qiuxf.x7.fjjsp01.com");
		ViewButton btn13 = new ViewButton();
		btn13.setName("体育用品");
		btn13.setType("view");
		btn13.setUrl("http://qiuxf.x7.fjjsp01.com");

		ClickButton btn21 = new ClickButton();
		btn21.setName("推广二维码");
		btn21.setType("click");
		btn21.setKey("getQRcode");


		ViewButton btn31 = new ViewButton();
		btn31.setName("公司简介");
		btn31.setType("view");
		btn31.setUrl("http://qiuxf.x7.fjjsp01.com/admin/news/about.html");


		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("芸祥商城");
		mainBtn1.setSub_button(new Button[] {btn11,btn12,btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("会员中心");
		mainBtn2.setSub_button(new Button[] {btn21});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于我们");
		mainBtn3.setSub_button(new Button[] {btn31});

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxf20c625997ab39c9";
		// 第三方用户唯一凭证密钥
		String appSecret = "9957bac23069424031dddfbc37fa79a7";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
            System.out.println(result);
			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
		
	}
}
