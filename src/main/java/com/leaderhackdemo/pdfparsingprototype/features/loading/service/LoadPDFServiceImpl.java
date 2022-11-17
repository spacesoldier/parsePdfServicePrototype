package com.leaderhackdemo.pdfparsingprototype.features.loading.service;

import com.leaderhackdemo.pdfparsingprototype.features.aggregation.model.ServiceAggregationNotification;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.LoadPdfFilesRequest;
import com.leaderhackdemo.pdfparsingprototype.features.loading.model.PdfFileDescription;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.rest.incoming.model.RestRequestEnvelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Service
@Slf4j
public class LoadPDFServiceImpl implements LoadPDFService{

    private BiConsumer<String,Object> requestSink;
    public void setIncomingRequestsSink(
            BiConsumer<String,Object> requestSink
    ){
        this.requestSink = requestSink;
    };
    public Function<RestRequestEnvelope, Object> transformRequest(){

        return routedObjectEnvelope -> {

            Object result = null;

            String rqId = routedObjectEnvelope.getRequestId();

            if (routedObjectEnvelope.getPayload() == null){
                // we received a request
                // and wait for request body
                result = LoadPdfFilesRequest.builder()
                                                    .requestId(rqId)
                                                .build();
            } else {

                List requestPartsEnvelope = null;

                try {
                    requestPartsEnvelope = new ArrayList((Collection)routedObjectEnvelope.getPayload());
                } catch ( Exception e){
                    log.info("[RECEIVE FILES ERROR]: cannot obtain a collection from multipart data");
                }

                if (requestPartsEnvelope != null){

                    List requestParts = null;

                    try{
                        requestParts = (List) requestPartsEnvelope.get(0);
                    } catch (Exception e){
                        log.info("[RECEIVE FILES ERROR]: empty part collection");
                    }

                    if (requestParts != null){

                        int partsCount = requestParts.size();

                        requestParts = requestParts.stream().map(
                                item -> {

                                    FilePart currentPart = (FilePart) item;

                                    log.info("bingo!");
                                    return ServiceAggregationNotification.builder()
                                            .requestId(rqId)
                                            .partsTotal(partsCount)
                                            .partInfo(
                                                    PdfFileDescription.builder()
                                                            .name(
                                                                    currentPart.filename()
                                                            )
                                                            .status("RECEIVED OK")
                                                            .build()
                                            )
                                            .build();
                                }
                        ).toList();

                        result = Flux.fromIterable(requestParts);

                    }

                }
            }

            return result;
        };
    }

}
