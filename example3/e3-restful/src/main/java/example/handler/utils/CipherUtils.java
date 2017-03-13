package example.handler.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class CipherUtils {
	
	public static String hexDigest(String cipher, String plaintext, String encoding) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest messageDigest = MessageDigest.getInstance(cipher);
		messageDigest.update(plaintext.getBytes(encoding));
		return Hex.encodeHexString(messageDigest.digest());
	}
	
}
