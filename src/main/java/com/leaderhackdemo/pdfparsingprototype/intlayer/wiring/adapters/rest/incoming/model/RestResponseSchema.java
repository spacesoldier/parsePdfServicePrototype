package com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.model;

import org.springframework.lang.Nullable;

import java.util.List;

public class RestResponseSchema<T> {
    @Nullable
    public OperationStatus status;

    @Nullable
    public List<OperationMessage> messages;

    @Nullable
    public T data;
}
