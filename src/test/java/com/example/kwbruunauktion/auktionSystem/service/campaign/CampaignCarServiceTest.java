package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignCarRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CampaignCarServiceTest {

    public CampaignCarService campaignCarService;

    public static CampaignRepository campaignRepository;
    public static CampaignCarRepository campaignCarRepository;

    @BeforeAll
    public static void setupData(@Autowired CampaignRepository campaign_Repository,
                                 @Autowired CampaignCarRepository campaignCar_Repository) {
        campaignRepository = campaign_Repository;
        campaignCarRepository = campaignCar_Repository;

        CampaignCar campaignCar1 = CampaignCar.builder()
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

        CampaignCar.builder()
                .brand("OPEL")
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

        CampaignCar campaignCar2 = CampaignCar.builder()
                .brand("MERCEDES")
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
        CampaignCar campaignCar3 = CampaignCar.builder()
                .brand("CAMPAIGN BMW")
                .model("CAMPAIGN X5")
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
        CampaignCar campaignCar4 = CampaignCar.builder()
                .brand("CAMPAIGN Audi")
                .model("CAMPAIGN A4")
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
        List<CampaignCar> campaignCarList = List.of(campaignCar1, campaignCar2, campaignCar3, campaignCar4);
        campaignCarRepository.saveAll(campaignCarList);
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


    }

    @BeforeEach
    public void setupService() {
        campaignCarService = new CampaignCarService(campaignRepository, campaignCarRepository);
    }


    @Test
    void getAllCampaignCars() {
        List<CampaignCarResponse> campaignCarList = campaignCarService.getAllCampaignCars();
        assertEquals(4, campaignCarList.size());
    }

    @Test
    void getCampaignCarById() {
        CampaignCarResponse foundCarResponse = campaignCarService.getCampaignCarById(1L);
        assertEquals("BMW", foundCarResponse.getBrand());
    }

    @Test
    void addCampaignCar() {
        CampaignCarRequest carRequest = CampaignCarRequest.builder()
                .brand("NEWCAR")
                .model("BRAND NEW MODEL")
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
                .campaignId(5L)
                .build();
        campaignCarService.addCampaignCar(carRequest);
        assertEquals(5, campaignCarRepository.findAll().size());
        assertEquals("NEWCAR", campaignCarRepository.findAll().get(4).getBrand());
    }

    @Test
    void deleteCampaignCar() {
        campaignCarService.deleteCampaignCar(1L);
        assertEquals(3, campaignCarService.getAllCampaignCars().size());
    }

    @Test
    void editCampaignCar() {
        CampaignCar campaignCar = campaignCarRepository.findById(1L).get();
        CampaignCarRequest carRequest = new CampaignCarRequest(campaignCar);
        carRequest.setBrand("Brand from Test world");
        campaignCarService.editCampaignCar(carRequest);
        assertEquals("Brand from Test world", campaignCarRepository.findById(1L).get().getBrand());
    }

    @Test
    void getCampaignCarByCampaignId() {
        CampaignCarResponse foundCarResponse = campaignCarService.getCampaignCarByCampaignId(1L);
        assertEquals("BMW", foundCarResponse.getBrand());

    }
}