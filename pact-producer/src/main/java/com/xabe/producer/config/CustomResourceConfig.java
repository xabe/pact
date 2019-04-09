package com.xabe.producer.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class CustomResourceConfig extends ResourceConfig {

    public CustomResourceConfig() {
        packages("com.xabe.producer.resource");
        register(JacksonFeature.class);
        register(new ObjectMapperContextResolver());
        register(new LoggingFeature());
        property( ServerProperties.BV_FEATURE_DISABLE, true );
        property( ServerProperties.RESOURCE_VALIDATION_IGNORE_ERRORS, true );
        property( ServerProperties.TRACING, "ALL" );
        property( ServerProperties.TRACING_THRESHOLD, "VERBOSE" );
    }
}

