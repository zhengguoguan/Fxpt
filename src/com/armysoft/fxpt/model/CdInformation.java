package com.armysoft.fxpt.model;

import java.util.Date;

public class CdInformation implements java.io.Serializable {
	private Integer id;
	private String cpbh;
	private String cdpicturech;
	public String getCdpicturech() {
		return cdpicturech;
	}
	public void setCdpicturech(String cdpicturech) {
		this.cdpicturech = cdpicturech;
	}
	public String getCpbh() {
		return cpbh;
	}
	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}
	private String cdname;//商品名字
	private String cdprice;//商品价格
	private String cdcategories;//商品大类
	private String cdsmallclass;//商品小类
	private String cdintroduction;//商品简介
	private String shelfnumber;//货架号
	private String stockr;//库存
	private String cdpicture;//商品图片
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCdname() {
		return cdname;
	}
	public void setCdname(String cdname) {
		this.cdname = cdname;
	}
	public String getCdprice() {
		return cdprice;
	}
	public void setCdprice(String cdprice) {
		this.cdprice = cdprice;
	}
	public String getCdcategories() {
		return cdcategories;
	}
	public void setCdcategories(String cdcategories) {
		this.cdcategories = cdcategories;
	}
	public String getCdsmallclass() {
		return cdsmallclass;
	}
	public void setCdsmallclass(String cdsmallclass) {
		this.cdsmallclass = cdsmallclass;
	}
	public String getCdintroduction() {
		return cdintroduction;
	}
	public void setCdintroduction(String cdintroduction) {
		this.cdintroduction = cdintroduction;
	}
	public String getShelfnumber() {
		return shelfnumber;
	}
	public void setShelfnumber(String shelfnumber) {
		this.shelfnumber = shelfnumber;
	}
	public String getStockr() {
		return stockr;
	}
	public void setStockr(String stockr) {
		this.stockr = stockr;
	}
	public String getCdpicture() {
		return cdpicture;
	}
	public void setCdpicture(String cdpicture) {
		this.cdpicture = cdpicture;
	}
}