package example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.bean.MessageBean;
import example.config.MessageConfig;
import example.dao.MessageDao;

@Service
public class MessageService {
	
	@Autowired
	private MessageConfig messageConfig;
	
	@Autowired
	private MessageDao messgeDao;
	
	public MessageBean processMessage(MessageBean requestMessageBean) {
		insert(requestMessageBean);
		MessageBean responseMessageBean = new MessageBean();
		responseMessageBean.setFromUserName(requestMessageBean.getToUserName());
		responseMessageBean.setToUserName(requestMessageBean.getFromUserName());
		responseMessageBean.setContent(messageConfig.getResprefix() + requestMessageBean.getContent());
		return responseMessageBean;
	}
	
	@Transactional
	public void insert(MessageBean messageBean){
		messgeDao.insert(messageBean);
	}
	
	public List<MessageBean> query() {
		return messgeDao.query();
	}
}
