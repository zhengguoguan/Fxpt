package com.armysoft.fxpt.model;

import java.util.Date;
/**
 * 订单细目
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class OrdersDetail implements java.io.Serializable {
	private Integer id; 
	private String orderId;
	private String productId;
	private String count;
	private Integer price;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}