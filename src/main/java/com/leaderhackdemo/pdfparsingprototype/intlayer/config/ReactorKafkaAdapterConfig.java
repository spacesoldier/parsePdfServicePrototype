package com.leaderhackdemo.pdfparsingprototype.intlayer.config;

import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.WiringAdapter;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.kafka.ReactorKafkaAdapter;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.kafka.config.ReactorKafkaBindings;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.providers.FluxChannelProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(ReactorKafkaBindings.class)
public class ReactorKafkaAdapterConfig {

    @Autowired
    WiringAdapter wiringAdapter;

    @Autowired
    FluxChannelProvider fluxChannelProvider;

    @Bean
    public ReactorKafkaAdapter prepareReactorKafkaAdapter(){


        return ReactorKafkaAdapter.builder()
                                        .fluxByNameProvider(
                                                channelName -> fluxChannelProvider.getStream(channelName)
                                        )
                                    .build();

    }

}
