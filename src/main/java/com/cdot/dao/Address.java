package com.cdot.dao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

//import jakarta.persistence.Entity;

//@Entity
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
	
	
	@Override
	public String toString() {
		return "Address [firstName=" + firstName + ", lastName=" + lastName + ", address1=" + address1 + ", address2="
				+ address2 + ", postalCode=" + postalCode + ", city=" + city + ", stateregion=" + stateregion
				+ ", state=" + state + ", countryCode=" + countryCode + ", mobileNumber=" + mobileNumber + "]";
	}


	public Address() {
		
	}
	
	
	public Address(String firstName, String lastName, String address1, String address2, String postalCode, String city,
			String stateregion, String state, String countryCode, String mobileNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.postalCode = postalCode;
		this.city = city;
		this.stateregion = stateregion;
		this.state = state;
		this.countryCode = countryCode;
		this.mobileNumber = mobileNumber;
	}

	@XmlElement(name="FirstName")
	private String firstName;
	
	@XmlElement(name="LastName")
	private String lastName;
	
	@XmlElement(name="Address1")
	private String address1;
	
	@XmlElement(name="Address2")
	private String address2;
	
	@XmlElement(name="PostalCode")
	private String postalCode;
	
	@XmlElement(name="City")
	private String city;
	
	@XmlElement(name="StateRegion")
	private String stateregion;
	
	@XmlElement(name="State")
	private String state;
	
	@XmlElement(name="CountryCode")
	private String countryCode;
	
	@XmlElement(name="MobileNumber")
	private String mobileNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateregion() {
		return stateregion;
	}

	public void setStateregion(String stateregion) {
		this.stateregion = stateregion;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	

}
