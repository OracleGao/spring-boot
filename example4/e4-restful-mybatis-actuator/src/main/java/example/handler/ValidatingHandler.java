package example.handler;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.handler.service.ValidatingService;

@RestController
@RequestMapping("/validating")
public class ValidatingHandler {
	
	@Autowired
	private ValidatingService validatingService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String onGetValidating(@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			@RequestParam(value = "nonce", required = true) String nonce,
			@RequestParam(value = "echostr", required = true) String echostr) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		if (validatingService.isValid(signature, nonce, timestamp)) {
			return echostr;
		}
		return null;
	}
	
}
