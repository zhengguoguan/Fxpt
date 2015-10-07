package com.armysoft.fxpt.pojo;

/**
 * 临时票
 * 
 * @author guoguan
 * @date 2013-10-17
 */
public class JsApiTicket {
	// 临时票
	private String ticket;
	// 凭证有效期，单位：秒
	private int expiresIn;
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	
}