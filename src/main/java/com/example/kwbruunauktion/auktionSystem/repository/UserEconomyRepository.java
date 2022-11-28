package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.SpecificCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEconomyRepository extends JpaRepository<SpecificCar, Long> {
}
