package com.example.kwbruunauktion.auktionSystem.repository.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignBidRepository extends JpaRepository<CampaignBid, Long> {

}

