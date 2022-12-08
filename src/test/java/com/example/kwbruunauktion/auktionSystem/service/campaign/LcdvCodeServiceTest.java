package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignCarRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LcdvCodeServiceTest {

    public static LcdvCodeService lcdvCodeService;
    public static LcdvCodeRepository lcdvCodeRepository;
    public static CampaignRepository campaignRepository;
    public static CampaignCarRepository campaignCarRepository;


    @BeforeAll
    public static void setup(@Autowired LcdvCodeRepository lcdvCode_Repository,
                             @Autowired CampaignRepository campaign_Repository,
                             @Autowired CampaignCarRepository campaignCar_Repository) {
        lcdvCodeRepository = lcdvCode_Repository;
        campaignRepository = campaign_Repository;
        campaignCarRepository = campaignCar_Repository;

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

        CampaignCar campaignCar1 = CampaignCar.builder()
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
                .build();
        CampaignCar campaignCar2 = CampaignCar.builder()
                .brand("BMW")
                .model("X5")
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
                .build();
        campaignCarRepository.save(campaignCar1);
        campaignCarRepository.save(campaignCar2);

        Campaign campaign1 = Campaign.builder()
                .campaignStatus(CampaignStatus.ACTIVE)
                .activeDate(LocalDate.now())
                .build();

        Campaign campaign2 = Campaign.builder()
                .campaignStatus(CampaignStatus.ACTIVE)
                .activeDate(LocalDate.now())
                .build();

        List<Campaign> campaignList = List.of(campaign1, campaign2);
        campaignRepository.saveAll(campaignList);
        campaignCar1.setCampaign(campaign1);
        campaignCar2.setCampaign(campaign2);
        campaignCarRepository.save(campaignCar1);
        campaignCarRepository.save(campaignCar2);
        lcdvCodeRepository.saveAll(lcdvCodes);

        /*
        campaign1.setLcdvCodes(lcdvCodes);
        campaignRepository.save(campaign1);
        campaign2.setLcdvCodes(lcdvCodes);
        campaignRepository.save(campaign2);

        for(LcdvCode l : lcdvCodes){
            l.setCampaign(Collections.singletonList(campaign1));
            lcdvCodeRepository.save(l);
        }
        for(LcdvCode l : lcdvCodes){
            l.setCampaign(Collections.singletonList(campaign2));
            lcdvCodeRepository.save(l);
        }

         */
    }


    @BeforeEach
    public void setupService() {
        lcdvCodeService = new LcdvCodeService(lcdvCodeRepository, campaignRepository);
    }

    @Test
    void addLcdvCode() {
        LcdvCodeRequest lcq = LcdvCodeRequest.builder()
                .lcdvCode("6")
                .build();
        LcdvCodeResponse lcdvCodeResponse = lcdvCodeService.addLcdvCode(lcq);
        assertEquals(7, lcdvCodeService.getLcdvCodes().size());
    }

    @Test
    void getLcdvCodes() {
        List<LcdvCodeResponse> lcdvCodeResponses = lcdvCodeService.getLcdvCodes();
        assertEquals(6,lcdvCodeResponses.size());
    }

    @Test
    void getLcdvCodeById() {
        LcdvCodeResponse lcdvCodeResponseToGet = lcdvCodeService.getLcdvCodeById(1L);
        assertEquals(lcdvCodeResponseToGet.getId(),1L);
        assertNotEquals(lcdvCodeResponseToGet.getId(),3L);
    }

    @Test
    void deleteLcdvCodeById() {
        //List<LcdvCodeResponse> responsesBeforeDeletion = lcdvCodeService.getLcdvCodes();
        lcdvCodeService.deleteLcdvCodeById(1L);
        List<LcdvCodeResponse> responsesAfterDeletion = lcdvCodeService.getLcdvCodes();
        assertEquals(5,responsesAfterDeletion.size());
    }
}