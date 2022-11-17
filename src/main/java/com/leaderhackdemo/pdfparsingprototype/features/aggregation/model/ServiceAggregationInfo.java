package com.leaderhackdemo.pdfparsingprototype.features.aggregation.model;

import com.leaderhackdemo.pdfparsingprototype.features.loading.model.PdfFileDescription;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class ServiceAggregationInfo {
    private String requestId;
    private Integer partsTotal;
    private List<PdfFileDescription> partsReady;
}
