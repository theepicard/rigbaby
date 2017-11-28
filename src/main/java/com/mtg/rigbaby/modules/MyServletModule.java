package com.mtg.rigbaby.modules;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.google.api.control.ServiceManagementConfigFilter;
import com.google.api.control.extensions.appengine.GoogleAppEngineControlFilter;
import com.google.api.server.spi.guice.EndpointsModule;
import com.google.common.collect.ImmutableList;
import com.mtg.rigbaby.resources.RigResource;

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

    bind(RigResource.class).toInstance(new RigResource());
    configureEndpoints("/_ah/api/*", ImmutableList.of(RigResource.class));
  }
}
