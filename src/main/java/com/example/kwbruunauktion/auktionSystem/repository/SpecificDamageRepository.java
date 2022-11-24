package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.SpecificDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificDamageRepository extends JpaRepository<SpecificDamage, Long> {


}
