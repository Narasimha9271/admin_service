package com.bank.admin_service.service;

import com.bank.admin_service.dto.CustomerResponseDTO;
import com.bank.admin_service.dto.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminViewServiceImpl implements AdminViewService {

    private final RestTemplate restTemplate;

    private static final String CUSTOMER_URL = "http://localhost:8081/api/customers";
    private static final String TRANSACTION_URL = "http://localhost:8081/api/transactions";

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        String jwtToken = extractJwtFromSecurityContext();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<CustomerResponseDTO>> response = restTemplate.exchange(
                CUSTOMER_URL,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<CustomerResponseDTO>>() {}
        );

        return response.getBody();
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        String jwtToken = extractJwtFromSecurityContext();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<TransactionResponseDTO>> response = restTemplate.exchange(
                TRANSACTION_URL,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<TransactionResponseDTO>>() {}
        );

        return response.getBody();
    }

    public void deleteCustomerById(Long id) {
        String jwtToken = extractJwtFromSecurityContext();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                CUSTOMER_URL + "/" + id,
                HttpMethod.DELETE,
                entity,
                String.class
        );
    }

    private String extractJwtFromSecurityContext() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials(); // ✅ JWT token
        }
        throw new RuntimeException("JWT token not found in SecurityContext");
    }
}
