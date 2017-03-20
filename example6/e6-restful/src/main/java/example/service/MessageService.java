package example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.bean.MessageBean;
import example.config.MessageConfig;

@Service
public class MessageService {
	
	@Autowired
	private MessageConfig messageConfig;
	
	public MessageBean processMessage(MessageBean requestMessageBean) {
		MessageBean responseMessageBean = new MessageBean();
		responseMessageBean.setFromUserName(requestMessageBean.getToUserName());
		responseMessageBean.setToUserName(requestMessageBean.getFromUserName());
		responseMessageBean.setContent(messageConfig.getResprefix() + requestMessageBean.getContent());
		return responseMessageBean;
	}
	
}
