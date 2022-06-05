package uk.gemwire.scram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class FaviconConfiguration {

    @Bean
    public SimpleUrlHandlerMapping customFaviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap(
                "/favicon.ico", faviconRequestHandler()));
        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler
                = new ResourceHttpRequestHandler();
        ClassPathResource classPathResource
                = new ClassPathResource("com/baeldung/images/");
        List<Resource> locations = List.of(classPathResource);
        requestHandler.setLocations(locations);
        return requestHandler;
    }
}
