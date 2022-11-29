package com.example.kwbruunauktion.auktionSystem.repository.damageMatrix;

import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageMatrixRepository extends JpaRepository<DamageMatrix, Long> {

  boolean existsByUserWithRolesId(Long id);
}
