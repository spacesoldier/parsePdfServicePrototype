package com.leaderhackdemo.pdfparsingprototype.intlayer.tools;

import com.leaderhackdemo.pdfparsingprototype.intlayer.routing.IntlayerObjectRouter;
import com.leaderhackdemo.pdfparsingprototype.intlayer.tools.bandwidth.TokenBucketRateLimiter;
import com.leaderhackdemo.pdfparsingprototype.intlayer.tools.bandwidth.model.RouterBypassRequest;
import com.leaderhackdemo.pdfparsingprototype.intlayer.wiring.adapters.WiringAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BandwidthControllerConfig {

    @Autowired
    WiringAdapter wiringAdapter;

    @Autowired
    IntlayerObjectRouter intlayerObjectRouter;

    @Bean
    public TokenBucketRateLimiter initTokenBucketBandwidthControl(){

        TokenBucketRateLimiter rateLimiter =
                TokenBucketRateLimiter.builder()
                                        .bucketCapacity(400)
                                        .requestSink(
                                            (rqId, payload) -> wiringAdapter.receiveSingleRequest(rqId,payload)
                                        )
                                    .build();

        intlayerObjectRouter.addPostProcessAggregation(
                RouterBypassRequest.class,
                rateLimiter.aggregateBypass()
        );

        wiringAdapter.registerFeature(
                RouterBypassRequest.class,
                requestObj -> {
                    RouterBypassRequest request = null;
                    try {
                        request = (RouterBypassRequest) requestObj;
                    } catch (Exception e){

                    }

                    if (request != null){
                        rateLimiter.receiveBypassRequest().accept(request);
                    }

                    return "Ok";
                }
        );

        return rateLimiter;
    }
}
