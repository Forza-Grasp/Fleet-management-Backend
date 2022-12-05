package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.BlackList;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCar;
import com.example.kwbruunauktion.auktionSystem.enums.BlackListStatus;
import com.example.kwbruunauktion.auktionSystem.repository.BlackListRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarRepository;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
public class BlackListConfiguration implements ApplicationRunner {

  private final SpecificCarRepository specificCarRepository;
  private final BlackListRepository blackListRepository;

  public BlackListConfiguration(SpecificCarRepository specificCarRepository, BlackListRepository blackListRepository) {
    this.specificCarRepository = specificCarRepository;
    this.blackListRepository = blackListRepository;
  }

  @Override
  @SneakyThrows
  public void run(ApplicationArguments args) {


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

    List<BlackListStatus> statuses = Collections.singletonList(BlackListStatus.ACTIVE);

    BlackList blackList1 = BlackList.builder()
        .vinNumber(car1.getChassisNumber())
        .status(statuses)
        .build();
    BlackList blackList2 = BlackList.builder()
        .vinNumber(car2.getChassisNumber())
        .status(statuses)
        .build();
    specificCarRepository.save(car1);
    specificCarRepository.save(car2);
    blackListRepository.save(blackList1);
    blackListRepository.save(blackList2);

  }
}

