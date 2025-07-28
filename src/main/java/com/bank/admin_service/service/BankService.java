package com.bank.admin_service.service;

import com.bank.admin_service.dto.BankRequestDTO;
import com.bank.admin_service.dto.BankResponseDTO;

import java.util.List;

public interface BankService {
    BankResponseDTO createBank(BankRequestDTO dto);
    List<BankResponseDTO> getAllBanks();
    BankResponseDTO getBankById(Long id);
    BankResponseDTO updateBank(Long id, BankRequestDTO dto);
    void deleteBank(Long id);
}
