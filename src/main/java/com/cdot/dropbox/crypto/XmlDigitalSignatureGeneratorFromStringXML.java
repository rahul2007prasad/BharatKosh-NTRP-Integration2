package com.cdot.dropbox.crypto;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collections;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlDigitalSignatureGeneratorFromStringXML {

	
	
	 public XmlDigitalSignatureGeneratorFromStringXML() {
		super();
		// TODO Auto-generated constructor stub
	}



	private  Document getXmlDocument(String xmlStr) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	        try  
	        {  
	            builder = factory.newDocumentBuilder();  
	            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	            return doc;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return null;
	    }
	 
	 
	 
	 /**
	     * Method used to get the KeyInfo
	     *
	     * @param xmlSigFactory
	     * @param publicKeyPath
	     * @return KeyInfo
	     */
	    private KeyInfo getKeyInfo(XMLSignatureFactory xmlSigFactory, String publicKeyPath) {
	        KeyInfo keyInfo = null;
	        KeyValue keyValue = null;
	        PublicKey publicKey = new KryptoUtil().getStoredPublicKey(publicKeyPath);
	        KeyInfoFactory keyInfoFact = xmlSigFactory.getKeyInfoFactory();

	        try {
	            keyValue = keyInfoFact.newKeyValue(publicKey);
	        } catch (KeyException ex) {
	            ex.printStackTrace();
	        }
	        keyInfo = keyInfoFact.newKeyInfo(Collections.singletonList(keyValue));
	        return keyInfo;
	    }
	    
	    
	    
	    
	    /*
	     * Method used to store the signed XMl document
	     */
	    
	    private static String convertDocumentToString(Document doc) {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer;
	        try {
	            transformer = tf.newTransformer();
	            // below code to remove XML declaration
	            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            StringWriter writer = new StringWriter();
	            transformer.transform(new DOMSource(doc), new StreamResult(writer));
	            String output = writer.getBuffer().toString();
	            System.out.println(output);
	            return output;
	        } catch (TransformerException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	    }
	    private void storeSignedDoc(Document doc, String destnSignedXmlFilePath) {
	        TransformerFactory transFactory = TransformerFactory.newInstance();
	        Transformer trans = null;
	        try {
	            trans = transFactory.newTransformer();
	        } catch (TransformerConfigurationException ex) {
	            ex.printStackTrace();
	        }
	        try {
	            StreamResult streamRes = new StreamResult(new File(destnSignedXmlFilePath));
	            trans.transform(new DOMSource(doc), streamRes);
	        } catch (TransformerException ex) {
	            ex.printStackTrace();
	        }
	        System.out.println("XML file with attached digital signature generated successfully ...");
	    }
	    
	    
	    

	    /**
	     * Method used to attach a generated digital signature to the existing
	     * document
	     *
	     * @param originalXmlFilePath
	     * @param destnSignedXmlFilePath
	     * @param privateKeyFilePath
	     * @param publicKeyFilePath
	     */
	    public void generateXMLDigitalSignature(String originalXmlString,
	            String destnSignedXmlString, String privateKeyFilePath, String publicKeyFilePath) {
	        //Get the XML Document object
	        Document doc = getXmlDocument(originalXmlString);
	        //Create XML Signature Factory
	        XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");
	        PrivateKey privateKey = new KryptoUtil().getStoredPrivateKey(privateKeyFilePath);
	        DOMSignContext domSignCtx = new DOMSignContext(privateKey, doc.getDocumentElement());
	        Reference ref = null;
	        SignedInfo signedInfo = null;
	        try {
	            ref = xmlSigFactory.newReference("", xmlSigFactory.newDigestMethod(DigestMethod.SHA1, null),
	                    Collections.singletonList(xmlSigFactory.newTransform(Transform.ENVELOPED,
	                    (TransformParameterSpec) null)), null, null);
	            signedInfo = xmlSigFactory.newSignedInfo(
	                    xmlSigFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
	                    (C14NMethodParameterSpec) null),
	                    xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
	                    Collections.singletonList(ref));
	        } catch (NoSuchAlgorithmException ex) {
	            ex.printStackTrace();
	        } catch (InvalidAlgorithmParameterException ex) {
	            ex.printStackTrace();
	        }
	        //Pass the Public Key File Path 
	        KeyInfo keyInfo = getKeyInfo(xmlSigFactory, publicKeyFilePath);
	        //Create a new XML Signature
	        XMLSignature xmlSignature = xmlSigFactory.newXMLSignature(signedInfo, keyInfo);
	        try {
	            //Sign the document
	            xmlSignature.sign(domSignCtx);
	        } catch (MarshalException ex) {
	            ex.printStackTrace();
	        } catch (XMLSignatureException ex) {
	            ex.printStackTrace();
	        }
	        //Store the digitally signed document inta a location
	        storeSignedDoc(doc, destnSignedXmlString);
	        convertDocumentToString(doc);
	    }

	    
	    
	    public static void main(String args[]) {
	    	String pubkey ="E://pkjs//publickey.key";
	    	String prvkey ="E://pkjs//privatekey.key";
	    	String xmlraw ="E://pkjs//orignalxl.xml";
	    	String xmlcert ="E://pkjs//createdxl.xml";
	    	
	    	String xmlStr="<cartDetails>\n"
	    			+ "        <description>Purchasing plane</description>\n"
	    			+ "        <amount>100</amount>\n"
	    			+ "        <orderContent>new boeing</orderContent>\n"
	    			+ "        <paymentTypeId>DEBIT CARD ID</paymentTypeId>\n"
	    			+ "        <paoCode>PAO</paoCode>\n"
	    			+ "        <ddoCode>CDOT </ddoCode>\n"
	    			+ "    </cartDetails>";
	    	
	     
	    	
	    	XmlDigitalSignatureGeneratorFromStringXML xmlgen = new XmlDigitalSignatureGeneratorFromStringXML();
	    	try {
	    	//xmlgen.generateXMLDigitalSignature(xmlStr,xmlcert,prvkey,pubkey);
	    	}catch (Exception e) {
	    		e.printStackTrace();
				// TODO: handle exception
			}
	    }
	 
	 
}
