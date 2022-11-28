package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.SpecificCar;
import com.example.kwbruunauktion.auktionSystem.entity.UserEconomy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEconomyRepository extends JpaRepository<UserEconomy, Long> {
}
