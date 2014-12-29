/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.presentation.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Alena_Grouk
 */
public class EncodingFilter implements Filter {

	private String encoding;

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
	}

	@Override
	public void destroy() {
		encoding = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String requestEncoding = request.getCharacterEncoding();
		if (!encoding.equalsIgnoreCase(requestEncoding)) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);

	}

}
