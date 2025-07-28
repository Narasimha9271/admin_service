package com.bank.admin_service.repository;

import com.bank.admin_service.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}