package com.armysoft.fxpt.model;

public class FareTemplate {
	private Integer id; 
	private String name;
	private String shopAddr;
	private String dispatchTime;  //发货时间
	private Integer isFreePostage; //是否包邮
	private Integer valuationModel; //计价方式(1:按件 2:按重量 3:按体积)
	private Integer isFreePostageByif; //是否指定条件包邮
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getDispatchTime() {
		return dispatchTime;
	}
	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	public Integer getIsFreePostage() {
		return isFreePostage;
	}
	public void setIsFreePostage(Integer isFreePostage) {
		this.isFreePostage = isFreePostage;
	}
	
	public Integer getValuationModel() {
		return valuationModel;
	}
	public void setValuationModel(Integer valuationModel) {
		this.valuationModel = valuationModel;
	}
	public Integer getIsFreePostageByif() {
		return isFreePostageByif;
	}
	public void setIsFreePostageByif(Integer isFreePostageByif) {
		this.isFreePostageByif = isFreePostageByif;
	}

	
}
