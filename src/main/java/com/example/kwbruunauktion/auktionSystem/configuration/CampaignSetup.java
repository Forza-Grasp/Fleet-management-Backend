package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.dto.CampaignRequest;
import com.example.kwbruunauktion.auktionSystem.entity.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.service.CampaignService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CampaignSetup implements ApplicationRunner {
        CampaignRepository campaignRepository;
        CampaignService campaignService;
        CampaignStatus campaignStatus;
        CampaignCar campaignCar;
        Campaign campaign;


    public CampaignSetup(CampaignRepository campaignRepository, CampaignService campaignService, CampaignStatus campaignStatus, CampaignCar campaignCar, Campaign campaign) {
            this.campaignRepository = campaignRepository;
            this.campaignService = campaignService;
            this.campaignStatus = campaignStatus;
            this.campaignCar = campaignCar;
            this.campaign = campaign;
        }

    @Override
        public void run(ApplicationArguments args) throws Exception {

        Campaign campaign1 = Campaign.builder()
                    .id(1L)
                    .campaignStatus(CampaignStatus.ACTIVE)
                    .campaignCar(CampaignCar.builder().brand("Ford").build())
                    .created(LocalDateTime.now())
                    .updated(LocalDateTime.now())
                    .build();

            Campaign campaign2 = Campaign.builder()
                    .id(2L)
                    .campaignStatus(CampaignStatus.CANCELLED)
                    .campaignCar(CampaignCar.builder().brand("Mazda").build())
                    .created(LocalDateTime.now())
                    .updated(LocalDateTime.now())
                    .build();

            Campaign campaign3 = Campaign.builder()
                    .id(3L)
                    .campaignStatus(CampaignStatus.CANCELLED)
                    .campaignCar(CampaignCar.builder().brand("BMW").build())
                    .created(LocalDateTime.now())
                    .updated(LocalDateTime.now())
                    .build();

            campaignRepository.save(campaign1);
            campaignRepository.save(campaign2);
            campaignRepository.save(campaign3);
        }
}
