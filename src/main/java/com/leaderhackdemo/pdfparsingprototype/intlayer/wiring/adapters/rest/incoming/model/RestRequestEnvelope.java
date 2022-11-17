package com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Map;

@Data @Builder
public class RestRequestEnvelope {
    private String requestId;
    private Map<String,String> pathVariables;
    private MultiValueMap<String,String> queryParams;
    private Mono<MultiValueMap<String, Part>> multipartData;
    private Object payload;
}
