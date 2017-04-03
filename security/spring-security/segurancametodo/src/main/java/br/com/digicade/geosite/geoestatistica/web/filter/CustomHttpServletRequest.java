package br.com.digicade.geosite.geoestatistica.web.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomHttpServletRequest extends HttpServletRequestWrapper {
	private Map<String, String> customHeaderMap = null;
	//private Map<String, String[]> customParameterMap = null;

	public CustomHttpServletRequest(HttpServletRequest request) {
		super(request);
		customHeaderMap = new HashMap<String, String>();
		//customParameterMap = new HashMap<String, String[]>();
	}

	public void addHeader(String name, String value) {
		customHeaderMap.put(name, value);
	}

	@Override
	public String getHeader(String name) {
		String paramValue = super.getHeader(name);
		if (paramValue == null) {
			paramValue = customHeaderMap.get(name);
		}
		return paramValue;
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		List<String> names = Collections.list(super.getHeaderNames());
		names.addAll(customHeaderMap.keySet());
		return Collections.enumeration(names);
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		String value = customHeaderMap.get(name);
		if (value != null) {
			List<String> values = new ArrayList<String>();
			values.add(value);
			return Collections.enumeration(values);
		} else {
			return super.getHeaders(name);
		}
	}

//	public void setParameter(String name, String value) {
//		customParameterMap.put(name, new String[]{value});
//	}
//
//	public void setParameter(String name, String[] value) {
//		customParameterMap.put(name, value);
//	}
//
//	@Override
//	public Map<String, String[]> getParameterMap() {
//		Map<String, String[]> parameterMap = new HashMap<String, String[]>();
//		parameterMap.putAll(customParameterMap);
//		parameterMap.putAll(super.getParameterMap());
//		return Collections.unmodifiableMap(parameterMap);
//	}
//
//	@Override
//	public String getParameter(String name) {
//		String value = super.getParameter(name);
//		if(value!= null){
//			return value;
//		}
//		
//		String[] strings = this.getParameterMap().get(name);
//		if (strings != null) {
//			return strings[0];
//		}
//		
//		return null; 
//	}
//
//	@Override
//	public Enumeration<String> getParameterNames() {
//		return Collections.enumeration(this.getParameterMap().keySet());
//	}
//
//	@Override
//	public String[] getParameterValues(String name) {
//		return this.getParameterMap().get(name);
//	}
//
//	@Override
//	public String getQueryString() {
//		String queryString = super.getQueryString();
//
//		if(queryString == null){
//			queryString= "";
//		}
//		for (String name : this.customParameterMap.keySet()) {
//			if (queryString != null && !"".equals(queryString)) {
//				queryString += "&";
//			}
//
//			queryString += name + "=" + this.getParameterMap().get(name)[0];
//		}
//
//		return queryString;
//	}
}