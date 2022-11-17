package com.leaderhackdemo.pdfparsingprototype.intlayer.config;

import com.leaderhackdemo.pdfparsingprototype.intlayer.startup.AppReadyListener;
import com.leaderhackdemo.pdfparsingprototype.intlayer.routing.IntlayerObjectRouter;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.providers.FluxChannelProvider;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.providers.MonoChannelProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RouterConfig {

    @Autowired
    MonoChannelProvider monoChannelProvider;

    @Autowired
    FluxChannelProvider fluxChannelProvider;

    @Bean
    public IntlayerObjectRouter initObjectRouter(){

        return IntlayerObjectRouter.builder()
                    .sinkByRqIdProvider(requestId -> monoChannelProvider.getInput(requestId))
                    .sinkByChannelNameProvider(channelName -> fluxChannelProvider.getExistingSink(channelName))
                    .fluxProvider(channelName -> fluxChannelProvider.getStream(channelName))
                .build();
    }

    @Bean
    public AppReadyListener initAppListener(){
        return new AppReadyListener();
    }
}
