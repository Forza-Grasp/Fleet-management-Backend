package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BrandColorMixServiceTest {

    public static BrandColorMixRepository brandColorMixRepository;
    public static BrandColorMixService brandColorMixService;
    public static SpecificCarModelRepository specificCarModelRepository;
    public static ColorMixRepository colorMixRepository;
    public static ColorMixService colorMixService;
    public static ColorTypesRepository colorTypesRepository;

    public static ColorTypesService colorTypesService;

    @BeforeAll
    public static void initData(@Autowired BrandColorMixRepository brand_Color_Mix_Repository,
                                @Autowired SpecificCarModelRepository specific_Car_Model_Repository,
                                @Autowired ColorMixRepository color_Mix_Repository,
                                @Autowired ColorTypesRepository color_Types_Repository) {

        brandColorMixRepository = brand_Color_Mix_Repository;
        specificCarModelRepository = specific_Car_Model_Repository;
        colorMixRepository = color_Mix_Repository;
        colorTypesRepository = color_Types_Repository;

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

    @BeforeEach
    public void setUpService() {brandColorMixService = new BrandColorMixService(brandColorMixRepository, specificCarModelRepository, colorMixRepository);
    colorTypesService = new ColorTypesService(colorTypesRepository, colorMixService);}

    @Test
    void getBrandColorMixById() {
        BrandColorMixResponse brandColorMixResponse = brandColorMixService.getBrandColorMixById(1L);
        assertEquals("Ford Fiesta", brandColorMixResponse.getSpecificCarModel().getBrand());
        assertEquals("S10", brandColorMixResponse.getSpecificCarModel().getModel());
        assertEquals("2008", brandColorMixResponse.getSpecificCarModel().getModelYear());

        assertEquals("Silver 2012 grade A", brandColorMixResponse.getColorMix().getColorName());
        assertEquals("S12A", brandColorMixResponse.getColorMix().getColorCode());
        assertEquals("Metallic", brandColorMixResponse.getColorMix().getColorTypesResponse().getType());


    }

    @Test
    void getAllBrandColorMix() {
        List<BrandColorMixResponse> listOfBrandColorMix = brandColorMixService.getAllBrandColorMix();

        assertEquals(3, listOfBrandColorMix.size());
        assertEquals("Ford Fiesta", listOfBrandColorMix.get(0).getSpecificCarModel().getBrand());
        assertEquals("Silver 2012 grade A", listOfBrandColorMix.get(0).getColorMix().getColorName());
        assertEquals("Metallic", listOfBrandColorMix.get(0).getColorMix().getColorTypesResponse().getType());

        assertEquals("Mazda", listOfBrandColorMix.get(1).getSpecificCarModel().getBrand());
        assertEquals("Silver 2012 grade A", listOfBrandColorMix.get(0).getColorMix().getColorName());
        assertEquals("Metallic", listOfBrandColorMix.get(0).getColorMix().getColorTypesResponse().getType());

        assertEquals("Mercedes", listOfBrandColorMix.get(2).getSpecificCarModel().getBrand());
        assertEquals("Silver 2012 grade A", listOfBrandColorMix.get(0).getColorMix().getColorName());
        assertEquals("Metallic", listOfBrandColorMix.get(0).getColorMix().getColorTypesResponse().getType());
    }

    @Test
    void addBrandColorMix() {
        List<BrandColorMixResponse> listOfBrandColorMix = brandColorMixService.getAllBrandColorMix();
        assertEquals(3, listOfBrandColorMix.size());

        BrandColorMix brandColorMix = BrandColorMix.builder()
                .id(4L)
                .build();
        BrandColorMixRequest brandColorMixRequest = new BrandColorMixRequest(4L, 2L, 2L);
        brandColorMixService.addBrandColorMix(brandColorMixRequest);

        List<BrandColorMixResponse> listOfBrandColorMixAfterAdd = brandColorMixService.getAllBrandColorMix();
        assertEquals(4, listOfBrandColorMixAfterAdd.size());

        assertEquals("Mazda", listOfBrandColorMixAfterAdd.get(3).getSpecificCarModel().getBrand());
        assertEquals("F5", listOfBrandColorMixAfterAdd.get(3).getSpecificCarModel().getModel());
        assertEquals("2015", listOfBrandColorMixAfterAdd.get(3).getSpecificCarModel().getModelYear());

        assertEquals("Red Shiny", listOfBrandColorMixAfterAdd.get(3).getColorMix().getColorName());
        assertEquals("RS", listOfBrandColorMixAfterAdd.get(3).getColorMix().getColorCode());
        assertEquals("Shiny", listOfBrandColorMixAfterAdd.get(3).getColorMix().getColorTypesResponse().getType());

    }

    @Test
    void editBrandColorMix() {
        List<BrandColorMixResponse> listOfBrandColorMix = brandColorMixService.getAllBrandColorMix();
        assertEquals(3, listOfBrandColorMix.size());
        assertEquals("Ford Fiesta", listOfBrandColorMix.get(0).getSpecificCarModel().getBrand());
        assertEquals("Metallic", listOfBrandColorMix.get(0).getColorMix().getColorTypesResponse().getType());

        BrandColorMixRequest brandColorMixRequest = new BrandColorMixRequest(1L, 3L, 2L);
        brandColorMixService.editBrandColorMix(brandColorMixRequest);

        List<BrandColorMixResponse> listOfBrandColorMixModified = brandColorMixService.getAllBrandColorMix();
        assertEquals(3, listOfBrandColorMixModified.size());
        assertEquals("Mercedes", listOfBrandColorMixModified.get(0).getSpecificCarModel().getBrand());
        assertEquals("Red Shiny", listOfBrandColorMixModified.get(0).getColorMix().getColorName());
    }

    @Test
    void deleteBrandColorMix() {
       List<BrandColorMixResponse> listOfBrandColorMix = brandColorMixService.getAllBrandColorMix();
       assertEquals(3, listOfBrandColorMix.size());
       assertEquals("Ford Fiesta", listOfBrandColorMix.get(0).getSpecificCarModel().getBrand());

       brandColorMixService.deleteBrandColorMix(1L);

       List<BrandColorMixResponse> listOfBrandColorMixNew = brandColorMixService.getAllBrandColorMix();
       assertEquals(2, listOfBrandColorMixNew.size());
       assertEquals("Mazda", listOfBrandColorMixNew.get(0).getSpecificCarModel().getBrand());
    }
}