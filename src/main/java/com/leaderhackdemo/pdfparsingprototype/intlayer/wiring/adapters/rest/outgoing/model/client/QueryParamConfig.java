package com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.outgoing.model.client;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class QueryParamConfig {
    CollectionFormat collectionFormat;
    String name;
    Object value;
}
