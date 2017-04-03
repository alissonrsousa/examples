package br.com.alissonrsousa.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;


import com.google.inject.Singleton;

@Singleton
public class ServiceAPIFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String name = "digicade";
		String password = "#digicade#";
		String authString = name + ":" + password;
		
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);

		CustomHttpServletRequest customHttpServletRequest = new CustomHttpServletRequest((HttpServletRequest) request);
		customHttpServletRequest.addHeader("authorization", "Basic " + authStringEnc);
		
		chain.doFilter(customHttpServletRequest, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}