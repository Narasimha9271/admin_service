package com.bank.admin_service.service;

import com.bank.admin_service.dto.AccountDTO;
import java.util.List;

public interface AdminAccountService {

    List<AccountDTO> getAllAccounts();

    AccountDTO getAccountById(Long id);

    AccountDTO createAccount(AccountDTO dto);

    AccountDTO updateAccount(Long id, AccountDTO dto);

    void deleteAccount(Long id);
}
