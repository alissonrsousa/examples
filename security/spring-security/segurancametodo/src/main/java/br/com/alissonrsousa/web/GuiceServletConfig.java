package br.com.alissonrsousa.web;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import br.com.alissonrsousa.resource.TesteResource;
import br.com.alissonrsousa.resource.impl.TesteResourceImpl;

public class GuiceServletConfig extends GuiceServletContextListener {
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {
			
			@Override
			protected void configureServlets() {
				bind(TesteResource.class).to(TesteResourceImpl.class);
				
				/*********************SERVLET***********************/
				Map<String, String> params = new HashMap<String, String>();
				params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
				serve("/rest/*").with(GuiceContainer.class, params);
				/*********************SERVLET***********************/
				
			}
		});
	}
}