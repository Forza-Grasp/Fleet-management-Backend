package com.example.kwbruunauktion.security.repository;



import com.example.kwbruunauktion.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles, Long> {
  Boolean existsByEmail(String email);
  Optional<UserWithRoles> findByUsername(String username);
}
