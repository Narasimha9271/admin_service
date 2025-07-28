package com.bank.admin_service.repository;

import com.bank.admin_service.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);

    Optional<Object> findByUsername(String username);
}
