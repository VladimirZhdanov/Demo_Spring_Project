package com.homel.demo.project.integration;

import feign.Client;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class ClientConfig {
    @Bean
    public Client feignClient() {
        return new CustomFeignClient(null, null);
    }

    // Custom handler for exceptions
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
