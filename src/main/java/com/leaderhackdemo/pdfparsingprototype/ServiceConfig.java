package com.leaderhackdemo.pdfparsingprototype;

import com.leaderhackdemo.pdfparsingprototype.features.loading.spec.LoadPDFApiSpec;
import com.leaderhackdemo.pdfparsingprototype.features.parsing.model.ParsedContentRequest;
import com.leaderhackdemo.pdfparsingprototype.features.parsing.model.ParsedContentResponse;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.EndpointAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
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

//    @Bean
//    @LoadPDFApiSpec
//    RouterFunction<ServerResponse> initFeatureTwoAPI(){
//        return route(
//                                POST("/api/feature1"),
//                                req ->  endpointAdapter.forwardRequestToLogic(
//                                                                                String.class,
//                                                                                FeatureTwoRequest.class,
//                                                                                req,
//                                                                                FeatureOneResponse.class
//                                                                              )
//        );
//    }

}
