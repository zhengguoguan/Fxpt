package com.armysoft.fxpt.model;

public class FareCarryMode {
	private Integer id; 
	private Integer fareId ; //模板表外键
	private String region ; //运送地区(存id,格式为'省-市-区',以'|'分隔)
	private Float firstPiece ;
	private Float firstWeight ;
	private Float firstBulk ;
	private Float firstAmount ; //首费
	private Float secondPiece  ;
	private Float secondWeight  ;
	private Float secondBulk  ;
	private Float secondAmount  ;
	private Integer carryWay  ;    //运送方式（赣农速运,ems,顺丰，天天）
	
	private Integer isDefault   ; //是否默认的运送方式
    public FareCarryMode(){}
    
	public FareCarryMode(Integer fareId, String region, Float firstPiece,
			Float firstWeight, Float firstBulk, Float firstAmount,
			Float secondPiece, Float secondWeight, Float secondBulk,
			Float secondAmount, Integer carryWay, Integer isDefault) {
		super();
		this.fareId = fareId;
		this.region = region;
		this.firstPiece = firstPiece;
		this.firstWeight = firstWeight;
		this.firstBulk = firstBulk;
		this.firstAmount = firstAmount;
		this.secondPiece = secondPiece;
		this.secondWeight = secondWeight;
		this.secondBulk = secondBulk;
		this.secondAmount = secondAmount;
		this.carryWay = carryWay;
		this.isDefault = isDefault;
	}
    
	public FareCarryMode(Integer fareId, String region, Integer valuationModel,Float first,Float second,
			Float firstAmount,Float secondAmount, Integer carryWay, Integer isDefault) {
		super();
		this.fareId = fareId;
		this.region = region;
		this.firstAmount = firstAmount;
		this.secondAmount = secondAmount;
		if(valuationModel==1){
			this.firstPiece =first ;
			this.secondPiece = second;
		}else if(valuationModel==2){
			this.firstWeight = first;
			this.secondWeight = second;
		}else if(valuationModel==3){
			this.firstBulk = first;
			this.secondBulk = second;
		}
		this.carryWay = carryWay;
		this.isDefault = isDefault;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFareId() {
		return fareId;
	}

	public void setFareId(Integer fareId) {
		this.fareId = fareId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Float getFirstPiece() {
		return firstPiece;
	}

	public void setFirstPiece(Float firstPiece) {
		this.firstPiece = firstPiece;
	}

	public Float getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(Float firstWeight) {
		this.firstWeight = firstWeight;
	}

	public Float getFirstBulk() {
		return firstBulk;
	}

	public void setFirstBulk(Float firstBulk) {
		this.firstBulk = firstBulk;
	}

	public Float getFirstAmount() {
		return firstAmount;
	}

	public void setFirstAmount(Float firstAmount) {
		this.firstAmount = firstAmount;
	}

	public Float getSecondPiece() {
		return secondPiece;
	}

	public void setSecondPiece(Float secondPiece) {
		this.secondPiece = secondPiece;
	}

	public Float getSecondWeight() {
		return secondWeight;
	}

	public void setSecondWeight(Float secondWeight) {
		this.secondWeight = secondWeight;
	}

	public Float getSecondBulk() {
		return secondBulk;
	}

	public void setSecondBulk(Float secondBulk) {
		this.secondBulk = secondBulk;
	}

	public Float getSecondAmount() {
		return secondAmount;
	}

	public void setSecondAmount(Float secondAmount) {
		this.secondAmount = secondAmount;
	}

	public Integer getCarryWay() {
		return carryWay;
	}

	public void setCarryWay(Integer carryWay) {
		this.carryWay = carryWay;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	
	
	
}
