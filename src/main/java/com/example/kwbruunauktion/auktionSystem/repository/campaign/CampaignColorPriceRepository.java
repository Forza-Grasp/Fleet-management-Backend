package com.example.kwbruunauktion.auktionSystem.repository.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignColorPriceRepository extends JpaRepository<CampaignColorPrice, Long> {
}

