package com.cdot.dao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethodMask {

	
	public PaymentMethodMask() {
		
	}
	
	@Override
	public String toString() {
		return "PaymentMethodMask [includeCode=" + includeCode + "]";
	}

	//@XmlElement(name="IncludeCode")
	private String includeCode;

	public PaymentMethodMask(String includeCode) {
		super();
		this.includeCode = includeCode;
	}

	public String getIncludeCode() {
		return includeCode;
	}

	public void setIncludeCode(String includeCode) {
		this.includeCode = includeCode;
	}
}
