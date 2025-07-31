package com.bank.admin_service.controller;

import com.bank.admin_service.dto.AccountDTO;
import com.bank.admin_service.service.AdminAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/admin/accounts")
//@RequiredArgsConstructor
//public class AccountController {
//
//    private final AdminAccountService adminAccountService;
//
//    @GetMapping
//    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
//        return ResponseEntity.ok(adminAccountService.getAllAccounts());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
//        return ResponseEntity.ok(adminAccountService.getAccountById(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
//        return ResponseEntity.ok(adminAccountService.createAccount(dto));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO dto) {
//        return ResponseEntity.ok(adminAccountService.updateAccount(id, dto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
//        adminAccountService.deleteAccount(id);
//        return ResponseEntity.noContent().build();
//    }
//}


@RestController
@RequestMapping("/api/admin/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AdminAccountService accountService;

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts()
                .stream()
                .map(acc -> new AccountDTO(
                        acc.getId(),
                        acc.getAccountNumber(),
                        acc.getType(),
                        acc.getMinBalance(),
                        acc.getStatus()   // ✅ include this!
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        var acc = accountService.getAccountById(id);
        return new AccountDTO(acc.getId(), acc.getAccountNumber(),
                acc.getType(), acc.getMinBalance(),
                acc.getStatus());
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountDTO dto) {
        var acc = accountService.createAccount(dto);
        return new AccountDTO(acc.getId(), acc.getAccountNumber(),
                acc.getType(), acc.getMinBalance(),
                acc.getStatus());
    }

    @PutMapping("/{id}")
    public AccountDTO updateAccount(@PathVariable Long id, @RequestBody AccountDTO dto) {
        var acc = accountService.updateAccount(id, dto);
        return new AccountDTO(acc.getId(), acc.getAccountNumber(),
                acc.getType(), acc.getMinBalance(),
                acc.getStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
