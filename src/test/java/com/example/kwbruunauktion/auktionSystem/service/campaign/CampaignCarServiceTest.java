package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
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

        List<CampaignCar> campaignCarList = List.of(
       CampaignCar.builder()
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
                .build(),

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
                .build(),

        CampaignCar.builder()
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
                .build()
        );

        campaignCarRepository.saveAll(campaignCarList);
    }

    @BeforeEach
    public void setupService(){
        campaignCarService = new CampaignCarService(campaignRepository, campaignCarRepository);
    }


    @Test
    void getAllCampaignCars() {
        List<CampaignCarResponse> campaignCarList = campaignCarService.getAllCampaignCars();
        assertEquals(3, campaignCarList.size());
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
        assertEquals(4, campaignCarRepository.findAll().size());
        assertEquals("NEWCAR", campaignCarRepository.findAll().get(3).getBrand());
    }

    @Test
    void deleteCampaignCar() {
        campaignCarService.deleteCampaignCar(1L);
        assertEquals(2, campaignCarService.getAllCampaignCars().size());
    }

    @Test
    void editCampaignCar() {
        CampaignCar campaignCar = campaignCarRepository.findById(1L).get();
        CampaignCarRequest carRequest = new CampaignCarRequest(campaignCar);
        carRequest.setBrand("Brand from Test world");
        campaignCarService.editCampaignCar(carRequest);
        assertEquals("Brand from Test world", campaignCarRepository.findById(1L).get().getBrand());
    }
}