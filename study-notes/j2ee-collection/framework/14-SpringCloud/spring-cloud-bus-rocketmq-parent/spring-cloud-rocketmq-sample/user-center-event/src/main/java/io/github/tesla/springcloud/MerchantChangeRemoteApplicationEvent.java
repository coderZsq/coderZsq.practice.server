package io.github.tesla.springcloud;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MerchantChangeRemoteApplicationEvent extends RemoteApplicationEvent {

    @SuppressWarnings("unused")
    private MerchantChangeRemoteApplicationEvent() {
        // for serializers
    }

    public MerchantChangeRemoteApplicationEvent(Object source, String originService,
                                                String destinationService) {
        super(source, originService, destinationService);
    }
}