package com.homel.demo.project.integration;

import feign.Client;
import feign.Request;
import feign.Response;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;

public class CustomFeignClient extends Client.Default {

    public CustomFeignClient(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier) {
        super(sslContextFactory, hostnameVerifier);
    }

    @Override
    public Response execute(Request request, Request.Options options) {
        try {
            return super.execute(request, options);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
