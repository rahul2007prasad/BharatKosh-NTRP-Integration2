package com.cdot.dao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

/* @Author Rahul Prasad
*/


//@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class CartDetails {
	public CartDetails() {
		super();
	}
	
	public CartDetails(String description, String amount, String orderContent, String paymentTypeId, String paoCode,
			String ddoCode) {
		super();
		this.description = description;
		this.amount = amount;
		this.orderContent = orderContent;
		this.paymentTypeId = paymentTypeId;
		this.paoCode = paoCode;
		this.ddoCode = ddoCode;
		
	}

	//@XmlElement(name="Description")
	private String description;
	
	//@XmlElement(name="Amount")
	private String amount;
	
	//@XmlElement(name="OrderContent")
	private String orderContent;
	
	
	

	//@XmlElement(name="PaymentTypeId")
	private String paymentTypeId;
	
	//@XmlElement(name="PAOCode")
	private String paoCode;
	
	//@XmlElement(name="DDOCode")
	private String ddoCode;
	
	
	//@XmlAttribute(name="cart")
	private String type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String ampount) {
		this.amount = ampount;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaoCode() {
		return paoCode;
	}

	public void setPaoCode(String paoCode) {
		this.paoCode = paoCode;
	}

	public String getDdoCode() {
		return ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}
	
}
