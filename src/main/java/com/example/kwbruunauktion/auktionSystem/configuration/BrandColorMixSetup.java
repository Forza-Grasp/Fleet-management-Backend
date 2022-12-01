package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.service.BrandColorMixService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BrandColorMixSetup implements ApplicationRunner {

    BrandColorMixRepository brandColorMixRepository;
    BrandColorMixService brandColorMixService;
    SpecificCarModelRepository specificCarModelRepository;
    ColorMixRepository colorMixRepository;
    ColorTypesRepository colorTypesRepository;

    public BrandColorMixSetup(BrandColorMixRepository brandColorMixRepository,
                              BrandColorMixService brandColorMixService,
                              SpecificCarModelRepository specificCarModelRepository,
                              ColorMixRepository colorMixRepository,
                              ColorTypesRepository colorTypesRepository) {
        this.brandColorMixRepository = brandColorMixRepository;
        this.brandColorMixService = brandColorMixService;
        this.specificCarModelRepository = specificCarModelRepository;
        this.colorMixRepository = colorMixRepository;
        this.colorTypesRepository = colorTypesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

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
    }

}
