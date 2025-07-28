package com.bank.admin_service.service.impl;

import com.bank.admin_service.dto.BranchRequestDTO;
import com.bank.admin_service.dto.BranchResponseDTO;
import com.bank.admin_service.entity.Bank;
import com.bank.admin_service.entity.Branch;
import com.bank.admin_service.repository.BankRepository;
import com.bank.admin_service.repository.BranchRepository;
import com.bank.admin_service.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BankRepository bankRepository;

    @Override
    public BranchResponseDTO createBranch(BranchRequestDTO dto) {
        Branch branch = new Branch();
        BeanUtils.copyProperties(dto, branch);
        Bank bank = bankRepository.findById(dto.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        branch.setBank(bank);
        Branch saved = branchRepository.save(branch);
        return convertToDTO(saved);
    }

    @Override
    public List<BranchResponseDTO> getAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BranchResponseDTO getBranchById(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        return convertToDTO(branch);
    }

    @Override
    public BranchResponseDTO updateBranch(Long id, BranchRequestDTO dto) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        branch.setName(dto.getName());
        branch.setAddress(dto.getAddress());
        Bank bank = bankRepository.findById(dto.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        branch.setBank(bank);
        return convertToDTO(branchRepository.save(branch));
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }

    private BranchResponseDTO convertToDTO(Branch branch) {
        BranchResponseDTO dto = new BranchResponseDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setAddress(branch.getAddress());
        dto.setBankId(branch.getBank().getId());
        dto.setBankName(branch.getBank().getName());
        return dto;
    }
}
