package example.respository;

import example.bean.MessageBean;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends JpaRepository<MessageBean, Long>, JpaSpecificationExecutor<MessageBean> {
	public Page<MessageBean> findByFromUserNameContaining(String fromUserName, Pageable pageable);

}
