package com.armysoft.fxpt.model;

import java.util.Date;

public class Cars implements java.io.Serializable {
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
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	private Date createdate;
	private Integer pid;
	private Integer pnum;
	private String openid;
	private String cdname;
	private String cdprice;
	private String cdpicturech;
	public String getCdpicturech() {
		return cdpicturech;
	}
	public void setCdpicturech(String cdpicturech) {
		this.cdpicturech = cdpicturech;
	}
	public String getCdprice() {
		return cdprice;
	}
	public void setCdprice(String cdprice) {
		this.cdprice = cdprice;
	}
	public String getCdname() {
		return cdname;
	}
	public void setCdname(String cdname) {
		this.cdname = cdname;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}