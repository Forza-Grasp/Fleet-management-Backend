package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.*;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.*;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignColorPriceRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignLcdvCodeJoinRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.service.campaign.CampaignService;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
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
    LcdvCodeRepository lcdvCodeRepository;
    CampaignColorPriceRepository campaignColorPriceRepository;
    BrandColorMixRepository brandColorMixRepository;
    SpecificCarModelRepository specificCarModelRepository;
    ColorMixRepository colorMixRepository;
    ColorTypesRepository colorTypesRepository;
    CampaignLcdvCodeJoinRepository campaignLcdvCodeJoinRepository;


    public CampaignSetup(CampaignRepository campaignRepository,
                         CampaignService campaignService,
                         LcdvCodeRepository lcdvCodeRepository,
                         CampaignColorPriceRepository campaignColorPriceRepository,
                         BrandColorMixRepository brandColorMixRepository,
                         SpecificCarModelRepository specificCarModelRepository,
                         ColorMixRepository colorMixRepository,
                         ColorTypesRepository colorTypesRepository,
                         CampaignLcdvCodeJoinRepository campaignLcdvCodeJoinRepository) {
        this.campaignRepository = campaignRepository;
        this.campaignService = campaignService;
        this.lcdvCodeRepository = lcdvCodeRepository;
        this.campaignColorPriceRepository = campaignColorPriceRepository;
        this.brandColorMixRepository = brandColorMixRepository;
        this.specificCarModelRepository = specificCarModelRepository;
        this.colorMixRepository = colorMixRepository;
        this.colorTypesRepository = colorTypesRepository;
        this.campaignLcdvCodeJoinRepository = campaignLcdvCodeJoinRepository;
    }

    @Override
    @SneakyThrows
    public void run(ApplicationArguments args) {


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
        Campaign campaign2 = Campaign.builder()
                .campaignCar(CampaignCar.builder()
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
                        .build())
                .campaignStatus(CampaignStatus.ACTIVE)
                .activeDate(LocalDate.now())
                .build();

        List<Campaign> campaignList = List.of(campaign1, campaign2);
        campaignRepository.saveAll(campaignList);
        lcdvCodeRepository.saveAll(lcdvCodes);



        lcdvCodes.stream().map(lcdvcode ->
                CampaignLcdvCodeJoin.builder()
                        .campaign(campaign1)
                        .lcdvCode(lcdvcode)
                        .build()
        ).forEach(campaignLcdvCodeJoin -> campaignLcdvCodeJoinRepository.save(campaignLcdvCodeJoin));

        lcdvCodes.stream().map(lcdvcode ->
                CampaignLcdvCodeJoin.builder()
                        .campaign(campaign2)
                        .lcdvCode(lcdvcode)
                        .build()
        ).forEach(campaignLcdvCodeJoin -> campaignLcdvCodeJoinRepository.save(campaignLcdvCodeJoin));




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
                .build();
        BrandColorMix brandColorMix3 = BrandColorMix.builder()
                .colorMix(colorMix3)
                .specificCarModel(specificCarModel1)
                .build();

        System.out.println("\n" + brandColorMix1 + "\n");
        brandColorMixRepository.save(brandColorMix1);

        //CampaignColorPrice campaignColorPrice = CampaignColorPrice.builder()
        //        .price(1000)
        //        .brandColorMix(brandColorMix1)
        //        .campaign(campaign1)
        //        .build();
        //campaignColorPriceRepository.save(campaignColorPrice);



    }
}
