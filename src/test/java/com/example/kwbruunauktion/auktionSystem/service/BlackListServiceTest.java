package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.blackList.BlackListRequest;
import com.example.kwbruunauktion.auktionSystem.dto.blackList.BlackListResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BlackList;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCar;
import com.example.kwbruunauktion.auktionSystem.enums.BlackListStatus;
import com.example.kwbruunauktion.auktionSystem.repository.BlackListRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BlackListServiceTest {

  public BlackListService blackListService;
  public static BlackListRepository blackListRepository;
  public static SpecificCarRepository specificCarRepository;

  @BeforeAll
  public static void setupData(@Autowired BlackListRepository blackList_Repository,
                               @Autowired SpecificCarRepository specificCar_Repository) {
    blackListRepository = blackList_Repository;
    specificCarRepository = specificCar_Repository;


    SpecificCar car1 = SpecificCar.builder()
        .chassisNumber("123456789")
        .model("Fiesta")
        .firstRegDate(LocalDate.now())
        .facModelCodeL("S10")
        .holdPeriod("1")
        .modelText("FiestaText")
        .modelYearCode("2008")
        .regNumberImp("123456")
        .regMonths("1")
        .origModelCode1("S10")
        .returnDate(LocalDate.now().plusDays(1))
        .colorCode("1")
        .trimCode("trim")
        .build();
    SpecificCar car2 = SpecificCar.builder()
        .chassisNumber("12345")
        .model("Fiesta")
        .firstRegDate(LocalDate.now())
        .facModelCodeL("S10")
        .holdPeriod("1")
        .modelText("FiestaText")
        .modelYearCode("2008")
        .regNumberImp("123456")
        .regMonths("1")
        .origModelCode1("S10")
        .colorCode("123")
        .returnDate(LocalDate.now().plusDays(1))
        .trimCode("trim")
        .build();

    BlackList blackList1 = BlackList.builder()
        .vinNumber(car1.getChassisNumber())
        .status(Collections.singletonList(BlackListStatus.ACTIVE))
        .build();
    BlackList blackList2 = BlackList.builder()
        .vinNumber(car2.getChassisNumber())
        .status(Collections.singletonList(BlackListStatus.INACTIVE))
        .build();
    specificCarRepository.save(car1);
    specificCarRepository.save(car2);
    blackListRepository.save(blackList1);
    blackListRepository.save(blackList2);



  }

  @BeforeEach
  public void setBlackListService(){
    blackListService = new BlackListService(blackListRepository, specificCarRepository);
  }

  @Test
  void getAllBlackListedCars() {
    List<BlackListResponse> listResponses = blackListService.getAllBlackListedCars();
    assertEquals(2, listResponses.size());
  }

  @Test
  void getBlackListedCarByVinNumber() {
    BlackListResponse blackListResponse = blackListService.getBlackListedCarByVinNumber("123456789");
    assertEquals("123456789", blackListResponse.getVinNumber());
  }

  @Test
  void addBlackListedCar() {
    BlackList blackList = BlackList.builder()
        .id(3L)
        .vinNumber("1234567899")
        .status(Collections.singletonList(BlackListStatus.ACTIVE))
        .build();
    blackListService.addBlackListedCar(new BlackListRequest(blackList));
    assertEquals("ACTIVE", blackListRepository.findById(3L).get().getStatus().get(0).toString());
    assertEquals(3, blackListRepository.count());

  }

  @Test
  void deActivateBlackList() {
    SpecificCar s = specificCarRepository.findById(1L).orElseThrow();
    blackListService.deActivateBlackList(s.getChassisNumber());
    assertEquals("INACTIVE", blackListRepository.findById(1L).get().getStatus().get(0).toString());
  }

  @Test
  void activateBlackList() {
    SpecificCar s = specificCarRepository.findById(2L).orElseThrow();
    blackListService.activateBlackList(s.getChassisNumber());
    assertEquals("ACTIVE", blackListRepository.findById(2L).get().getStatus().get(0).toString());
  }

  @Test
  void deleteBlackList() {
    SpecificCar car = specificCarRepository.findById(1L).orElseThrow();
    blackListService.deleteBlackList(car.getChassisNumber());
    assertEquals(1, blackListRepository.count());
    assertEquals("INACTIVE", blackListRepository.findById(2L).get().getStatus().get(0).toString());
  }
}