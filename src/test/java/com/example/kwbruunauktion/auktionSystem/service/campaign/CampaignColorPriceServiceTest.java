package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignColorPriceRepository;
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
class CampaignColorPriceServiceTest {

    public CampaignColorPriceService campaignColorPriceService;

    public static CampaignColorPriceRepository campaignColorPriceRepository;
    public static BrandColorMixRepository brandColorMixRepository;
    public static CampaignRepository campaignRepository;
    public static ColorMixRepository colorMixRepository;
    public static ColorTypesRepository colorTypesRepository;
    public static SpecificCarModelRepository specificCarModelRepository;
    public static LcdvCodeRepository lcdvCodeRepository;

    @BeforeAll
    public static void setupData(@Autowired CampaignColorPriceRepository campaignColorPrice_Repository,
                                 @Autowired BrandColorMixRepository brandColorMix_Repository,
                                 @Autowired CampaignRepository campaign_Repository,
                                 @Autowired ColorMixRepository colorMix_Repository,
                                 @Autowired ColorTypesRepository colorTypes_Repository,
                                 @Autowired SpecificCarModelRepository specificCarModel_Repository,
                                 @Autowired LcdvCodeRepository lcdvCode_Repository) {

        campaignColorPriceRepository = campaignColorPrice_Repository;
        brandColorMixRepository = brandColorMix_Repository;
        campaignRepository = campaign_Repository;
        colorMixRepository = colorMix_Repository;
        colorTypesRepository = colorTypes_Repository;
        specificCarModelRepository = specificCarModel_Repository;
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



        SpecificCarModel specificCarModel1 = SpecificCarModel.builder()
                .brand("Ford Fiesta")
                .model("S10")
                .modelYear("2008")
                .build();
        SpecificCarModel specificCarModel2 = SpecificCarModel.builder()
                .brand("Mazda")
                .model("F5")
                .modelYear("2015")
                .build();
        SpecificCarModel specificCarModel3 = SpecificCarModel.builder()
                .brand("Mercedes")
                .model("G2A")
                .modelYear("2022")
                .build();

        specificCarModelRepository.save(specificCarModel1);
        specificCarModelRepository.save(specificCarModel2);
        specificCarModelRepository.save(specificCarModel3);

        ColorTypes colorType1 = ColorTypes.builder()
                .type("Metallic")
                .build();
        ColorTypes colorType2 = ColorTypes.builder()
                .type("Mat")
                .build();
        ColorTypes colorType3 = ColorTypes.builder()
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
                .specificCarModel(specificCarModel1)
                .colorMix(colorMix1)
                .build();
        BrandColorMix brandColorMix2 = BrandColorMix.builder()
                .colorMix(colorMix2)
                .specificCarModel(specificCarModel3)
                .build();
        BrandColorMix brandColorMix3 = BrandColorMix.builder()
                .colorMix(colorMix3)
                .specificCarModel(specificCarModel2)
                .build();

        brandColorMixRepository.save(brandColorMix1);
        brandColorMixRepository.save(brandColorMix2);
        brandColorMixRepository.save(brandColorMix3);


        CampaignColorPrice campaignColorPrice = CampaignColorPrice.builder()
                .price(1000)
                .brandColorMix(brandColorMix1)
                .campaign(campaign1)
                .build();
        CampaignColorPrice campaignColorPrice1 = CampaignColorPrice.builder()
                .price(2000)
                .brandColorMix(brandColorMix1)
                .campaign(campaign1)
                .build();
        CampaignColorPrice campaignColorPrice2 = CampaignColorPrice.builder()
                .price(3000)
                .brandColorMix(brandColorMix1)
                .campaign(campaign1)
                .build();
        campaignColorPriceRepository.save(campaignColorPrice);
        campaignColorPriceRepository.save(campaignColorPrice1);
        campaignColorPriceRepository.save(campaignColorPrice2);

    }

    @BeforeEach
    public void setupService() {
        campaignColorPriceService = new CampaignColorPriceService(campaignColorPriceRepository,brandColorMixRepository,campaignRepository);
    }

    @Test
    void getAllCampaignColorPrice() {
        List<CampaignColorPriceResponse> campaignColorPriceResponses = campaignColorPriceService.getAllCampaignColorPrice();
        assertEquals(3, campaignColorPriceResponses.size());
    }

    @Test
    void getCampaignColorPriceById() {
        CampaignColorPriceResponse campaignColorPriceResponse = campaignColorPriceService.getCampaignColorPriceById(1L);
        assertEquals(1000, campaignColorPriceResponse.getPrice());
    }

    @Test
    void addCampaignColorPrice() {

        CampaignColorPriceRequest campaignColorPriceRequest = CampaignColorPriceRequest.builder()
                .price(4000)
                .brandColorMixId(1L)
                .campaignId(1L)
                .build();
        CampaignColorPriceResponse campaignColorPriceResponse = campaignColorPriceService.addCampaignColorPrice(campaignColorPriceRequest);
        assertEquals(4000, campaignColorPriceResponse.getPrice());

    }

    @Test
    void getCampaignColorPriceByCampaignId() {
        CampaignColorPriceResponse campaignColorPriceResponses = campaignColorPriceService.getCampaignColorPriceByCampaignId(1L);
        assertEquals(1000,campaignColorPriceResponses.getPrice() );
    }

    @Test
    void deleteCampaignColorPrice() {
        campaignColorPriceService.deleteCampaignColorPrice(1L);
        assertEquals(2, campaignColorPriceRepository.count());
    }

    @Test
    void editCampaignColorPrice() {
        CampaignColorPriceRequest campaignColorPriceRequest = CampaignColorPriceRequest.builder()
                .id(1L)
                .price(4000)
                .brandColorMixId(1L)
                .campaignId(1L)
                .build();
        campaignColorPriceService.editCampaignColorPrice(campaignColorPriceRequest);
        assertEquals(4000, campaignColorPriceRepository.findById(1L).get().getPrice());
    }
}