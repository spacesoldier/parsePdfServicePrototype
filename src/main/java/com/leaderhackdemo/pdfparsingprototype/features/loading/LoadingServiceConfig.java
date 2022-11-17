package com.leaderhackdemo.pdfparsingprototype.features.loading;

import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesRequest;
import com.leaderhackdemo.pdfparsingprototype.features.loading.service.LoadPDFService;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.WiringAdapter;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.EndpointAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoadingServiceConfig {
    @Autowired
    EndpointAdapter endpointAdapter;

    @Autowired
    WiringAdapter wiringAdapter;

    @Autowired
    LoadPDFService loadPDFService;

    @Bean
    public void prepareLoadFilesEndpoint(){

        loadPDFService.setIncomingRequestsSink(
                (rqId, payload) -> wiringAdapter.receiveSingleRequest(rqId,payload)
        );

        endpointAdapter.registerRequestBuilder(
                LoadPdfFilesRequest.class,
                loadPDFService.transformRequest()
        );

    }

}
