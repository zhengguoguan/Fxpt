package com.armysoft.fxpt.model;

import java.util.Date;
/**
 * 订单
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Orders implements java.io.Serializable {
	private Integer id; 
	private String orderId;
	private String openId;
	private String realName;
    private String address;
	private String zip;
	private String tel;
	private String email;
	private String memo;
	private Date preTime;
	private Date afterTime;
	private Integer tag;
	private Integer price; //单位分
	private String randCode;//随机码
	
	public Orders(){
		
	}

	public Orders(String orderId, String openId, String realName,
			String address, String tel, Integer tag,Integer price, String randCode) {
		super();
		this.orderId = orderId;
		this.openId = openId;
		this.realName = realName;
		this.address = address;
		this.tel = tel;
		this.tag = tag;
		this.price=price;
		this.randCode = randCode;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Date getPreTime() {
		return preTime;
	}

	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}

	public Date getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(Date afterTime) {
		this.afterTime = afterTime;
	}

	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getRandCode() {
		return randCode;
	}
	public void setRandCode(String randCode) {
		this.randCode = randCode;
	}
	
	
	
}