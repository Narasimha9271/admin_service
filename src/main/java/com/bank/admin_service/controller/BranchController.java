package com.bank.admin_service.controller;

import com.bank.admin_service.dto.BranchRequestDTO;
import com.bank.admin_service.dto.BranchResponseDTO;
import com.bank.admin_service.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchResponseDTO> createBranch(@RequestBody BranchRequestDTO dto) {
        return ResponseEntity.ok(branchService.createBranch(dto));
    }

    @GetMapping
    public ResponseEntity<List<BranchResponseDTO>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> updateBranch(
            @PathVariable Long id,
            @RequestBody BranchRequestDTO dto) {
        return ResponseEntity.ok(branchService.updateBranch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
