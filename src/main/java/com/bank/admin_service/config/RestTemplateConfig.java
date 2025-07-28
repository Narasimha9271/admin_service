package com.bank.admin_service.config;

import com.bank.admin_service.security.TokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    private final TokenStore tokenStore;

    public RestTemplateConfig(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setInterceptors(List.of((request, body, execution) -> {
            String token = tokenStore.getToken();
            if (token != null) {
                System.out.println("✅ Adding JWT to outbound request → " + token.substring(0, 15) + "...");
                request.getHeaders().add("Authorization", "Bearer " + token);
            } else {
                System.out.println("🚨 No JWT found in TokenStore");
            }
            return execution.execute(request, body);
        }));

        return restTemplate;
    }
}
