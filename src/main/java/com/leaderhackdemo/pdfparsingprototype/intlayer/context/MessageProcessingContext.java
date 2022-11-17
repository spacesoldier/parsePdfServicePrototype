package com.leaderhackdemo.pdfparsingprototype.intlayer.context;

import com.leaderhackdemo.pdfparsingprototype.intlayer.routing.model.RoutedObjectEnvelope;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class MessageProcessingContext {

    @Builder
    private MessageProcessingContext(){

    }

    public Function<RoutedObjectEnvelope,Object> requestUpdateReceiver(){
        return envelope -> "";
    }

    public Consumer<RoutedObjectEnvelope> completedRequestSink(){

        return envelope -> {

        };
    }

}
