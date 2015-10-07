package com.armysoft.fxpt.model;

import java.util.Date;

public class CdCategories implements java.io.Serializable {
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
	public String getCategoriesmc() {
		return categoriesmc;
	}
	public void setCategoriesmc(String categoriesmc) {
		this.categoriesmc = categoriesmc;
	}
	private String categoriesmc;
	
}