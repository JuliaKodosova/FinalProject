package my_project;

import ApiModel.VkData;
import Runner.Run;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URISyntaxException;
public class TestAPI {
API api;
private static final Logger logger = Logger.getLogger(TestAPI.class);

@Before
public void setup() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
	
	VkData VkData=Run.VkDataCollector();
	api=new API(VkData);
}

@Test
public void testGetPostOnWall() throws ClientProtocolException, IOException, URISyntaxException {
	Assert.assertEquals(200, api.getWallPosts().getStatusLine().getStatusCode());
	logger.info("Список постов на стене проверен");
}

@Test
public void testAddPostOnWall() throws ClientProtocolException, IOException, URISyntaxException {
	Assert.assertEquals(200, api.addPostOnWall().getStatusLine().getStatusCode());
	logger.info("Пост успешно добавлен");
}

@Test
public void testDeletePostOnWall() throws ClientProtocolException, IOException, URISyntaxException {
	api.addPostOnWall();
	Assert.assertEquals(Integer.parseInt(api.deletePostOnWall()), 1);
	logger.info("Пост успешно удален");
}

@Test
public void testEditPostOnWall() throws ClientProtocolException, IOException, URISyntaxException {
	api.addPostOnWall();
	Assert.assertEquals(200, api.editPostOnWall().getStatusLine().getStatusCode());
	logger.info("Пост успешно отредактирован");
}

}
