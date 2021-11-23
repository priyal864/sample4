package com.abc.paymentapi.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestHelper {

    public static RestTemplate constructRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }

}
