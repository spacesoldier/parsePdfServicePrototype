package com.leaderhackdemo.pdfparsingprototype.features.aggregation.service;

import com.leaderhackdemo.pdfparsingprototype.features.aggregation.model.ServiceAggregationInfo;
import com.leaderhackdemo.pdfparsingprototype.features.aggregation.model.ServiceAggregationNotification;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesRequest;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class RequestAggregationServiceImpl implements RequestAggregationService{

    private Map<String, ServiceAggregationInfo> requestsData = new ConcurrentHashMap<>();

    @Override
    public void initializeNewRequest(LoadPdfFilesRequest loadRequest) {
        if (!requestsData.containsKey(loadRequest.getRequestId())){
            requestsData.put(
                    loadRequest.getRequestId(),
                    ServiceAggregationInfo.builder()
                            .requestId(loadRequest.getRequestId())
                    .build());
        }
    }

    @Override
    public Object receiveNotification(ServiceAggregationNotification notification) {

        Object result = "waiting";

        if (!requestsData.containsKey(notification.getRequestId())){
            log.info("[AGGREGATION]: received status for item "+notification.getRequestId());
            requestsData.put(
                    notification.getRequestId(),
                    ServiceAggregationInfo.builder()
                            .partsTotal(notification.getPartsTotal())
                            .partsReady(
                                    new ArrayList<>(){{
                                        add(notification.getPartInfo());
                                    }}
                            )
                            .build());
        } else {


            requestsData.get(notification.getRequestId()).getPartsReady().add(notification.getPartInfo());

        }

        ServiceAggregationInfo aggregate = requestsData.get(notification.getRequestId());

        if (aggregate.getPartsReady().size() == aggregate.getPartsTotal()){
            log.info("[AGGREGATION]: process completed for request "+notification.getRequestId());
            result = LoadPdfFilesResponse.builder()
                    .requestId(notification.getRequestId())
                    .processResults(
                            aggregate.getPartsReady()
                    )
                    .build();
        }

        return result;
    }
}
