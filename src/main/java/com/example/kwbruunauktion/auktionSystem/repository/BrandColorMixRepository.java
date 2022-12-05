package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandColorMixRepository extends JpaRepository<BrandColorMix, Long> {
    List<BrandColorMix> getAllBySpecificCarModelId(Long id);
}

