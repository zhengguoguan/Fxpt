package com.armysoft.fxpt.model;

import java.util.Date;

public class CdSmallclass implements java.io.Serializable {
	private Integer id;
	private String categoriesdm;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoriesdm() {
		return categoriesdm;
	}
	public void setCategoriesdm(String categoriesdm) {
		this.categoriesdm = categoriesdm;
	}
	public String getCasmallclassdm() {
		return casmallclassdm;
	}
	public void setCasmallclassdm(String casmallclassdm) {
		this.casmallclassdm = casmallclassdm;
	}
	public String getCasmallclassmc() {
		return casmallclassmc;
	}
	public void setCasmallclassmc(String casmallclassmc) {
		this.casmallclassmc = casmallclassmc;
	}
	private String casmallclassdm;
	private String casmallclassmc;
	
}