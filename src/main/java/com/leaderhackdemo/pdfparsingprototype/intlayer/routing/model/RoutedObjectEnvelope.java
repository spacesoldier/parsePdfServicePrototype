package com.leaderhackdemo.pdfparsingprototype.intlayer.routing.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class RoutedObjectEnvelope {
    private String rqId;
    private String correlId; // for future one-to-many transformations and etc
    private Object payload;
}
