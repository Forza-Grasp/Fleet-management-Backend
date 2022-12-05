package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.SpecificCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificCarRepository extends JpaRepository<SpecificCar, Long> {

  boolean existsByChassisNumber(String chassisNumber);


}
