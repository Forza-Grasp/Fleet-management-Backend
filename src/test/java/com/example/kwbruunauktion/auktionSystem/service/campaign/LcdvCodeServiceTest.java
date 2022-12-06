package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class LcdvCodeServiceTest {

    public static LcdvCodeService lcdvCodeService;
    public static LcdvCodeRepository lcdvCodeRepository;

    @BeforeAll
    public static void setup(@Autowired LcdvCodeRepository lcdvCode_Repository,
                             @Autowired LcdvCodeService lcdvCode_Service){
        lcdvCodeService = lcdvCode_Service;
        lcdvCodeRepository = lcdvCode_Repository;



        LcdvCode lcdvCode1 = LcdvCode.builder()
                .lcdvCode("1PP2A5LMZJB0A0D1")
                .campaign()



    }

    @Test
    void addLcdvCode() {
    }

    @Test
    void getLcdvCodes() {
    }

    @Test
    void getLcdvCodeById() {
    }

    @Test
    void deleteLcdvCodeById() {
    }
}