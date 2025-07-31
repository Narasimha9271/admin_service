package com.bank.admin_service.service;

import com.bank.admin_service.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAccountServiceImpl implements AdminAccountService {

    private final RestTemplate restTemplate;

    private static final String CUSTOMER_SERVICE_ACCOUNTS_URL = "http://localhost:8081/api/accounts";

    @Override
    public List<AccountDTO> getAllAccounts() {
        return fetchWithJwt(CUSTOMER_SERVICE_ACCOUNTS_URL, new ParameterizedTypeReference<>() {});
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        return fetchWithJwt(
                CUSTOMER_SERVICE_ACCOUNTS_URL + "/" + id,
                new ParameterizedTypeReference<AccountDTO>() {}
        );
    }

    @Override
    public AccountDTO createAccount(AccountDTO dto) {
        String jwt = extractJwt();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AccountDTO> entity = new HttpEntity<>(dto, headers);
        ResponseEntity<AccountDTO> response = restTemplate.exchange(
                CUSTOMER_SERVICE_ACCOUNTS_URL,
                HttpMethod.POST,
                entity,
                AccountDTO.class
        );
        return response.getBody();
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        String jwt = extractJwt();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AccountDTO> entity = new HttpEntity<>(dto, headers);
        ResponseEntity<AccountDTO> response = restTemplate.exchange(
                CUSTOMER_SERVICE_ACCOUNTS_URL + "/" + id,
                HttpMethod.PUT,
                entity,
                AccountDTO.class
        );
        return response.getBody();
    }

    @Override
    public void deleteAccount(Long id) {
        String jwt = extractJwt();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                CUSTOMER_SERVICE_ACCOUNTS_URL + "/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }

    // 🔄 Helper for GET (both single object & list)
    private <T> T fetchWithJwt(String url, ParameterizedTypeReference<T> typeRef) {
        String jwt = extractJwt();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                typeRef
        );
        return response.getBody();
    }

    // ✅ Extract token from Security Context
    private String extractJwt() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getCredentials() instanceof String) {
            return (String) auth.getCredentials();
        }
        throw new RuntimeException("JWT token missing in Security Context");
    }
}
