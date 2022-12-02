package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.blackList.BlackListRequest;
import com.example.kwbruunauktion.auktionSystem.dto.blackList.BlackListResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BlackList;
import com.example.kwbruunauktion.auktionSystem.enums.BlackListStatus;
import com.example.kwbruunauktion.auktionSystem.repository.BlackListRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class BlackListService {

  private final BlackListRepository blackListRepository;
  private final SpecificCarRepository specificCarRepository;

  public BlackListService(BlackListRepository blackListRepository,
                          SpecificCarRepository specificCarRepository) {
    this.blackListRepository = blackListRepository;
    this.specificCarRepository = specificCarRepository;
  }


  public List<BlackListResponse> getAllBlackListedCars() {
    List<BlackList> blackList = blackListRepository.findAll();
    return blackList.stream().map(BlackListResponse::new).toList();
  }

  public List<BlackListResponse> getAllBlackListedCars(Pageable p) {
    Page<BlackList> blackList = blackListRepository.findAll(p);
    return blackList.stream().map(BlackListResponse::new).toList();
  }

  public BlackListResponse getBlackListedCarByVinNumber(@PathVariable String vinNumber) {
    BlackList blackList = blackListRepository.findBlackListByVinNumber(vinNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blacklist not found"));
    return new BlackListResponse(blackList);
  }

  public void addBlackListedCar(BlackListRequest blackListRequest) {
    if (blackListRepository.existsById(blackListRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Blacklist already exist");
    }
    if (specificCarRepository.existsByChassisNumber(blackListRequest.getVinNumber())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SpecificCarModel already exist");
    }
    if (blackListRequest.getStatus() == BlackListStatus.ACTIVE) {
      BlackList blackList = BlackList.blackListBuilderActive()
          .vinNumber(blackListRequest.getVinNumber())
          .status(Collections.singletonList(BlackListStatus.ACTIVE))
          .build();
      blackListRepository.save(blackList);
    }else if (blackListRequest.getStatus() == BlackListStatus.INACTIVE) {
      BlackList blackList = BlackList.blackListBuilderActive()
          .vinNumber(blackListRequest.getVinNumber())
          .status(Collections.singletonList(BlackListStatus.INACTIVE))
          .build();
      blackListRepository.save(blackList);
    }
  }
  public void deActivateBlackList(@PathVariable String vinNumber) {
    BlackList blackList = blackListRepository.findBlackListByVinNumber(vinNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blacklist not found"));
    List<BlackListStatus> statusList = new ArrayList<BlackListStatus>();
    statusList.add(BlackListStatus.INACTIVE);
    blackList.setStatus(statusList);
    blackListRepository.save(blackList);
  }

  public void activateBlackList(@PathVariable String vinNumber) {
    BlackList blackList = blackListRepository.findBlackListByVinNumber(vinNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blacklist not found"));
    List<BlackListStatus> statusList = new ArrayList<BlackListStatus>();
    statusList.add(BlackListStatus.ACTIVE);
    blackList.setStatus(statusList);
    blackListRepository.save(blackList);
  }

  public void deleteBlackList(@PathVariable String vinNumber) {
    BlackList blackList = blackListRepository.findBlackListByVinNumber(vinNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blacklist not found"));
    blackListRepository.delete(blackList);

  }

}
