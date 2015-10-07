package com.armysoft.fxpt.model;

import java.util.Date;

public class Address implements java.io.Serializable {
	private Integer id; 
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getLxrxm() {
		return lxrxm;
	}
	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}
	public String getLxrdh() {
		return lxrdh;
	}
	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}
	public String getProvno() {
		return provno;
	}
	public void setProvno(String provno) {
		this.provno = provno;
	}
	public String getCityno() {
		return cityno;
	}
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	public String getXxdz() {
		return xxdz;
	}
	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	private Date createdate;
    private String lxrxm;
    private String lxrdh;
    private String provno;
    private String cityno;
    private String xxdz;
    private String openid;
    private String check;
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
    
}