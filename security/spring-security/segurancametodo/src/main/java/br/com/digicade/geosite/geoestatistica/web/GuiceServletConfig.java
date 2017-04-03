package br.com.digicade.geosite.geoestatistica.web;

import java.util.HashMap;
import java.util.Map;

import br.com.digicade.commons.annotation.DataSourceDefault;
import br.com.digicade.commons.util.DgProperties;
import br.com.digicade.geosite.geoestatistica.resource.EstatisticaResource;
import br.com.digicade.geosite.geoestatistica.resource.LocalidadeResource;
import br.com.digicade.geosite.geoestatistica.resource.MotivoNaoEspacializadoResource;
import br.com.digicade.geosite.geoestatistica.resource.TipoConsultaResource;
import br.com.digicade.geosite.geoestatistica.resource.UsuarioResource;
import br.com.digicade.geosite.geoestatistica.resource.impl.EstatisticaResourceImpl;
import br.com.digicade.geosite.geoestatistica.resource.impl.LocalidadeResourceImpl;
import br.com.digicade.geosite.geoestatistica.resource.impl.MotivoNaoEspacializadoResourceImpl;
import br.com.digicade.geosite.geoestatistica.resource.impl.TipoConsultaResourceImpl;
import br.com.digicade.geosite.geoestatistica.resource.impl.UsuarioResourceImpl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.wideplay.warp.jpa.JpaPersistenceStrategy;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

public class GuiceServletConfig extends GuiceServletContextListener {
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {
			
			@Override
			protected void configureServlets() {
				JpaPersistenceStrategy jpa = JpaPersistenceStrategy.builder().unit("dbmubdv").annotatedWith(DataSourceDefault.class).build();
				install(PersistenceService.using(jpa).across(UnitOfWork.TRANSACTION).buildModule());
				
				bind(TipoConsultaResource.class).to(TipoConsultaResourceImpl.class);
				bind(LocalidadeResource.class).to(LocalidadeResourceImpl.class);
				bind(UsuarioResource.class).to(UsuarioResourceImpl.class);
				bind(EstatisticaResource.class).to(EstatisticaResourceImpl.class);
				bind(MotivoNaoEspacializadoResource.class).to(MotivoNaoEspacializadoResourceImpl.class);
				
				DgProperties properties = new DgProperties();
                requestInjection(properties);
				
				/*********************SERVLET***********************/
				Map<String, String> params = new HashMap<String, String>();
				params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
				serve("/rest/*").with(GuiceContainer.class, params);
				
				Map<String, String> dgsecurity = new HashMap<String, String>();
				dgsecurity.put("targetUri", properties.getProperties("application.properties").getProperty("ws.security"));
				
				/*********************SERVLET***********************/
				
			}
		});
	}
}