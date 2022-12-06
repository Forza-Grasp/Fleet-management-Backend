package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


class LcdvCodeServiceTest {

    public static LcdvCodeService lcdvCodeService;
    public static LcdvCodeRepository lcdvCodeRepository;

    public static CampaignRepository campaignRepository;

    @BeforeAll
    public static void setup(@Autowired LcdvCodeRepository lcdvCode_Repository,
                             @Autowired LcdvCodeService lcdvCode_Service,
                             @Autowired CampaignRepository campaignRepository){
        lcdvCodeService = lcdvCode_Service;
        lcdvCodeRepository = lcdvCode_Repository;

        List<LcdvCode> lcdvCodes = List.of(
                LcdvCode.builder()
                        .lcdvCode("0")
                        .build(),
                LcdvCode.builder()
                        .lcdvCode("1")
                        .build(),
                LcdvCode.builder()
                        .lcdvCode("2")
                        .build(),
                LcdvCode.builder()
                        .lcdvCode("3")
                        .build(),
                LcdvCode.builder()
                        .lcdvCode("4")
                        .build(),
                LcdvCode.builder()
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
        lcdvCodeRepository.saveAll(lcdvCodes);
    }

    @Test
    void addLcdvCode() {
    }

    @Test
    void getLcdvCodes() {
    }

    @Test
    void getLcdvCodeById() {
    }

    @Test
    void deleteLcdvCodeById() {
    }
}