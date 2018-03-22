/**
 * 패키지명 : com.github.swhacademy.chatting.server
 * 파일명 : YmlConfiguration.java
 * 설명 : 
 * 
 * </pre>
 * 
 * @since : 2018. 2. 5.
 * @author : tobby48
 * @version : v1.0
 */
package com.github.swhacademy.chatting.server;

public class YmlConfiguration {
	
	public class DatabaseConfiguration{
		private String url;
		private String username;
		private String password;
		/**
		 * 
		 */
		public DatabaseConfiguration() {
			// TODO Auto-generated constructor stub
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	
	public class RabbitMQConfiguration{
		private String host;
		private String username;
		private String password;
		/**
		 * 
		 */
		public RabbitMQConfiguration() {
			// TODO Auto-generated constructor stub
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	private DatabaseConfiguration database;
	private RabbitMQConfiguration rabbitmq;
	public DatabaseConfiguration getDatabase() {
		return database;
	}
	public void setDatabase(DatabaseConfiguration database) {
		this.database = database;
	}
	public RabbitMQConfiguration getRabbitmq() {
		return rabbitmq;
	}
	public void setRabbitmq(RabbitMQConfiguration rabbitmq) {
		this.rabbitmq = rabbitmq;
	}
	
}
