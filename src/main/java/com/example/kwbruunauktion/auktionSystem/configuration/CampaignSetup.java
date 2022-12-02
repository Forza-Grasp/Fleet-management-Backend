package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.LcdvCodes;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.service.CampaignService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

@Controller
public class CampaignSetup implements ApplicationRunner {
        CampaignRepository campaignRepository;
        CampaignService campaignService;



    public CampaignSetup(CampaignRepository campaignRepository, CampaignService campaignService) {
            this.campaignRepository = campaignRepository;
            this.campaignService = campaignService;
        }

    @Override
        public void run(ApplicationArguments args) throws Exception {

            Campaign campaign1 = Campaign.builder()
                    .campaignStatus(CampaignStatus.ACTIVE)
                    .campaignCar(CampaignCar.builder().brand("Ford").build())
                    .build();

            Campaign campaign2 = Campaign.builder()
                    .campaignStatus(CampaignStatus.CANCELLED)
                    .campaignCar(CampaignCar.builder().brand("Mazda").build())
                    .build();

            Campaign campaign3 = Campaign.builder()
                    .campaignStatus(CampaignStatus.CANCELLED)
                    .campaignCar(CampaignCar.builder().brand("BMW").build())
                    .build();


            campaignRepository.save(campaign1);
            campaignRepository.save(campaign2);
            campaignRepository.save(campaign3);
        }
}
