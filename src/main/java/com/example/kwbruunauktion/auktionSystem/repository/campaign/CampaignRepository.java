package com.example.kwbruunauktion.auktionSystem.repository.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {


}
