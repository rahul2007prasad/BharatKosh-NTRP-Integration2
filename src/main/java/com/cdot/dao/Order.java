package com.cdot.dao;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

//@Entity
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlAccessorOrder
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	

	@XmlAttribute(name = "InstallationId" , required = true)
	private String InstallationId;

	@XmlAttribute(name = "OrderCode" , required = true)
	private String OrderCode;
	
	//@XmlElement(name = "CartDetails")
	private CartDetails cartDetails;

	//@XmlElement(name = "PaymentMethodMask")
	private PaymentMethodMask payment;

	//@XmlElement(name = "Shopper")
	private Shopper shopper;

	//@XmlElement(name = "ShippingAddress")
	private ShippingAddress shippingAddress;

	//@XmlElement(name = "BillingAddress")
	private BillingAddress billingAddress;

	

	public Order() {

	}

	

	public Order(CartDetails cartDetails, PaymentMethodMask payment, Shopper shopper, ShippingAddress shippingAddress,
			BillingAddress billingAddress, String InstallationId, String OrderCode) {
		super();
		this.cartDetails = cartDetails;
		this.payment = payment;
		this.shopper = shopper;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.InstallationId = InstallationId;
		this.OrderCode = OrderCode;
	}

	public CartDetails getCartDetails() {
		return cartDetails;
	}
	
	public void setCartDetails(CartDetails cartDetails) {
		this.cartDetails = cartDetails;
	}

	public PaymentMethodMask getPayment() {
		return payment;
	}

	public void setPayment(PaymentMethodMask payment) {
		this.payment = payment;
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getInstallationId() {
		return InstallationId;
	}

	public void setInstallationId(String installationId) {
		InstallationId = installationId;
	}

	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	
	
	
	@Override
	public String toString() {
		return "Order [InstallationId=" + InstallationId + ", OrderCode=" + OrderCode + ", cartDetails=" + cartDetails
				+ ", payment=" + payment + ", shopper=" + shopper + ", shippingAddress=" + shippingAddress
				+ ", billingAddress=" + billingAddress + "]";
	}
	
}
