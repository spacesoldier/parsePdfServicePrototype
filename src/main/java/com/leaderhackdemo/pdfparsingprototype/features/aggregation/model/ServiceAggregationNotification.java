package com.leaderhackdemo.pdfparsingprototype.features.aggregation.model;

import com.leaderhackdemo.pdfparsingprototype.features.loading.model.PdfFileDescription;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ServiceAggregationNotification {
    private String requestId;
    private Integer partsTotal;
    private PdfFileDescription partInfo;
}
