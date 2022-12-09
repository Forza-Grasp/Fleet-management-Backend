package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignBid;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignBidStatus;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.*;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CampaignBidServiceTest {

    public CampaignBidService campaignBidService;

    public static CampaignBidRepository campaignBidRepository;
    public static UserBuyerRepository userBuyerRepository;
    public static CampaignRepository campaignRepository;
    public static LcdvCodeRepository lcdvCodeRepository;
    public static CampaignColorPriceRepository campaignColorPriceRepository;
    public static CampaignCarRepository campaignCarRepository;
    public static SpecificCarModelRepository specificCarModelRepository;
    public static ColorTypesRepository colorTypesRepository;
    public static BrandColorMixRepository brandColorMixRepository;
    public static ColorMixRepository colorMixRepository;

    @BeforeAll
    public static void setupData(@Autowired CampaignBidRepository campaignBid_Repository,
                                 @Autowired UserBuyerRepository userBuyer_Repository,
                                 @Autowired CampaignRepository campaign_Repository,
                                 @Autowired LcdvCodeRepository lcdvCode_Repository,
                                 @Autowired CampaignColorPriceRepository campaignColorPrice_Repository,
                                 @Autowired CampaignCarRepository campaignCar_Repository,
                                 @Autowired SpecificCarModelRepository specificCarModel_Repository,
                                 @Autowired ColorTypesRepository colorTypes_Repository,
                                 @Autowired BrandColorMixRepository brandColorMix_Repository,
                                 @Autowired ColorMixRepository colorMix_Repository) {
        campaignBidRepository = campaignBid_Repository;
        userBuyerRepository = userBuyer_Repository;
        campaignRepository = campaign_Repository;
        lcdvCodeRepository = lcdvCode_Repository;
        campaignColorPriceRepository = campaignColorPrice_Repository;
        campaignCarRepository = campaignCar_Repository;
        specificCarModelRepository = specificCarModel_Repository;
        colorTypesRepository = colorTypes_Repository;
        brandColorMixRepository = brandColorMix_Repository;
        colorMixRepository = colorMix_Repository;


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

        SpecificCarModel specificCarModel1 = SpecificCarModel.builder()
                .id(1L)
                .brand("Ford Fiesta")
                .model("S10")
                .modelYear("2008")
                .build();
        SpecificCarModel specificCarModel2 = SpecificCarModel.builder()
                .id(2L)
                .brand("Mazda")
                .model("F5")
                .modelYear("2015")
                .build();
        SpecificCarModel specificCarModel3 = SpecificCarModel.builder()
                .id(3L)
                .brand("Mercedes")
                .model("G2A")
                .modelYear("2022")
                .build();

        specificCarModelRepository.save(specificCarModel1);
        specificCarModelRepository.save(specificCarModel2);
        specificCarModelRepository.save(specificCarModel3);

        ColorTypes colorType1 = ColorTypes.builder()
                .id(1L)
                .type("Metallic")
                .build();
        ColorTypes colorType2 = ColorTypes.builder()
                .id(2L)
                .type("Mat")
                .build();
        ColorTypes colorType3 = ColorTypes.builder()
                .id(3L)
                .type("Shiny")
                .build();
        colorTypesRepository.save(colorType1);
        colorTypesRepository.save(colorType2);
        colorTypesRepository.save(colorType3);

        ColorMix colorMix1 = ColorMix.builder()
                .colorCode("S12A")
                .colorName("Silver 2012 grade A")
                .colorType(colorType1)
                .build();
        ColorMix colorMix2 = ColorMix.builder()
                .colorCode("RS")
                .colorName("Red Shiny")
                .colorType(colorType3)
                .build();
        ColorMix colorMix3 = ColorMix.builder()
                .colorCode("B1998")
                .colorName("Burgundy 1998 Opel")
                .colorType(colorType2)
                .build();
        colorMixRepository.save(colorMix1);
        colorMixRepository.save(colorMix2);
        colorMixRepository.save(colorMix3);

        BrandColorMix brandColorMix1 = BrandColorMix.builder()
                .id(1L)
                .specificCarModel(specificCarModel1)
                .colorMix(colorMix1)
                .build();
        BrandColorMix brandColorMix2 = BrandColorMix.builder()
                .id(2L)
                .specificCarModel(specificCarModel2)
                .colorMix(colorMix2)
                .build();
        BrandColorMix brandColorMix3 = BrandColorMix.builder()
                .id(3L)
                .specificCarModel(specificCarModel3)
                .colorMix(colorMix3)
                .build();
        brandColorMixRepository.save(brandColorMix1);
        brandColorMixRepository.save(brandColorMix2);
        brandColorMixRepository.save(brandColorMix3);


        CampaignColorPrice campaignColorPrice1 = CampaignColorPrice.builder()
                .brandColorMix(brandColorMix1)
                .price(1000)
                .build();
        CampaignColorPrice campaignColorPrice2 = CampaignColorPrice.builder()
                .brandColorMix(brandColorMix2)
                .price(1000)
                .build();
        campaignColorPriceRepository.save(campaignColorPrice1);
        campaignColorPriceRepository.save(campaignColorPrice2);
        campaignColorPrice1.setCampaign(campaign1);
        campaignColorPrice2.setCampaign(campaign2);
        campaignColorPriceRepository.save(campaignColorPrice1);
        campaignColorPriceRepository.save(campaignColorPrice2);
        UserBuyer buyer1 = UserBuyer.userBuyerBuilder()
                .user("buyer11")
                .password("buyer")
                .email("buyer@Eleven.dk")
                .firstName("Mo")
                .lastName("Adel")
                .phoneNumber("12345678")
                .city("Aarhus")
                .companyEuVatNumber("DK29233133")
                .zipCode("8000")
                .addressLine1("Vej 1")
                .addressLine2("Vej 2")
                .companyName("Mo's Cars")
                .companyEuVatNumber("12345678")
                .country("Denmark")
                .build();
        UserBuyer buyer2 = UserBuyer.userBuyerBuilder()
                .user("buyer12")
                .password("buyer")
                .email("buyer@Twelve.dk")
                .firstName("Mo")
                .lastName("Adel")
                .phoneNumber("12345678")
                .city("Aarhus")
                .companyEuVatNumber("DK29233133")
                .zipCode("8000")
                .addressLine1("Vej 1")
                .addressLine2("Vej 2")
                .companyName("Mo's Cars")
                .companyEuVatNumber("12345678")
                .country("Denmark")
                .build();
        UserBuyer buyer3 = UserBuyer.userBuyerBuilder()
                .user("buyer13")
                .password("buyer")
                .email("buyer@Thirteen.dk")
                .firstName("Mo")
                .lastName("Adel")
                .phoneNumber("12345678")
                .city("Aarhus")
                .companyEuVatNumber("DK29233133")
                .zipCode("8000")
                .addressLine1("Vej 1")
                .addressLine2("Vej 2")
                .companyName("Mo's Cars")
                .companyEuVatNumber("12345678")
                .country("Denmark")
                .build();
        userBuyerRepository.save(buyer1);
        userBuyerRepository.save(buyer2);
        userBuyerRepository.save(buyer3);

        CampaignBid bid1 = CampaignBid.builder()
                .userWithRoles(buyer1)
                .bidPrice(1000)
                .minAmountOfCars(20)
                .maxAmountOfCars(100)
                .campaignBidStatus(CampaignBidStatus.CURRENT_OFFER)
                .build();
        CampaignBid bid2 = CampaignBid.builder()
                .userWithRoles(buyer2)
                .bidPrice(2000)
                .minAmountOfCars(10)
                .maxAmountOfCars(200)
                .campaignBidStatus(CampaignBidStatus.CURRENT_OFFER)
                .build();
        CampaignBid bid3 = CampaignBid.builder()
                .userWithRoles(buyer3)
                .bidPrice(3000)
                .minAmountOfCars(50)
                .maxAmountOfCars(300)
                .campaignBidStatus(CampaignBidStatus.CURRENT_OFFER)
                .build();
        campaignBidRepository.save(bid1);
        campaignBidRepository.save(bid2);
        campaignBidRepository.save(bid3);
        bid1.setCampaign(campaign1);
        bid2.setCampaign(campaign1);
        bid3.setCampaign(campaign2);
        campaignBidRepository.save(bid1);
        campaignBidRepository.save(bid2);
        campaignBidRepository.save(bid3);
    }


    @BeforeEach
    public void setupCampaignBidService() {
        campaignBidService = new CampaignBidService(campaignBidRepository, userBuyerRepository, campaignRepository);
    }

    @Test
    void getAllCampaignBids() {
        List<CampaignBidsResponse> campaignBidsResponseList = campaignBidService.getAllCampaignBids();
        assertEquals(3, campaignBidsResponseList.size());
    }

    @Test
    void getAllCampaignBidsByCampaignId() {
        List<CampaignBidsResponse> campaignBidsResponseList = campaignBidService.getAllCampaignBidsByCampaignId(1L);
        assertEquals(2, campaignBidsResponseList.size());
    }

    @Test
    void getCampaignBidById() {
        CampaignBidsResponse campaignBidsResponse = campaignBidService.getCampaignBidById(1L);
        assertEquals(1L, campaignBidsResponse.getBidId());
    }

    @Test
    void createCampaignBid() {
        CampaignBidsRequest request = CampaignBidsRequest.builder()
                .campaignId(1L)
                .userId(1L)
                .bidPrice(6000)
                .minAmountOfCars(20)
                .maxAmountOfCars(100)
                .campaignBidStatus(CampaignBidStatus.CURRENT_OFFER)
                .build();
        campaignBidService.createCampaignBid(request);
        assertEquals(4, campaignBidRepository.findAll().size());
        Campaign campaign = campaignRepository.findById(1L).orElseThrow(() -> new RuntimeException("Campaign with that ID not found"));
        assertEquals(3, campaign.getCampaignBids().size());
        assertEquals(6000, campaign.getCampaignBids().get(2).getBidPrice());
    }

    @Test
    void deleteCampaignBidById() {
        campaignBidService.deleteCampaignBidById(1L);
        assertEquals(2, campaignBidRepository.findAll().size());
    }

    @Test
    void changeStatusOfCampaignBidById() {
        CampaignBidsRequest request = CampaignBidsRequest.builder()
                .bidId(1L)
                .campaignBidStatus(CampaignBidStatus.ACCEPTED_OFFER)
                .build();
        CampaignBidsResponse campaignBidsResponse = campaignBidService.changeStatusOfCampaignBidById(request);
        assertEquals(CampaignBidStatus.ACCEPTED_OFFER, campaignBidsResponse.getCampaignBidStatus());
        CampaignBidsResponse response = campaignBidService.getCampaignBidById(1L);
        assertEquals(CampaignBidStatus.ACCEPTED_OFFER, response.getCampaignBidStatus());
    }
}