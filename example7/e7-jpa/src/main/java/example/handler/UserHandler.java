package example.handler;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.bean.UserBean;
import example.respository.UserRespository;

@RestController
@RequestMapping("/user")
public class UserHandler {

	@Autowired
	private UserRespository userRespository;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBean onPut(@RequestBody UserBean userBean) {
		return userRespository.save(userBean);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<UserBean> onGet(Pageable pageable) {
		System.out.println(pageable);
		return userRespository.findAll(pageable);
	}
	
}
