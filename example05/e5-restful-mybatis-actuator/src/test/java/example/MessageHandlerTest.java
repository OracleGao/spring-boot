package example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import example.bean.MessageBean;
import example.config.MessageConfig;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MessageHandlerTest {
	@Autowired
	protected TestRestTemplate restTemplate;
	
	@Autowired
	private MessageConfig messageConfig;
	
	@Test
	public void messageHandlerTest() {
		String fromUserName = "Clyne";
		String toUserName = "MessageServer";
		String content = "好好学习，天天向上";
		String xmlBody = "<xml>" 
        + "<fromUserName>" + fromUserName + "</fromUserName>" 
        + "<toUserName>" + toUserName + "</toUserName>" 
        + "<content>" + content + "</content>" 
        + "</xml>";
		String url = "/message";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_XML);
		HttpEntity<String> httpEnitiy = new HttpEntity<>(xmlBody, httpHeaders);
		
		ResponseEntity<MessageBean> responseEntity = restTemplate.postForEntity(url, httpEnitiy, MessageBean.class);
		MessageBean messageBean = responseEntity.getBody();
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals(fromUserName, messageBean.getToUserName());
		Assert.assertEquals(toUserName, messageBean.getFromUserName());
		Assert.assertEquals(messageConfig.getResprefix() + content, messageBean.getContent());
	} 
}
