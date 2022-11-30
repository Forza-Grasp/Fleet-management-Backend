package com.example.kwbruunauktion.auktionSystem.repository.users;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {
  boolean existsByUsername(String username);
}
