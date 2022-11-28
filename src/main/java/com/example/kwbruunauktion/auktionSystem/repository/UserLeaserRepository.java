package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLeaserRepository extends JpaRepository<UserLeaser, Long> {
}
