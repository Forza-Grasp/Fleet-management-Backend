package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LcdvCodeServiceTest {

    public static LcdvCodeService lcdvCodeService;
    public static LcdvCodeRepository lcdvCodeRepository;


    @BeforeAll
    public static void setup(@Autowired LcdvCodeRepository lcdvCode_Repository) {
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

        lcdvCodeRepository.saveAll(lcdvCodes);
    }


    @BeforeEach
    public void setupService() {
        lcdvCodeService = new LcdvCodeService(lcdvCodeRepository);
    }

    @Test
    void addLcdvCode() {
        List<LcdvCodeResponse> responsesBeforeAddition = lcdvCodeService.getAllLcdvCodes();

        LcdvCodeRequest lcr = LcdvCodeRequest.builder()
                .lcdvCode("6")
                .build();
        LcdvCodeResponse lcdvCodeResponse = lcdvCodeService.addLcdvCode(lcr);

        List<LcdvCodeResponse> responsesAfterAddition = lcdvCodeService.getAllLcdvCodes();
        assertNotEquals(responsesBeforeAddition, responsesAfterAddition);
        assertEquals(7,responsesAfterAddition.size());
    }

    @Test
    void getLcdvCodes() {
        List<LcdvCodeResponse> lcdvCodeResponses = lcdvCodeService.getAllLcdvCodes();
        assertNotEquals(0,lcdvCodeResponses.size());
        assertEquals(6,lcdvCodeResponses.size());
    }

    @Test
    void getLcdvCodeById() {
        LcdvCodeResponse lcdvCodeResponseToGet = lcdvCodeService.getLcdvCodeById(1L);
        assertEquals(lcdvCodeResponseToGet.getId(),1L);
        assertNotEquals(lcdvCodeResponseToGet.getId(),3L);
    }

    @Test
    void deleteLcdvCodeById() {
        List<LcdvCodeResponse> responsesBeforeDeletion = lcdvCodeService.getAllLcdvCodes();
        lcdvCodeService.deleteLcdvCodeById(1L);
        List<LcdvCodeResponse> responsesAfterDeletion = lcdvCodeService.getAllLcdvCodes();
        assertNotEquals(responsesBeforeDeletion.size(),responsesAfterDeletion.size());
        assertEquals(5,responsesAfterDeletion.size());
        assertNotEquals(1L,responsesAfterDeletion.get(0).getId());
        assertEquals(2L,responsesAfterDeletion.get(0).getId());
    }
}