package com.bank.admin_service.service;

import com.bank.admin_service.dto.CustomerResponseDTO;
import com.bank.admin_service.dto.TransactionResponseDTO;
import com.bank.admin_service.service.AdminViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminViewServiceImpl implements AdminViewService {

    private final RestTemplate restTemplate;

    private static final String CUSTOMER_URL = "http://localhost:8081/api/customers";
    private static final String TRANSACTION_URL = "http://localhost:8081/api/transactions";

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        CustomerResponseDTO[] response = restTemplate.getForObject(CUSTOMER_URL, CustomerResponseDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        TransactionResponseDTO[] response = restTemplate.getForObject(TRANSACTION_URL, TransactionResponseDTO[].class);
        return Arrays.asList(response);
    }
}
