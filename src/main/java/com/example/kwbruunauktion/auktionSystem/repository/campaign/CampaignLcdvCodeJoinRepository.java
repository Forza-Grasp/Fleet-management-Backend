package com.example.kwbruunauktion.auktionSystem.repository.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignLcdvCodeJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignLcdvCodeJoinRepository extends JpaRepository<CampaignLcdvCodeJoin, Long> {
}

