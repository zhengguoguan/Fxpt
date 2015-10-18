package com.armysoft.fxpt.model;

public class FareFreeCondition {
	private Integer id; 
	private String fareId ; //模板表外键
	private String region ; //包邮地区(存id,格式为'省-市-区',以'|'分隔)
	private Integer pieceNo ; //包邮件数
	private Float weightNo ;
	private Float bulkNo;
	private Float amount; //包邮金额
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFareId() {
		return fareId;
	}
	public void setFareId(String fareId) {
		this.fareId = fareId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getPieceNo() {
		return pieceNo;
	}
	public void setPieceNo(Integer pieceNo) {
		this.pieceNo = pieceNo;
	}
	public Float getWeightNo() {
		return weightNo;
	}
	public void setWeightNo(Float weightNo) {
		this.weightNo = weightNo;
	}
	public Float getBulkNo() {
		return bulkNo;
	}
	public void setBulkNo(Float bulkNo) {
		this.bulkNo = bulkNo;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
}
