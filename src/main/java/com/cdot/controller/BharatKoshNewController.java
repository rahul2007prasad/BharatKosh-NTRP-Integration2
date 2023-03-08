package com.cdot.controller;

import java.io.StringWriter;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdot.dao.Address;
import com.cdot.dao.BillingAddress;
import com.cdot.dao.CartDetails;
import com.cdot.dao.Order;
import com.cdot.dao.PaymentMethodMask;
import com.cdot.dao.ShippingAddress;
import com.cdot.dao.Shopper;
import com.cdot.xmlfromstring.XmlDigitalSignatureGeneratorFromStringXML;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@RestController
public class BharatKoshNewController {
	String pubkey ="E://pkjs//publickey.key";
	String prvkey ="E://pkjs//privatekey.key";
	
	//@Autowired
	//XmlDigitalSignatureGeneratorFromStringXML xmlDigitalString = new XmlDigitalSignatureGeneratorFromStringXML() ;
	//XmlDigitalSignatureGenerator xmlDigitalString = new XmlDigitalSignatureGenerator();
	
	XmlDigitalSignatureGeneratorFromStringXML xmlDigitalString= new XmlDigitalSignatureGeneratorFromStringXML();
	String result = "";
	String outputxmlString="";
	
	
	@PostMapping(value="/request" , produces = {MediaType.APPLICATION_XML_VALUE  }, consumes= MediaType.APPLICATION_XML_VALUE)
	//public ResponseEntity<String> sendRequest() {
	//public ResponseEntity<Order> sendRequest() {	
	public ResponseEntity<String> sendRequest() {	
				
		
		 Order order = new Order();
		
		JAXBContext jaxbContext = null;
        try {

            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Order.class}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

           
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ShippingAddress shippingAddress= new ShippingAddress();
    		BillingAddress bladd = new BillingAddress();
    		 Address address = new Address();
    		 CartDetails cardDetails = new CartDetails();
    		 
    		 PaymentMethodMask pmm = new PaymentMethodMask();
    		 Shopper shopper = new Shopper();
    		 
    		
    		 

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
            
    		//Result result=null;
    		//jaxbMarshaller.marshal(order , result);
    		
    		
    		StringWriter sw = new StringWriter();
    		//m.marshal(cc, sw);
    		jaxbMarshaller.marshal(order, sw);
    		 result = sw.toString();
    		
            jaxbMarshaller.marshal(order, System.out);
            System.out.println("+++++++++++++++++++++++++++++");
            //System.out.println(result.toString());
            System.out.println("order string"+result);

            // XML Unmarshalling
            /*File file = new File("C:\\test\\fruit.xml");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
           
            System.out.println(o);*/

            
            
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        Encoder encoder = Base64.getMimeEncoder();
        //byte[] encode = encoder.encode(order.toString().getBytes());
        String encodeToString = encoder.encodeToString(order.toString().getBytes());
        System.out.println("+++++++++++++++++++++++");
        System.out.println(encodeToString);
        
        
        String generateXMLDigitalSignature = xmlDigitalString.generateXMLDigitalSignature(result, outputxmlString, prvkey, pubkey);
         //ResponseEntity<Order> responseEntity = new ResponseEntity<>(order, HttpStatus.OK);
        
       // return new ResponseEntity<>(order , HttpStatus.OK);
        //return new ResponseEntity<>(encodeToString , HttpStatus.OK);
        
        return new ResponseEntity<>(generateXMLDigitalSignature , HttpStatus.OK);
        
		
	}
	
	
	
	
	
	
	@GetMapping(value="/recive" , produces = {MediaType.APPLICATION_XML_VALUE  }, consumes= MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> reciveRequest(@RequestBody String baseEncString) {
		
		Decoder decoder = Base64.getMimeDecoder();
		 byte[] decode = decoder.decode(baseEncString);
		 String decodeValue = new String(decode);
		 
		 System.out.println(decodeValue.toString());
		
		return new ResponseEntity<>(decodeValue.toString() , HttpStatus.OK);
	}

}
