package com.bank.admin_service.service;

import com.bank.admin_service.dto.BranchRequestDTO;
import com.bank.admin_service.dto.BranchResponseDTO;

import java.util.List;

public interface BranchService {
    BranchResponseDTO createBranch(BranchRequestDTO dto);
    List<BranchResponseDTO> getAllBranches();
    BranchResponseDTO getBranchById(Long id);
    BranchResponseDTO updateBranch(Long id, BranchRequestDTO dto);
    void deleteBranch(Long id);
}
