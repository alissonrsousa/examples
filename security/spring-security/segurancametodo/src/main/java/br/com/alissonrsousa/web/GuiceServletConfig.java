package br.com.alissonrsousa.web;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.wideplay.warp.jpa.JpaPersistenceStrategy;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

import br.com.alissonrsousa.resource.TesteResource;
import br.com.alissonrsousa.resource.impl.TesteResourceImpl;
import br.com.digicade.commons.annotation.DataSourceDefault;
import br.com.digicade.commons.util.DgProperties;

public class GuiceServletConfig extends GuiceServletContextListener {
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {
			
			@Override
			protected void configureServlets() {
				JpaPersistenceStrategy jpa = JpaPersistenceStrategy.builder().unit("dbmubdv").annotatedWith(DataSourceDefault.class).build();
				install(PersistenceService.using(jpa).across(UnitOfWork.TRANSACTION).buildModule());
				
				bind(TesteResource.class).to(TesteResourceImpl.class);
				
				DgProperties properties = new DgProperties();
                requestInjection(properties);
				
				/*********************SERVLET***********************/
				Map<String, String> params = new HashMap<String, String>();
				params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
				serve("/rest/*").with(GuiceContainer.class, params);
				/*********************SERVLET***********************/
				
			}
		});
	}
}