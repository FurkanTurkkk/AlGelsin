package com.AlGelsin.payment_service.config;

import com.iyzipay.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IyzicoConfig {

    @Value("${iyzico.api.key}")
    private String apiKey;

    @Value("${iyzico.api.secret-key}")
    private String secretKey;

    @Value("${iyzico.api.base-url}")
    private String baseUrl;

    @Bean
    public Options options() {
        Options options = new Options();
        options.setApiKey(apiKey);
        options.setSecretKey(secretKey);
        options.setBaseUrl(baseUrl);
        return options;
    }
}
