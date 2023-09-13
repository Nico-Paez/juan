package com.parcial.catalogservice.client;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class FeignConfiguration {

    @Bean
    ReactorLoadBalancer<ServiceInstance> specificLoadBlancer(Environment environment,
                                                             LoadBalancerClientFactory loadBalancerClientFactory){
        String defaultName = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(defaultName, ServiceInstanceListSupplier.class), defaultName);
    }
}
