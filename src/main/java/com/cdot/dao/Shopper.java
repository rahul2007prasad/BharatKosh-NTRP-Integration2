package com.cdot.dao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

//@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Shopper {
	
	public Shopper() {
		
	}
	public Shopper(String shopperEmailAddress) {
		super();
		this.shopperEmailAddress = shopperEmailAddress;
	}
	//@XmlElement(name="ShopperEmailAddress")
	private String shopperEmailAddress;

	@Override
	public String toString() {
		return "Shopper [shopperEmailAddress=" + shopperEmailAddress + "]";
	}
	public String getShopperEmailAddress() {
		return shopperEmailAddress;
	}

	public void setShopperEmailAddress(String shopperEmailAddress) {
		this.shopperEmailAddress = shopperEmailAddress;
	}

}
