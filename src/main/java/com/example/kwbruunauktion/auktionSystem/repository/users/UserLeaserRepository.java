package com.example.kwbruunauktion.auktionSystem.repository.users;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLeaserRepository extends JpaRepository<UserLeaser, Long> {
    boolean existsByUsername(String username);
}
