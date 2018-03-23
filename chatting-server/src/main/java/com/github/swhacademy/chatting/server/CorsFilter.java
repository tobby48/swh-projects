/*
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.swhacademy.chatting.server;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <pre>
 * com.github.swhacademy.chatting.server 
 * CorsFilter.java
 *
 * 설명 : CrossDomain 부분 처리
 * </pre>
 * 
 * @since : 2018. 3. 23.
 * @author : 허석
 * @version : v1.0
 */
public class CorsFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
			HttpServletResponse resp = ((HttpServletResponse) response);
			addCorsHeaders(resp);
			return;
		}

		if (response instanceof HttpServletResponse) {
			HttpServletResponse alteredResponse = ((HttpServletResponse) response);
			addCorsHeaders(alteredResponse);
		}
		filterChain.doFilter(request, response);
	}

	private void addCorsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		response.addHeader("Access-Control-Max-Age", "3600");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		DateFormat fullDateFormatEN = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.KOREA);
		response.addHeader("Date", fullDateFormatEN.format(new Date()));
	}

	public void destroy() {}

	public void init(FilterConfig filterConfig) throws ServletException {}
}
