package com.bank.admin_service.service;

import com.bank.admin_service.dto.CustomerResponseDTO;
import com.bank.admin_service.dto.TransactionResponseDTO;

import java.util.List;

public interface AdminViewService {
    List<CustomerResponseDTO> getAllCustomers();
    List<TransactionResponseDTO> getAllTransactions();
}
