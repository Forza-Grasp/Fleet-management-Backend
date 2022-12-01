package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelRequest;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SpecificCarModelServiceTest {

    public static SpecificCarModelRepository specificCarModelRepository;

    public static SpecificCarModelService specificCarModelService;

    @BeforeAll
    public static void initData(@Autowired SpecificCarModelRepository specific_CarModel_Repository) {
        specificCarModelRepository = specific_CarModel_Repository;

        SpecificCarModel specificCarModel = SpecificCarModel.builder()
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

        specificCarModelRepository.save(specificCarModel);
        specificCarModelRepository.save(specificCarModel2);

    }

    @BeforeEach
    public void setSpecificCarModelServiceUp(){specificCarModelService = new SpecificCarModelService(specificCarModelRepository);}

    @Test
    void getSpecificCarModelById() {
        SpecificCarModelResponse specificCarModelResponse = specificCarModelService.getSpecificCarModelById(1L);
        assertEquals("Ford Fiesta", specificCarModelResponse.getBrand());
        assertEquals("S10", specificCarModelResponse.getModel());
        assertEquals("2008", specificCarModelResponse.getModelYear());
        assertNotEquals("Mazda", specificCarModelResponse.getBrand());
        assertNotEquals("F5", specificCarModelResponse.getModel());
        assertNotEquals("2015", specificCarModelResponse.getModelYear());

        SpecificCarModelResponse specificCarModelResponse2 = specificCarModelService.getSpecificCarModelById(2L);
        assertEquals("Mazda", specificCarModelResponse2.getBrand());
        assertEquals("F5", specificCarModelResponse2.getModel());
        assertEquals("2015", specificCarModelResponse2.getModelYear());
        assertNotEquals("Ford Fiesta", specificCarModelResponse2.getBrand());
        assertNotEquals("S10", specificCarModelResponse2.getModel());
        assertNotEquals("2008", specificCarModelResponse2.getModelYear());


        assertNotSame(specificCarModelResponse, specificCarModelResponse2);
    }

    @Test
    void getSpecificCarModels() {
        List<SpecificCarModelResponse> listOfSpecificCarModels = specificCarModelService.getSpecificCarModels();

        assertEquals(2, listOfSpecificCarModels.size());
        assertEquals("Mazda", listOfSpecificCarModels.get(1).getBrand());

        assertNotEquals(3, listOfSpecificCarModels.size());
        assertNotEquals("Mazda", listOfSpecificCarModels.get(0).getBrand());

        assertNotSame(listOfSpecificCarModels.get(0), listOfSpecificCarModels.get(1));
    }

    @Test
    void addSpecificCarModel() {
        List<SpecificCarModelResponse> listOfSpecificCarModels = specificCarModelService.getSpecificCarModels();
        assertEquals(2,listOfSpecificCarModels.size());

        SpecificCarModel specificCarModel = SpecificCarModel.builder()
                .id(3L)
                .brand("Mercedes")
                .model("C25")
                .modelYear("2022")
                .build();

        SpecificCarModel specificCarModel2 = SpecificCarModel.builder()
                .id(4L)
                .brand("Opel")
                .model("E22")
                .modelYear("2000")
                .build();

        SpecificCarModelRequest specificCarModelRequest = new SpecificCarModelRequest(specificCarModel);
        SpecificCarModelRequest specificCarModelRequest2 = new SpecificCarModelRequest(specificCarModel2);
        specificCarModelService.addSpecificCarModel(specificCarModelRequest);
        specificCarModelService.addSpecificCarModel(specificCarModelRequest2);

        List<SpecificCarModelResponse> updatedListOfSpecificCarModels = specificCarModelService.getSpecificCarModels();

        assertEquals(4, updatedListOfSpecificCarModels.size());
        assertEquals("Mercedes", updatedListOfSpecificCarModels.get(2).getBrand());
        assertEquals("C25", updatedListOfSpecificCarModels.get(2).getModel());
        assertEquals("2022", updatedListOfSpecificCarModels.get(2).getModelYear());

        assertEquals("Opel", updatedListOfSpecificCarModels.get(3).getBrand());
        assertEquals("E22", updatedListOfSpecificCarModels.get(3).getModel());
        assertEquals("2000", updatedListOfSpecificCarModels.get(3).getModelYear());

    }

    @Test
    void editSpecificCarModel() {
        List<SpecificCarModelResponse> listOfSpecificCarModels = specificCarModelService.getSpecificCarModels();
        assertEquals(2, listOfSpecificCarModels.size());
        assertEquals("Ford Fiesta", listOfSpecificCarModels.get(0).getBrand());

        SpecificCarModel specificCarModel = SpecificCarModel.builder()
                .id(1L)
                .brand("Citroen")
                .model("C500")
                .modelYear("1999")
                .build();
        SpecificCarModelRequest specificCarModelRequest = new SpecificCarModelRequest(specificCarModel);
        specificCarModelService.editSpecificCarModel(specificCarModelRequest);

        List<SpecificCarModelResponse> listOfSpecificCarModels2 = specificCarModelService.getSpecificCarModels();
        assertEquals(2, listOfSpecificCarModels2.size());
        assertNotEquals("Ford Fiesta", listOfSpecificCarModels2.get(0).getBrand());
        assertEquals("Citroen", listOfSpecificCarModels2.get(0).getBrand());

    }

    @Test
    void deleteSpecificCarModel() {
        List<SpecificCarModelResponse> listOfSpecificCarModels = specificCarModelService.getSpecificCarModels();
        assertEquals(2, listOfSpecificCarModels.size());
        assertEquals("Ford Fiesta", listOfSpecificCarModels.get(0).getBrand());

        specificCarModelService.deleteSpecificCarModel(1L);

        List<SpecificCarModelResponse> listOfSpecificCarModels2 = specificCarModelService.getSpecificCarModels();
        assertEquals(1, listOfSpecificCarModels2.size());
        assertEquals("Mazda", listOfSpecificCarModels2.get(0).getBrand());
    }
}