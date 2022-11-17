package com.leaderhackdemo.pdfparsingprototype;

import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesRequest;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesResponse;
import com.leaderhackdemo.pdfparsingprototype.features.loading.spec.LoadPDFApiSpec;
import com.leaderhackdemo.pdfparsingprototype.features.parsing.model.ParsedContentRequest;
import com.leaderhackdemo.pdfparsingprototype.features.parsing.model.ParsedContentResponse;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.EndpointAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class ServiceConfig {
    @Autowired
    EndpointAdapter endpointAdapter;

    @Bean
    RouterFunction<ServerResponse> initFeatureOneAPI(){
        return route(
                GET("/api/pdfjson"),
                req -> endpointAdapter.forwardRequestToLogic(
                        ParsedContentRequest.class,
                        req,
                        ParsedContentResponse.class
                )
        );
    }

    @Bean
    @LoadPDFApiSpec
    RouterFunction<ServerResponse> initFeatureTwoAPI(){
        return route(
                                RequestPredicates.POST("/api/loadfiles")
                                        .and(RequestPredicates.accept(MediaType.MULTIPART_FORM_DATA)),
                                req ->  endpointAdapter.forwardRequestToLogic(
                                                                                String.class,
                                                                                LoadPdfFilesRequest.class,
                                                                                req,
                                                                                LoadPdfFilesResponse.class
                                                                              )
        );
    }

}
