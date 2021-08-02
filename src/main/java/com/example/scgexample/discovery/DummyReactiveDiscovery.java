package com.example.scgexample.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;


@Service
public class DummyReactiveDiscovery implements ReactiveDiscoveryClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyReactiveDiscovery.class);

    public DummyReactiveDiscovery() {
        LOGGER.info("Creating Reactive Discovery Client");
    }

    @Override
    public String description() {
        return "Dummy Reactive Discovery";
    }

    @Override
    public Flux<ServiceInstance> getInstances(String serviceId) {
        LOGGER.info("Calling GetInstances");
        return Mono.fromCallable(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        LOGGER.error("Interrupt Error",e);
                        throw new RuntimeException(e);
                    }
                    return Collections.<ServiceInstance>emptyList();
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Flux<String> getServices() {
        LOGGER.info("Calling GetServices");

        return Mono.fromCallable(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupt Error",e);
                throw new RuntimeException(e);
            }
            return Collections.<String>emptyList();

        })
        .subscribeOn(Schedulers.boundedElastic())
        .flatMapMany(Flux::fromIterable);
    }
}
