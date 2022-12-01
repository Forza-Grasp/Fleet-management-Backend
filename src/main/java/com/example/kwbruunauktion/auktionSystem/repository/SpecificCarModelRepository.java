package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificCarModelRepository extends JpaRepository<SpecificCarModel, Long> {
    //SpecificCarModel findSpecificCarModelById(Long id);


}
