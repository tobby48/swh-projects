package com.github.swhacademy.chatting.server;


import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.github.swhacademy.chatting.server.websocket.WebSocketEndpoint;
import com.sun.jersey.spi.container.servlet.ServletContainer;


/**
 * <pre>
 * 패키지명 : com.github.tobby48.opensource.server
 * 파일명 : JettyServer.java
 * 설명 : JettyServer
 * 
 * </pre>
 * 
 * @since : 2018. 2. 1.
 * @author : tobby48
 * @version : v1.0
 */
public class JettyServer {
	private static final Logger logger = LogManager.getLogger(JettyServer.class.getName());
	
	private void startJetty() {
		YmlConfiguration config = ConfigurationSingleton.getInstance();
        System.out.println(config);
        
		final Server cxServer = new Server();
		ServerConnector connector = new ServerConnector(cxServer);
		connector.setHost("127.0.0.1");
		connector.setPort(3001);
		connector.setIdleTimeout(30000);
		connector.setSoLingerTime(30000);
		cxServer.addConnector(connector);
		
		//	ContextHandler(파일서버, 웹 어플리케이션, 웹소켓, 웹서비스) 모두 담기 위한 Collection
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		
		//	파일서버 ContextHandler 생성
		ContextHandler resourceContext = createFileServletContextHandler();
		
		//	웹 어플리케이션 ContextHandler 생성
	    final WebAppContext webApp = setupWebAppContext(contexts);
	    
	    //	웹 서비스 서블릿 추가
	    setWebServiceServletContextHandler(webApp);
	    
	    //	웹 소켓 서블릿 추가
	    setWebSocketServletContextHandler(webApp);
		
		contexts.setHandlers(new Handler[]{webApp, resourceContext});
		cxServer.setHandler(contexts);
        try {
			cxServer.start();
			logger.info("서버에 접속하였습니다.");
			cxServer.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override public void run() {
				logger.info("서버를 종료합니다.");
				try {
					cxServer.stop();
				} catch (Exception e) {
				}
			}
		});
    }
	
	private void setWebSocketServletContextHandler(WebAppContext webApp) {
		ServletHolder holder = new ServletHolder(new WebSocketServlet() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void configure(WebSocketServletFactory factory) {
				// TODO Auto-generated method stub
				factory.register(WebSocketEndpoint.class);
			}
		});
		holder.setName("ws");
		webApp.addServlet(holder, "/ws/*");
    }
	
	private void setWebServiceServletContextHandler(WebAppContext webApp) {
		ServletHolder holder = new ServletHolder(ServletContainer.class);
		holder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
		holder.setInitParameter("com.sun.jersey.config.property.packages", "com.github.swhacademy.chatting.server.webservice");
		holder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");		//	json 
		webApp.addServlet(holder, "/api/*");
    }
	
	private ContextHandler createFileServletContextHandler() {
		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setResourceBase(".");
		
		ContextHandler resourceContext = new ContextHandler();
		resourceContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "true");
		resourceContext.setContextPath("/files/*");
        resourceContext.setHandler(resource_handler);
        return resourceContext;
    }
	
	private WebAppContext setupWebAppContext(ContextHandlerCollection contexts) {

		WebAppContext webApp = new WebAppContext();
		
		//	dev mode
		String webappDirLocation = "C:/Users/허석/git/class1/server/src/main/webapp/";
		webApp.setContextPath("/");
		webApp.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
		webApp.setResourceBase(webappDirLocation);
		webApp.addServlet(new ServletHolder(new DefaultServlet()), "/*");
		webApp.addFilter(new FilterHolder(CorsFilter.class), "/*", EnumSet.allOf(DispatcherType.class));
		contexts.addHandler(webApp);

		return webApp;

	}
	
	public static void main(String[] args){
		JettyServer server = new JettyServer();
		server.startJetty();
	}
}
