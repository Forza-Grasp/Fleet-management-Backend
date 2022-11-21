package com.example.kwbruunauktion.security.repository;


import com.example.template.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles, String> {
  Boolean existsByEmail(String email);
}
