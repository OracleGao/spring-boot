package example.handler.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import example.bean.MessageBean;

public interface MessageDao {
	
	public String insert(@Param("value") MessageBean messageBean);
	public List<MessageBean> query();
	
}
