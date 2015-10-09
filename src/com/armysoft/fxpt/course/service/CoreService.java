package com.armysoft.fxpt.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.armysoft.fxpt.base.util.AdvancedUtil;
import com.armysoft.fxpt.base.util.CommonUtil;
import com.armysoft.fxpt.base.util.MessageUtil;
import com.armysoft.fxpt.message.resp.Article;
import com.armysoft.fxpt.message.resp.NewsMessage;
import com.armysoft.fxpt.message.resp.TextMessage;
import com.armysoft.fxpt.model.MbMember;
import com.armysoft.fxpt.pojo.WeixinMedia;
import com.armysoft.fxpt.pojo.WeixinUserInfo;
import com.armysoft.fxpt.service.member.MbMemberService;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
@Service
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	@Resource
	private MbMemberService service;
	public  String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					String appId = "wxf20c625997ab39c9";
					// 第三方用户唯一凭证密钥
					String appSecret = "9957bac23069424031dddfbc37fa79a7";
					String accessToken = CommonUtil.getToken(appId, appSecret).getAccessToken();
					WeixinUserInfo user = AdvancedUtil.getUserInfo(accessToken, fromUserName);
					System.out.println("OpenID：" + user.getOpenId());
					System.out.println("关注状态：" + user.getSubscribe());
					System.out.println("关注时间：" + user.getSubscribeTime());
					System.out.println("昵称：" + user.getNickname());
					System.out.println("性别：" + user.getSex());
					System.out.println("国家：" + user.getCountry());
					System.out.println("省份：" + user.getProvince());
					System.out.println("城市：" + user.getCity());
					System.out.println("语言：" + user.getLanguage());
					System.out.println("头像：" + user.getHeadImgUrl());
					String eventKey = requestMap.get("EventKey");
					String Mid="";
					if(!eventKey.isEmpty()){
						Mid=eventKey.replace("qrscene_", "");
						MbMember smb=service.findByKey(Long.valueOf(Mid));
						if(smb.getMbtype().equals("三级分销")){
							Mid=smb.getMid();
						}
					}
					Map<String, Object> params = new HashMap<String, Object>();
					Integer sumcount=service.getCount(params)+1;
					if(!Mid.isEmpty()){
						MbMember mc=service.findByKey(Long.valueOf(Mid));
						textMessage.setContent("您好" + user.getNickname()  +",感谢您关注芸祥文具礼品!  您是我们的第"+sumcount+"位会员"+"是"+mc.getWxname()+"下线");
					}else{
						textMessage.setContent("您好" + user.getNickname()  +",感谢您关注芸祥文具礼品!  您是我们的第"+sumcount+"位会员");
					}
					
					
					
					// 将消息对象转换成xml
					
					respXml = MessageUtil.messageToXml(textMessage);
					MbMember mm=new MbMember();
					mm.setWxname(user.getNickname());
					mm.setOpenid(user.getOpenId());
					mm.setMbtype("三级分销");
					mm.setMid(Mid);
					service.insert(mm);
					
					
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					String eventKey = requestMap.get("EventKey");
					textMessage.setContent(eventKey);
					respXml = MessageUtil.messageToXml(textMessage);
					// TODO 暂不做处理
				}else if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					String eventKey = requestMap.get("EventKey");
					textMessage.setContent(eventKey);
					respXml = MessageUtil.messageToXml(textMessage);
					// TODO 暂不做处理
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("oschina")) {
						
						
						Article article = new Article();
						article.setTitle("获取二维码");
						article.setDescription("开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。\n\n开源中国的目的是为中国的IT技术人员提供一个全面的、快捷更新的用来检索开源软件以及交流开源经验的平台。\n\n经过不断的改进,目前开源中国社区已经形成了由开源软件库、代码分享、资讯、讨论区和博客等几大频道内容。");
						article.setPicUrl("");
						article.setUrl("http://m.oschina.net");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("iteye")) {
						textMessage.setContent("ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com");
						respXml = MessageUtil.messageToXml(textMessage);
					} else if (eventKey.equals("getQRcode")){
						try{
							
						
						String appId = "wxf20c625997ab39c9";
						// 第三方用户唯一凭证密钥
						String appSecret = "9957bac23069424031dddfbc37fa79a7";
                        
						// 调用接口获取凭证
						String token = CommonUtil.getToken(appId, appSecret).getAccessToken();
						WeixinUserInfo user = AdvancedUtil.getUserInfo(token, fromUserName);
						MbMember mb=service.findByOpenid(user.getOpenId());
						Integer ticketId;
						if(mb.getMbtype().equals("三级分销")){
							ticketId=Integer.valueOf(mb.getMid());
						}else{
							ticketId=mb.getId();
						}
						/*textMessage.setContent("二维码生成中，请稍候...");
						respXml = MessageUtil.messageToXml(textMessage);*/
						respXml = "";
						//<xml><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[]]></Content></xml>
						 
						String ticket=AdvancedUtil.createPermanentQRCode(token,ticketId);
						String picUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
						String filePath=AdvancedUtil.getQRCode(ticket, picUrl, request); 
						String realPath="http://qiuxf.x7.fjjsp01.com/"+filePath;
						//QRCodeFile/gQEL8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3lreEVtZjNsczFMS3RiZ2dLMkJVAAIEo6vZVQMEAAAAAA==.jpg";" +         
						
						
						WeixinMedia weixinMedia = AdvancedUtil.uploadMedia(token, "image",realPath);
						String jsonMsg=AdvancedUtil.makeImageCustomMessage(fromUserName, weixinMedia.getMediaId());
						AdvancedUtil.sendCustomMessage(token, jsonMsg);
						}catch(Exception ex){
							System.out.println(ex.toString());
						}
					}
				}
			}
			// 当用户发消息时
			else {
				textMessage.setContent("请通过菜单使用网址导航服务！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
