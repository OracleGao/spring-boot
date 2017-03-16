package example.handler.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import example.bean.MessageBean;

@Mapper
public interface MessageDao {
	
	@Transactional
	public void insert(@Param("value") MessageBean messageBean);
	
}
