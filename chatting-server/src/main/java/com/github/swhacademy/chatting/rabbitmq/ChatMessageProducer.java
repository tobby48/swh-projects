package com.github.swhacademy.chatting.rabbitmq;

import com.github.swhacademy.chatting.server.ConfigurationSingleton;
import com.github.swhacademy.chatting.server.YmlConfiguration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <pre>
 * com.github.swhacademy.chatting.rabbitmq 
 * ChatMessageProducer.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2018. 3. 23.
 * @author : 허석
 * @version : v1.0
 */
public class ChatMessageProducer {
	
	private final String EXCHANGE_NAME = "broker";
	private final String EXCHANGE_TYPE = "topic";
	private final String ROUTE_KEY_MESSAGE = "chat";
	
	public void start() throws Exception{
		YmlConfiguration config = ConfigurationSingleton.getInstance();
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(config.getRabbitmq().getUsername());
		factory.setPassword(config.getRabbitmq().getPassword());
		factory.setHost(config.getRabbitmq().getHost());
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true);
		String message = "Hello World!";
		channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY_MESSAGE, null, message.getBytes("UTF-8"));

	}
	public static void main(String[] args){
		ChatMessageProducer client = new ChatMessageProducer();
		try {
			client.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
