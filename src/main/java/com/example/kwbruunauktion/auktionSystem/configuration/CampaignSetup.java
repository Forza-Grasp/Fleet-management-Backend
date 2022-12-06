package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCodes;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodesRepository;
import com.example.kwbruunauktion.auktionSystem.service.campaign.CampaignService;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CampaignSetup implements ApplicationRunner {
    CampaignRepository campaignRepository;
    CampaignService campaignService;
    LcdvCodesRepository lcdvCodesRepository;


    public CampaignSetup(CampaignRepository campaignRepository, CampaignService campaignService, LcdvCodesRepository lcdvCodesRepository) {
        this.campaignRepository = campaignRepository;
        this.campaignService = campaignService;
        this.lcdvCodesRepository = lcdvCodesRepository;
    }

    @Override
    @SneakyThrows
    public void run(ApplicationArguments args) {


        List<LcdvCodes> lcdvCodes = List.of(
                LcdvCodes.builder()
                        .lcdvCode("0")
                        .build(),
                LcdvCodes.builder()
                        .lcdvCode("1")
                        .build(),
                LcdvCodes.builder()
                        .lcdvCode("2")
                        .build(),
                LcdvCodes.builder()
                        .lcdvCode("3")
                        .build(),
                LcdvCodes.builder()
                        .lcdvCode("4")
                        .build(),
                LcdvCodes.builder()
                        .lcdvCode("5")
                        .build()
        );
        Campaign campaign1 = Campaign.builder()
                .campaignCar(CampaignCar.builder()
                        .brand("Audi")
                        .model("A4")
                        .campaignPictureOne("picture1")
                        .damageAndMileage("damageAndMileage")
                        .description("description")
                        .earliestExceptedReturnDate(LocalDate.of(2021, 10, 10))
                        .exceptedRegistrationFromDate(LocalDate.of(2021, 10, 10))
                        .exceptedRegistrationToDate(LocalDate.of(2021, 10, 10))
                        .monthsRegistered(12)
                        .mileage("mileage")
                        .depositPerCar("1000")
                        .modelText("modelText")
                        .supplyingConditions("supplyingConditions")
                        .latestExceptedReturnDate(LocalDate.of(2021, 10, 10))
                        .build())
                .campaignStatus(CampaignStatus.ACTIVE)
                .lcdvCodes(lcdvCodes)
                .activeDate(LocalDate.now())
                .build();
        campaignRepository.save(campaign1);
        lcdvCodesRepository.saveAll(lcdvCodes);

    }
}
