package example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "example.message")
public class MessageConfig {
	private String resprefix;

	public MessageConfig() {
		super();
	}

	public String getResprefix() {
		return resprefix;
	}

	public void setResprefix(String resprefix) {
		this.resprefix = resprefix;
	}
	
}
