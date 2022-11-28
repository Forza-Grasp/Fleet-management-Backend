package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBuyerRepository extends JpaRepository<UserBuyer, Long> {

}
