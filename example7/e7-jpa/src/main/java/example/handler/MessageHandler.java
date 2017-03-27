package example.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.bean.MessageBean;
import example.respository.MessageRepository;
import example.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageHandler {
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageBean onGetReceiveMessage(@RequestBody MessageBean messageBean) {
		return messageService.processMessage(messageBean);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<MessageBean> onGet(@RequestParam String name, Pageable pageable) {
		//return messageRepository.findByFromUserNameContaining(name, pageable);
		return messageService.query(name, pageable);
	}
	
}
