package com.bank.admin_service.service;

import com.bank.admin_service.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAccountServiceImpl implements AdminAccountService {

    private final RestTemplate restTemplate;
    private static final String CUSTOMER_SERVICE_URL = "http://localhost:8081/api/accounts";

    @Override
    public List<AccountDTO> getAllAccounts() {
        AccountDTO[] response = restTemplate.getForObject(CUSTOMER_SERVICE_URL, AccountDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        return restTemplate.getForObject(CUSTOMER_SERVICE_URL + "/" + id, AccountDTO.class);
    }

    @Override
    public AccountDTO createAccount(AccountDTO dto) {
        return restTemplate.postForObject(CUSTOMER_SERVICE_URL, dto, AccountDTO.class);
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        restTemplate.put(CUSTOMER_SERVICE_URL + "/" + id, dto);
        return dto;
    }

    @Override
    public void deleteAccount(Long id) {
        restTemplate.delete(CUSTOMER_SERVICE_URL + "/" + id);
    }
}
