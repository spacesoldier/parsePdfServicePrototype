package com.leaderhackdemo.pdfparsingprototype.features.loading.service;

import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.model.RestRequestEnvelope;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface LoadPDFService {

    void setIncomingRequestsSink(BiConsumer<String,Object> requestSink);
    Function<RestRequestEnvelope, Object> transformRequest();
}
