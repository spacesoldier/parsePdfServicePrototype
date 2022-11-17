package com.leaderhackdemo.pdfparsingprototype.intlayer.routing.model;

import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

@Data @Builder
public class RoutingUnit {
    private String name;
    private Class inputType;
    private Function call;
}
