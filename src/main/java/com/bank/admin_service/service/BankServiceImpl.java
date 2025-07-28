package com.bank.admin_service.service;

import com.bank.admin_service.dto.BankRequestDTO;
import com.bank.admin_service.dto.BankResponseDTO;
import com.bank.admin_service.entity.Bank;
import com.bank.admin_service.repository.BankRepository;
import com.bank.admin_service.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Override
    public BankResponseDTO createBank(BankRequestDTO dto) {
        Bank bank = new Bank();
        BeanUtils.copyProperties(dto, bank);
        Bank saved = bankRepository.save(bank);
        return convertToDTO(saved);
    }

    @Override
    public List<BankResponseDTO> getAllBanks() {
        return bankRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BankResponseDTO getBankById(Long id) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        return convertToDTO(bank);
    }

    @Override
    public BankResponseDTO updateBank(Long id, BankRequestDTO dto) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        bank.setName(dto.getName());
        bank.setCode(dto.getCode());
        return convertToDTO(bankRepository.save(bank));
    }

    @Override
    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    private BankResponseDTO convertToDTO(Bank bank) {
        BankResponseDTO dto = new BankResponseDTO();
        BeanUtils.copyProperties(bank, dto);
        return dto;
    }
}
