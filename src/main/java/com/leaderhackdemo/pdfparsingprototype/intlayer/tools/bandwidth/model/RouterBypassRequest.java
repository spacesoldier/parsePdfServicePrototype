package com.leaderhackdemo.pdfparsingprototype.intlayer.tools.bandwidth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouterBypassRequest {
    private String requestId;
    private String correlId;
    private Object payload;
}
