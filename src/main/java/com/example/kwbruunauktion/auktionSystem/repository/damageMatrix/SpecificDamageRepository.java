package com.example.kwbruunauktion.auktionSystem.repository.damageMatrix;

import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.SpecificDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificDamageRepository extends JpaRepository<SpecificDamage, Long> {

  List<SpecificDamage> findAllByDamageMatrixId(long id);
}
