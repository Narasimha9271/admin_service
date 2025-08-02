package com.bank.admin_service.controller;

import com.bank.admin_service.dto.CustomerResponseDTO;
import com.bank.admin_service.dto.TransactionResponseDTO;
import com.bank.admin_service.service.AdminViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/view")
@RequiredArgsConstructor
public class AdminViewController {

    private final AdminViewService adminViewService;

    @GetMapping("/customers")
    public List<CustomerResponseDTO> getAllCustomers() {
        return adminViewService.getAllCustomers();
    }

    @GetMapping("/transactions")
    public List<TransactionResponseDTO> getAllTransactions() {
        return adminViewService.getAllTransactions();
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        adminViewService.deleteCustomerById(id);
        return ResponseEntity.ok("✅ Customer deleted successfully.");
    }
}
