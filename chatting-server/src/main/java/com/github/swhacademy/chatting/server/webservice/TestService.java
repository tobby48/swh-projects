package com.github.swhacademy.chatting.server.webservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <pre>
 * 패키지명 : com.github.tobby48.opensource.server.webservice
 * 파일명 : LectureService.java
 * 설명 : LectureService 웹서비스
 * 
 * </pre>
 * 
 * @since : 2018. 2. 1.
 * @author : tobby48
 * @version : v1.0
 */
@Path("/service")
public class TestService {

	private static final Logger logger = LogManager.getLogger(TestService.class.getName());
	
	public static final String WEBSERVICE_IN_CONTEXTS = "[Method {}] {}";
	
	public TestService() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Collection<String> select() {
		logger.info(WEBSERVICE_IN_CONTEXTS, HttpMethod.GET, "select()");
		List<String> list = new ArrayList<String>();
		list.add(new String("하하"));
		return list;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String select(@PathParam("id") String id) {
		logger.info(WEBSERVICE_IN_CONTEXTS, HttpMethod.GET, "select()");
		return new String();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String insert(String obj) {
		logger.info(WEBSERVICE_IN_CONTEXTS, HttpMethod.POST);
		return obj;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(String obj) {
		logger.info(WEBSERVICE_IN_CONTEXTS, HttpMethod.PUT);
		return obj;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String delete(@PathParam("id") String id) {
		logger.info(WEBSERVICE_IN_CONTEXTS, HttpMethod.DELETE);
		return new String();
	}

}