package com.expedia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private Properties prop = new Properties();
	
	public Config(String configPath) throws IOException {
		FileInputStream fis = new FileInputStream(configPath);
		prop.load(fis);
		fis.close();
	}
	
	public Properties getProp() {
		return this.prop;
	}
	
	public boolean keyExists(String keyName) {
		return prop.containsKey(keyName);
	}
	
	public String getPropertyValue(String keyName) {
		if(keyExists(keyName)) {
			return prop.getProperty(keyName);
		} else {
			return null;
		}
	}
}