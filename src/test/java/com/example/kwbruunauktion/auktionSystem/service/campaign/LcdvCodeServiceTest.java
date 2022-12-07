package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LcdvCodeServiceTest {

    public static LcdvCodeService lcdvCodeService;
    public static LcdvCodeRepository lcdvCodeRepository;
    public static CampaignRepository campaignRepository;


    @BeforeAll
    public static void setup(@Autowired LcdvCodeRepository lcdvCode_Repository,
                             @Autowired CampaignRepository campaign_Repository) {
        lcdvCodeRepository = lcdvCode_Repository;
        campaignRepository = campaign_Repository;

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


        List<Campaign> campaignList = List.of(
                Campaign.builder()
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
                        .build(),

                Campaign.builder()
                        .campaignCar(CampaignCar.builder()
                                .brand("BMW")
                                .model("Q3")
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
                        .build(),

                Campaign.builder()
                        .campaignCar(CampaignCar.builder()
                                .brand("BMW")
                                .model("Q3")
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
                        .build()
        );

        campaignRepository.saveAll(campaignList);
        lcdvCodeRepository.saveAll(lcdvCodes);

        LcdvCode lcdvCode1 = LcdvCode.builder()
                .lcdvCode("111")
                .campaign(campaignList)
                .build();

        LcdvCode lcdvCode2 = LcdvCode.builder()
                .lcdvCode("222")
                .campaign(campaignList)
                .build();

        LcdvCode lcdvCode3 = LcdvCode.builder()
                .lcdvCode("333")
                .campaign(campaignList)
                .build();

        lcdvCodeRepository.save(lcdvCode1);
        lcdvCodeRepository.save(lcdvCode2);
        lcdvCodeRepository.save(lcdvCode3);
    }


    @BeforeEach
    public void setupService() {
        lcdvCodeService = new LcdvCodeService(lcdvCodeRepository, campaignRepository);
    }

    @Test
    void addLcdvCode() {
    }

    @Test
    void getLcdvCodes() {
        List<LcdvCodeResponse> lcdvCodeResponses = lcdvCodeService.getLcdvCodes();
        assertEquals(9,lcdvCodeResponses.size());
    }

    @Test
    void getLcdvCodeById() {
        LcdvCodeResponse lcdvCodeResponseToGet = lcdvCodeService.getLcdvCodeById(1L);
        assertEquals(lcdvCodeResponseToGet.getId(),1L);
        assertNotEquals(lcdvCodeResponseToGet.getId(),3L);
    }

    @Test
    void deleteLcdvCodeById() {
        lcdvCodeRepository.deleteById(1L);
        assertEquals(8,lcdvCodeRepository.count());
    }
}