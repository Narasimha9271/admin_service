package com.bank.admin_service.controller;

import com.bank.admin_service.dto.BankRequestDTO;
import com.bank.admin_service.dto.BankResponseDTO;
import com.bank.admin_service.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping
    public ResponseEntity<BankResponseDTO> createBank(@RequestBody BankRequestDTO dto) {
        return ResponseEntity.ok(bankService.createBank(dto));
    }

    @GetMapping
    public ResponseEntity<List<BankResponseDTO>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankResponseDTO> getBankById(@PathVariable Long id) {
        return ResponseEntity.ok(bankService.getBankById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankResponseDTO> updateBank(
            @PathVariable Long id,
            @RequestBody BankRequestDTO dto) {
        return ResponseEntity.ok(bankService.updateBank(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
}
