package com.mtg.rigbaby.modules;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.apache.http.impl.client.DefaultHttpClient;

import com.google.api.control.ServiceManagementConfigFilter;
import com.google.api.control.extensions.appengine.GoogleAppEngineControlFilter;
import com.google.api.server.spi.guice.EndpointsModule;
import com.google.common.collect.ImmutableList;
import com.google.inject.Provides;
import com.mtg.rigbaby.resources.OperatorResource;
import com.mtg.rigbaby.resources.RigResource;
import com.mtg.rigbaby.server.external.KasaClient;

public class MyServletModule extends EndpointsModule {
  
  @Override
  public void configureServlets() {
    super.configureServlets();

    bind(ServiceManagementConfigFilter.class).in(Singleton.class);
    filter("/_ah/api/*").through(ServiceManagementConfigFilter.class);

    Map<String, String> apiController = new HashMap<String, String>();
    apiController.put("endpoints.projectId", "rigbaby-186818");
    apiController.put("endpoints.serviceName", "rigbaby-186818.appspot.com");

    bind(GoogleAppEngineControlFilter.class).in(Singleton.class);
    filter("/_ah/api/*").through(GoogleAppEngineControlFilter.class, apiController);

    // How to add additional endpoints, this overrides web.xml
    
    // Step 1: Bind an instance of your resource to the resource class for guice DI
    bind(RigResource.class).toInstance(new RigResource());
    bind(OperatorResource.class).toInstance(new OperatorResource());
    requestStaticInjection(OperatorResource.class);
    
    // Step 2: Add your resource to the list of configured endpoints
    configureEndpoints("/_ah/api/*", ImmutableList.of(RigResource.class, OperatorResource.class));
  }
  
  @Provides
  KasaClient provideKasaClient() {
    KasaClient kasaClient = new KasaClient(new DefaultHttpClient());
    return kasaClient;
  }
}
