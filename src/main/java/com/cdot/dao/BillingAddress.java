package com.cdot.dao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

//@Entity
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BillingAddress {
	
	//@XmlElement(name="Address")
	private Address address;
	
	public BillingAddress() {
		
		
	}
	

	public BillingAddress(Address address) {
		super();
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
