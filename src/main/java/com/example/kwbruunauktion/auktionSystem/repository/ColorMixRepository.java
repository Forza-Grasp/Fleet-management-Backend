package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorMixRepository extends JpaRepository<ColorMix, Long> {
    ColorMix findColorMixById(Long id);
    List<ColorMix> findAllByColorTypeId(Long id);
}

