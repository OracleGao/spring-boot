package example.handler.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.config.ValidatingConfig;
import example.handler.utils.CipherUtils;

@Service
public class ValidatingService {
	
	@Autowired
	private ValidatingConfig validatingConfig;

	public boolean isValid(String signature, String nonce, String timestamp)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String plaintext = Stream.of(validatingConfig.getToken(), timestamp, nonce).sorted()
				.collect(Collectors.joining());
		String ciphertext = CipherUtils.hexDigest(validatingConfig.getCipher(), plaintext,
				validatingConfig.getEncoding());
		return ciphertext.equals(signature);
	}
	
}
