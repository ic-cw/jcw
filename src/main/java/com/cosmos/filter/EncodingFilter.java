package com.cosmos.filter;

import java.io.*;

import javax.servlet.*;

public class EncodingFilter implements Filter {

	private String encoding = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("utf");
		System.out.println(filterConfig.getFilterName() + "필터 시작됨");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request.getCharacterEncoding() == null) {
			if(encoding != null) {
				request.setCharacterEncoding(encoding);
				response.setCharacterEncoding(encoding);
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
