package com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.kafka.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class DataConnection{
    private String name;
    private DataProducer producer;
    private DataConsumer consumer;

}