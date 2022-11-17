package com.leaderhackdemo.pdfparsingprototype.features.aggregation.service;

import com.leaderhackdemo.pdfparsingprototype.features.aggregation.model.ServiceAggregationInfo;
import com.leaderhackdemo.pdfparsingprototype.features.aggregation.model.ServiceAggregationNotification;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesRequest;

public interface RequestAggregationService {
    void initializeNewRequest(LoadPdfFilesRequest loadRequest);

    Object receiveNotification(ServiceAggregationNotification notification);
}
