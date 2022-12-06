package com.example.kwbruunauktion.auktionSystem.repository.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignCarRepository extends JpaRepository<CampaignCar, Long> {


  Optional<CampaignCar> findByCampaignId(Long id);

}
