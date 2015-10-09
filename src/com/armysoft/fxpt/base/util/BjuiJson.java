/*    */ package com.armysoft.fxpt.base.util;
/*    */ 
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class BjuiJson
/*    */ {
/*    */   public static String json(String statusCode, String message, String tabid, String dialogid, String divid, String closeCurrent, String forward, String forwardConfirm)
/*    */     throws JSONException
/*    */   {
/* 14 */     JSONObject json = new JSONObject();
/* 15 */     json.put("statusCode", statusCode);
/* 16 */     json.put("message", message);
/* 17 */     json.put("tabid", tabid);
/* 18 */     json.put("dialogid", dialogid);
/* 19 */     json.put("divid", divid);
/* 20 */     json.put("closeCurrent", closeCurrent);
/* 21 */     json.put("forward", forward);
/* 22 */     json.put("forwardConfirm", forwardConfirm);
/* 23 */     return json.toString();
/*    */   }
/*    */ }

/* Location:           F:\apache-tomcat-7.0.52\webapps\Fenxiao\WEB-INF\classes\
 * Qualified Name:     com.lxinet.fenxiao.utils.BjuiJson
 * JD-Core Version:    0.6.1
 */