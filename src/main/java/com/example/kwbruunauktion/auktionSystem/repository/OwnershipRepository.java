package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
}
