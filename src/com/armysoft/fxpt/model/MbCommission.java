package com.armysoft.fxpt.model;

import java.util.Date;

public class MbCommission implements java.io.Serializable {
	private Integer id;
	private String mbtype;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMbtype() {
		return mbtype;
	}
	public void setMbtype(String mbtype) {
		this.mbtype = mbtype;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	private String commission;
	
}