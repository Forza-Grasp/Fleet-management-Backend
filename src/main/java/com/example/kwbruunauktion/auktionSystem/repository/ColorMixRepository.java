package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.service.BrandColorMixService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorMixRepository extends JpaRepository<ColorMix, Long> {
    List<ColorMix> findAllByColorTypeId(Long id);

    ColorMix getById(Long id);

}

