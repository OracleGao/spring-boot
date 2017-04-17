package example.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.bean.MessageBean;
import example.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageHandler {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(method = RequestMethod.POST, consumes =MediaType.TEXT_XML_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageBean onGetReceiveMessage(@RequestBody MessageBean messageBean) {
		return messageService.processMessage(messageBean);
	}
	
}
