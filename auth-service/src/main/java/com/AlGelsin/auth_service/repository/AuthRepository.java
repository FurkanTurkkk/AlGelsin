package com.AlGelsin.auth_service.repository;

import com.AlGelsin.auth_service.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {
    Optional<Auth> findByUsername(String username);
}
