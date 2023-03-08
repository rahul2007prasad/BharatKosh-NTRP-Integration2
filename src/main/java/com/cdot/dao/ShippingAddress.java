package com.cdot.dao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShippingAddress {

	@Override
	public String toString() {
		return "ShippingAddress [Address=" + Address + "]";
	}

	//@XmlElement(name="Address")
	private Address Address;
	
	public ShippingAddress() {
		
	}
	
	public ShippingAddress(com.cdot.dao.Address address) {
		super();
		Address = address;
	}

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address address) {
		Address = address;
	}

	
	
}
