package Runner;


import ApiModel.VkData;
import org.xml.sax.SAXException;
import sax.DataHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Run {

	private static final String ApiData_XML = "ApiData.xml";

	public static VkData VkDataCollector()
			throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

	
	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	SAXParser saxParser = saxParserFactory.newSAXParser();
	DataHandler DataHandler= new DataHandler();
	saxParser.parse(new File(ApiData_XML), DataHandler);
	List<VkData> VkData = DataHandler.getVkData();
	//VkData.forEach(apiData -> System.out.println(VkData));
	return VkData.get(0);

}
}
	