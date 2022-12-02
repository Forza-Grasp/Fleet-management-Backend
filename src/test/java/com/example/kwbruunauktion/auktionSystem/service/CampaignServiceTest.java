package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.entity.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.LcdvCodes;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.example.kwbruunauktion.auktionSystem.repository.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.LcdvCodesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CampaignServiceTest {
    public static CampaignRepository campaignRepository;

}