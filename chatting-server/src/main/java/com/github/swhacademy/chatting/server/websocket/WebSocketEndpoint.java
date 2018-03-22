package com.github.swhacademy.chatting.server.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * <pre>
 * 패키지명 : com.github.tobby48.opensource.server.websocket
 * 파일명 : WebSocketEndpoint.java
 * 설명 : WebSocketEndpoint 웹소켓
 * 
 * </pre>
 * 
 * @since : 2018. 2. 1.
 * @author : tobby48
 * @version : v1.0
 */
@WebSocket
public class WebSocketEndpoint{
	private static final Logger logger = LogManager.getLogger(WebSocketEndpoint.class.getName());
	private Session session_;

	public Session getSession() {
		return session_;
	}
	public WebSocketEndpoint(){
	}

	@OnWebSocketConnect
	public void onWebSocketConnect(Session session)
	{
		this.session_ = session;
		logger.debug("connection : " + session.getRemoteAddress().toString());
	}

	@OnWebSocketMessage
	public void onMessage(Session session, String message) {
		logger.debug("message : ", message);
	}

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		logger.debug("disconnection : " + this.getSession().getRemoteAddress().toString());
	}

	@OnWebSocketError
	public void onError(Throwable cause) {
		logger.debug("error : " + this.getSession().getRemoteAddress().toString());
		cause.printStackTrace(System.err);
	}
}
