package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesRequest;
import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesResponse;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
class ColorTypesServiceTest {

    public static ColorTypesRepository colorTypesRepository;
    public static ColorTypesService colorTypesService;

    public static ColorMixService colorMixService;

    @BeforeAll
    public static void initiateData(@Autowired ColorTypesRepository colorTypesRepository){
        ColorTypesServiceTest.colorTypesRepository = colorTypesRepository;

        ColorTypes colorTypes1 = ColorTypes.builder()
                .id(1L)
                .type("Metallic")
                .colorMixes(new ArrayList<>())
                .created(LocalDateTime.of(2022, 11, 27,12,0,0,0))
                .updated(LocalDateTime.of(2022, 11, 28,12,0,0,0))
                .build();

        ColorTypes colorTypes2 = ColorTypes.builder()
                .id(2L)
                .type("Pearle-scent")
                .colorMixes(new ArrayList<>())
                .created(LocalDateTime.of(2022, 11, 27,12,0,0,0))
                .updated(LocalDateTime.of(2022, 11, 28,12,0,0,0))
                .build();

        ColorTypes colorTypes3 = ColorTypes.builder()
                .id(3L)
                .type("Matte")
                .colorMixes(new ArrayList<>())
                .created(LocalDateTime.of(2022, 11, 27,12,0,0,0))
                .updated(LocalDateTime.of(2022, 11, 28,12,0,0,0))
                .build();

        colorTypesRepository.save(colorTypes1);
        colorTypesRepository.save(colorTypes2);
        colorTypesRepository.save(colorTypes3);
    }

    @BeforeEach
    public void initiateServiceClass(){
        colorTypesService = new ColorTypesService(colorTypesRepository, colorMixService);

    }


    @Test
    void addColorType() {
        assertEquals(colorTypesService.getAllColorTypes().size(),3);

        ColorTypes colorTypes4 = ColorTypes.builder()
                .id(4L)
                .type("Matte")
                .colorMixes(new ArrayList<>())
                .created(LocalDateTime.of(2022, 11, 27,12,0,0,0))
                .updated(LocalDateTime.of(2022, 11, 28,12,0,0,0))
                .build();
        colorTypesRepository.save(colorTypes4);

        assertEquals(colorTypesService.getAllColorTypes().size(),4);

        ColorTypesResponse foundColorTypeResponse = colorTypesService.getColorTypeById(4L);

        assertEquals(foundColorTypeResponse.getType(), "Matte");
        assertEquals(foundColorTypeResponse.getId(),4L);
        assertNotEquals(foundColorTypeResponse.getType(),"Shiny");
    }

    @Test
    void getAllColorTypes() {
        assertEquals(colorTypesService.getAllColorTypes().size(), 3);
        assertNotEquals(colorTypesService.getAllColorTypes().size(), 4);
        assertNotEquals(colorTypesService.getAllColorTypes().size(), 0);
    }

    @Test
    void getColorTypeById() {
        assertEquals(colorTypesService.getColorTypeById(1L).getType(),"Metallic");
        assertEquals(colorTypesService.getColorTypeById(2L).getType(),"Pearle-scent");
        assertEquals(colorTypesService.getColorTypeById(3L).getType(),"Matte");
        assertNotEquals(colorTypesService.getColorTypeById(3L).getType(),"Metallic");
        assertEquals(colorTypesService.getColorTypeById(1L).getId(),1L);
        assertNotEquals(colorTypesService.getColorTypeById(1L).getId(),3L);
    }

    @Test
    void editColorType() {
        ColorTypesRequest colorTypesRequest = new ColorTypesRequest(
                ColorTypes.builder()
                .id(1L)
                .type("Matte")
                .build()
                );

        colorTypesService.editColorType(colorTypesRequest);
        assertNotEquals(colorTypesService.getColorTypeById(1L).getType(),"Metallic");
        assertEquals(colorTypesService.getColorTypeById(1L).getType(),"Matte");

    }

    @Test
    void deleteColorTypeById() {
        //None deleted, id1 in first place
        assertEquals(colorTypesService.getAllColorTypes().get(0).getId(),1L);
        //None deleted, size=3
        assertEquals(colorTypesService.getAllColorTypes().size(),3);
        //delete 1
        colorTypesService.deleteColorTypeById(1L);
        //1 deleted, size should be one smaller(2)
        assertEquals(colorTypesService.getAllColorTypes().size(),2);
        //id1 should not be existing in place 0
        assertNotEquals(colorTypesService.getAllColorTypes().get(0).getId(),1L);
        //id in place 0 should equal prior second id(id2)
        assertEquals(colorTypesService.getAllColorTypes().get(0).getId(),2L);
    }
}