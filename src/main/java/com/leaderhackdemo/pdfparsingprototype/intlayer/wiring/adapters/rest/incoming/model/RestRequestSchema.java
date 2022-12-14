package com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.model;

import org.springframework.util.MultiValueMap;

import java.util.Map;

public class RestRequestSchema<T> {
    public String requestId;
    public Map<String,String> pathVariables;
    public MultiValueMap<String,String> queryParams;
    public T payload;
}
