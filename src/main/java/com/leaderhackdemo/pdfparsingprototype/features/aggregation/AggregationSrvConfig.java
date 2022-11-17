package com.leaderhackdemo.pdfparsingprototype.features.aggregation;

import com.leaderhackdemo.pdfparsingprototype.features.aggregation.model.ServiceAggregationNotification;
import com.leaderhackdemo.pdfparsingprototype.features.aggregation.service.RequestAggregationService;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesRequest;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesResponse;
import com.leaderhackdemo.pdfparsingprototype.intlayer.routing.IntlayerObjectRouter;
import com.leaderhackdemo.pdfparsingprototype.intlayer.routing.model.RoutedObjectEnvelope;
import com.leaderhackdemo.pdfparsingprototype.intlayer.tools.bandwidth.model.RouterBypassRequest;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.WiringAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AggregationSrvConfig {
    @Autowired
    private WiringAdapter wiringAdapter;

    @Autowired
    IntlayerObjectRouter intlayerObjectRouter;

    @Autowired
    RequestAggregationService requestAggregationService;

    @Bean
    public void configAggregationStage(){

        wiringAdapter.registerFeature(
                LoadPdfFilesRequest.class,
                inputObj -> {
                    Object result = "Ok";
                    try{
                        LoadPdfFilesRequest loadPdfFilesRequest = (LoadPdfFilesRequest) inputObj;
                        requestAggregationService.initializeNewRequest(loadPdfFilesRequest);
                    } catch (Exception e){
                        log.info("[AGGREGATION]: input is not a LoadPdfFilesRequest");
                    }

                    return result;
                }
        );

        wiringAdapter.registerFeature(
                ServiceAggregationNotification.class,
                inputObj -> {
                    Object result = "Ok";
                    try{
                        ServiceAggregationNotification serviceAggregationNotification = (ServiceAggregationNotification) inputObj;
                        result = requestAggregationService.receiveNotification(serviceAggregationNotification);
                    } catch (Exception e){
                        log.info("[AGGREGATION]: input is not a ServiceAggregationNotification");
                    }

                    return result;
                }
        );

        intlayerObjectRouter.addEnvelopeAggregation(
                LoadPdfFilesResponse.class,
                (responseObj, envelope)-> {
                        try{
                            LoadPdfFilesResponse loadPdfFilesResponse = (LoadPdfFilesResponse) responseObj;
                            envelope.setRqId(loadPdfFilesResponse.getRequestId());
                        } catch (Exception e){
                            log.info("[AGGREGATION]: input is not a ServiceAggregationNotification");
                        }

                        return envelope;
                }
        );
    }
}
