package sax;

import ApiModel.VkData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;



public class DataHandler extends DefaultHandler {
	private List<VkData> VkData;
	private VkData apiData;
	boolean bOwner_id = false;
	boolean bV = false;
	boolean bMessage = false;
	

	public List<VkData> getVkData() {
		return VkData;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equalsIgnoreCase("ApiData")) {
			apiData = new VkData();
			if (VkData == null) {
				VkData = new ArrayList<>();
			}}
			else if (qName.equalsIgnoreCase("owner_id")) {
			bOwner_id=true;
			} 
			else if (qName.equalsIgnoreCase("v")) {
			bV = true;
		} 
			else if (qName.equalsIgnoreCase("message")) {
			bMessage = true;
		}
	
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("ApiData")) {
			VkData.add(apiData);
		}
	}
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bOwner_id) {
			apiData.setOwner_id(new String(ch, start, length));
			bOwner_id = false;
		} else if (bV) {
			apiData.setV(new String(ch, start, length));
			bV = false;
		} else if (bMessage) {
			apiData.setMessage(new String(ch, start, length));
			bMessage = false;
		} 

	}
}
