package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
class ColorMixServiceTest {

    public static ColorMixService colorMixService;
    public static ColorMixRepository colorMixRepository;
    public static ColorTypesRepository colorTypesRepository;

    @BeforeAll
    public static void dataInitializer(@Autowired ColorMixRepository colorMixRepo, @Autowired ColorTypesRepository colorTypesRepo){
        colorMixRepository = colorMixRepo;
        colorTypesRepository = colorTypesRepo;
        colorMixRepository.deleteAll();
        colorTypesRepository.deleteAll();

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
    }

    @BeforeEach
    public void setUpService(){
        colorMixService = new ColorMixService(colorMixRepository, colorTypesRepository);
    }

    @Test
    void getAll() {
        List<ColorMixResponse> colorMixResponses = colorMixService.getAll();
        assertEquals(colorMixResponses.size(), 3);

        assertNotEquals(colorMixResponses.size(), 1);
        assertNotEquals(colorMixResponses.size(), 2);
        assertNotEquals(colorMixResponses.size(), 4);
    }

    @Test
    void getSpecificById() {
        ColorMixResponse colorMixResponse = colorMixService.getSpecificById(1L);

        assertEquals(colorMixResponse.getColorCode(), "S12A");
        assertEquals(colorMixResponse.getColorName(), "Silver 2012 grade A");


        assertNotEquals(colorMixResponse.getColorCode(), "s12a");
        assertNotEquals(colorMixResponse.getColorName(), "Gold 2012 grade A");
    }

    @Test
    void addColorMix() {
        int listSize = 3;
        List<ColorMixResponse> colorMixResponses = colorMixService.getAll();
        assertEquals(colorMixResponses.size(), listSize);

        ColorTypes colorType1 = ColorTypes.builder()
                .id(1L)
                .type("Metallic")
                .build();

        ColorMix colorMix1 = ColorMix.builder()
                .colorCode("WBMW")
                .colorName("White BMW")
                .colorType(colorType1)
                .build();

        ColorMixRequest colorMixRequest = new ColorMixRequest(colorMix1);

        colorMixService.addColorMix(colorMixRequest);

        List<ColorMixResponse> newColorMixResponses = colorMixService.getAll();


        assertEquals(newColorMixResponses.size(), listSize + 1);

        assertNotEquals(newColorMixResponses.size(), listSize);



    }

    @Test
    void editColorMix() {
        ColorMixResponse colorMixResponse = colorMixService.getSpecificById(2L);
        assertEquals(colorMixResponse.getColorName(), "Red Shiny");


        ColorTypes colorType1 = ColorTypes.builder()
                .id(1L)
                .type("Metallic")
                .build();

        ColorMix colorMix1 = ColorMix.builder()
                .id(2L)
                .colorCode("BNS")
                .colorName("Blue NOT shiny")
                .colorType(colorType1)
                .build();

        ColorMixRequest colorMixRequest = new ColorMixRequest(colorMix1);
        colorMixService.editColorMix(colorMixRequest);


        ColorMixResponse newColorMixResponse = colorMixService.getSpecificById(2L);
        assertEquals(newColorMixResponse.getColorName(), "Blue NOT shiny");
        assertNotEquals(newColorMixResponse.getColorName(), "Red Shiny");
    }

    @Test
    void deleteColorMix() {
        int listSize = 3;
        List<ColorMixResponse> colorMixResponses = colorMixService.getAll();
        assertEquals(colorMixResponses.size(), listSize);
        assertNotEquals(colorMixResponses.size(), 2);

        colorMixService.deleteColorMix(1L);

        colorMixResponses = colorMixService.getAll();
        assertEquals(colorMixResponses.size(), listSize - 1);
        assertNotEquals(colorMixResponses.size(), listSize);
        assertNotEquals(colorMixResponses.size(), listSize + 1);

    }
}