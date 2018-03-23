package com.github.swhacademy.chatting.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.github.swhacademy.chatting.server.ConfigurationSingleton;
import com.github.swhacademy.chatting.server.YmlConfiguration;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * <pre>
 * com.github.swhacademy.chatting.rabbitmq 
 * ChatMessageConsumer.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2018. 3. 23.
 * @author : 허석
 * @version : v1.0
 */
public class ChatMessageConsumer {

	private final String QUEUE_NAME = "chat";
	private final String EXCHANGE_NAME = "broker";
	private final String EXCHANGE_TYPE = "topic";
	
	public void start() throws IOException, TimeoutException{
		YmlConfiguration config = ConfigurationSingleton.getInstance();
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(config.getRabbitmq().getUsername());
		factory.setPassword(config.getRabbitmq().getPassword());
		factory.setHost(config.getRabbitmq().getHost());
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true);
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, QUEUE_NAME);
		
		System.out.println("Consumer 대기중입니다.");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(envelope.getRoutingKey());
				System.out.println("Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);


	}
	public static void main(String args[]){
		ChatMessageConsumer server = new ChatMessageConsumer();
		try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
