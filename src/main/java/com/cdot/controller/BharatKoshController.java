package com.cdot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdot.dao.Address;
import com.cdot.dao.BillingAddress;
import com.cdot.dao.CartDetails;
import com.cdot.dao.Order;
import com.cdot.dao.PaymentMethodMask;
import com.cdot.dao.ShippingAddress;
import com.cdot.dao.Shopper;

@RestController
//@RequestMapping("bharatkosh")
public class BharatKoshController {
	
	//@Autowired
	//public BharatKoshServiceRequestPayment  bkservice;
	
	
	//@Autowired
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	
		
	// produces= {"application/xml"}  {"application/xml"} MediaType.APPLICATION_JSON_VALUE
	
	
	@PostMapping(value="/send" , produces = {MediaType.APPLICATION_XML_VALUE  }, consumes= MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Order> requestPayment() {
		ShippingAddress shippingAddress= new ShippingAddress();
		BillingAddress bladd = new BillingAddress();
		 Address address = new Address();
		 CartDetails cardDetails = new CartDetails();
		 
		 PaymentMethodMask pmm = new PaymentMethodMask();
		 Shopper shopper = new Shopper();
		 
		 Order order = new Order();
		 

		address.setFirstName("Rahul");
		address.setLastName("prasad");
		address.setAddress1("sec 62");
		address.setAddress2("noida");
		address.setPostalCode("201301");
		address.setCity("noida");
		address.setStateregion("GTB");
		address.setState("UP");
		address.setCountryCode("india");
		address.setMobileNumber("9856784560");
		
		
		//card details start
		
		
		cardDetails.setAmount("100");
		cardDetails.setDdoCode("CDOT ");
		cardDetails.setDescription("Purchasing plane");
		cardDetails.setOrderContent("new boeing");
		cardDetails.setPaoCode("PAO");
		cardDetails.setPaymentTypeId("DEBIT CARD ID");
		//	pMTm6rjYF7eP27Vsz6GX
		
		//card details end
		
		//paymentMethod mask
		pmm.setIncludeCode("Online");
		
		
		
		//paymentMethod mask end
		 
		//Shopper 
		shopper.setShopperEmailAddress("rahulcdot@gmail.com");
		
		// Shopper end
		
		//order
		//attributes adding into oder tag element
		
				order.setInstallationId("10014");
				order.setOrderCode("20170326021134");
				//
		
		order.setCartDetails(cardDetails);
		order.setPayment(pmm);
		order.setShippingAddress(shippingAddress);
		order.setShopper(shopper);
		order.setBillingAddress(bladd);
		
		
		//order
		
		
		bladd.setAddress(address);
		shippingAddress.setAddress(address);
		//List<Order> asList = Arrays.asList(bladd,shippingAddress);
		
		//Address requestAddress = bkservice.requestAddress();
		
		return new ResponseEntity<>(order , HttpStatus.OK);
	}

}
