/**
 * 패키지명 : com.github.swhacademy.chatting.server
 * 파일명 : ConfigurationSingleton.java
 * 설명 : 
 * 
 * </pre>
 * 
 * @since : 2018. 2. 5.
 * @author : tobby48
 * @version : v1.0
 */
package com.github.swhacademy.chatting.server;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ConfigurationSingleton {
	private static ConfigurationSingleton singleton;
	private YmlConfiguration config;
	
	public YmlConfiguration getConfig() {
		return config;
	}
	private ConfigurationSingleton(){
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
        	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.yaml");
        	config = mapper.readValue(is, YmlConfiguration.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	public static YmlConfiguration getInstance(){
		if(singleton == null){
			singleton = new ConfigurationSingleton();
		}
		return singleton.getConfig();
	}
}
