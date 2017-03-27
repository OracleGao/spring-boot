package example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import example.bean.MessageBean;
import example.config.MessageConfig;
import example.respository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageConfig messageConfig;
	
	@Autowired
	private MessageRepository messageRepository;
	
	public MessageBean processMessage(MessageBean requestMessageBean) {
		messageRepository.save(requestMessageBean);
		MessageBean responseMessageBean = new MessageBean();
		responseMessageBean.setFromUserName(requestMessageBean.getToUserName());
		responseMessageBean.setToUserName(requestMessageBean.getFromUserName());
		responseMessageBean.setContent(messageConfig.getResprefix() + requestMessageBean.getContent());
		return responseMessageBean;
	}
	
	public Page<MessageBean> query(String name, Pageable pageable) {
		return messageRepository.findAll((root, query, cb) -> {
			//query.where(cb.like(root.get("toUserName"), "%" + name + "%"));
			return cb.and(cb.like(root.get("fromUserName"), "%" + name + "%"), cb.like(root.get("content"), "%" + "你" + "%"));
		}, pageable);
	}
}
