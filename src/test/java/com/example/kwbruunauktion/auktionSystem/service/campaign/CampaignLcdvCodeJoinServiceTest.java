package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.CampaignLcdvCodeJoin.CampaignLcdvCodeJoinRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.CampaignLcdvCodeJoin.CampaignLcdvCodeJoinResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignLcdvCodeJoin;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignLcdvCodeJoinRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CampaignLcdvCodeJoinServiceTest {
    public static LcdvCodeRepository lcdvCodeRepository;
    public static CampaignRepository campaignRepository;
    public static CampaignLcdvCodeJoinRepository campaignLcdvCodeJoinRepository;
    public static CampaignLcdvCodeJoinService campaignLcdvCodeJoinService;


    @BeforeAll
    public static void setUpData(@Autowired LcdvCodeRepository lcdvCode_Repository,
           @Autowired CampaignRepository campaign_Repository,
           @Autowired CampaignLcdvCodeJoinRepository campaignLcdvCodeJoin_Repository) {
        lcdvCodeRepository = lcdvCode_Repository;
        campaignRepository = campaign_Repository;
        campaignLcdvCodeJoinRepository = campaignLcdvCodeJoin_Repository;


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
                .activeDate(LocalDate.now())
                .build();

        campaignRepository.save(campaign1);
        lcdvCodeRepository.saveAll(lcdvCodes);

        List<CampaignLcdvCodeJoin> campaignLcdvCodeJoins = List.of(
                CampaignLcdvCodeJoin.builder()
                        .campaign(campaign1)
                        .lcdvCode(lcdvCodes.get(0))
                        .build(),
                CampaignLcdvCodeJoin.builder()
                        .campaign(campaign1)
                        .lcdvCode(lcdvCodes.get(1))
                        .build(),
                CampaignLcdvCodeJoin.builder()
                        .campaign(campaign1)
                        .lcdvCode(lcdvCodes.get(2))
                        .build());

        campaignLcdvCodeJoinRepository.saveAll(campaignLcdvCodeJoins);

    }

    @BeforeEach
    public void setUpService() {
        campaignLcdvCodeJoinService = new CampaignLcdvCodeJoinService(lcdvCodeRepository, campaignRepository, campaignLcdvCodeJoinRepository);
    }

    @Test
    void addCampaignLcdvCodeJoin() {
        CampaignLcdvCodeJoinRequest campaignLcdvCodeJoinRequest1 = CampaignLcdvCodeJoinRequest.builder()
                .campaignId(1L)
                .lcdvCodeIds(List.of(1L,2L,3L,4L,5L))
                .build();

        CampaignLcdvCodeJoinRequest campaignLcdvCodeJoinRequest2 = CampaignLcdvCodeJoinRequest.builder()
                .campaignId(1L)
                .lcdvCodeIds(List.of(1L,2L,3L,4L,5L))
                .build();

        campaignLcdvCodeJoinService.addCampaignLcdvCodeJoin(campaignLcdvCodeJoinRequest1);
        assertEquals(8, campaignLcdvCodeJoinRepository.findAll().size());
        campaignLcdvCodeJoinService.addCampaignLcdvCodeJoin(campaignLcdvCodeJoinRequest2);
        assertEquals(13, campaignLcdvCodeJoinRepository.findAll().size());
        assertNotEquals(8, campaignLcdvCodeJoinRepository.findAll().size());
    }

    @Test
    void getCampaignLcdvCodeJoinById() {
        CampaignLcdvCodeJoinResponse campaignLcdvCodeJoinResponse = campaignLcdvCodeJoinService.getCampaignLcdvCodeJoinById(1L);
        assertEquals(1L, campaignLcdvCodeJoinResponse.getCampaignId());
        assertEquals(1L, campaignLcdvCodeJoinResponse.getLcdvCodeId());
    }

    @Test
    void getAllCampaignLcdvCodeJoins() {
        List<CampaignLcdvCodeJoinResponse> campaignLcdvCodeJoinResponses = campaignLcdvCodeJoinService.getAllCampaignLcdvCodeJoins();
        assertEquals(3, campaignLcdvCodeJoinResponses.size());
    }

    @Test
    void deleteCampaignLcdvCodeJoinById() {
        campaignLcdvCodeJoinService.deleteCampaignLcdvCodeJoinById(1L);
        assertEquals(2, campaignLcdvCodeJoinRepository.findAll().size());
    }

}