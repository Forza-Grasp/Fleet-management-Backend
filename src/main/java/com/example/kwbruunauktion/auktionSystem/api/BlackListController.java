package com.example.kwbruunauktion.auktionSystem.api;

import com.example.kwbruunauktion.auktionSystem.dto.blackList.BlackListRequest;
import com.example.kwbruunauktion.auktionSystem.dto.blackList.BlackListResponse;
import com.example.kwbruunauktion.auktionSystem.service.BlackListService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: 02/12/2022 Test endpoints in Postman

@RestController
@CrossOrigin
@RequestMapping("/api/blacklist")

public class BlackListController {

  final private BlackListService blackListService;

  public BlackListController(BlackListService blackListService) {
    this.blackListService = blackListService;
  }

  @GetMapping
  public List<BlackListResponse> getAllBlackList(Pageable p) {
    return blackListService.getAllBlackListedCars(p);
  }
  @GetMapping("/all")
  public List<BlackListResponse> getAllBlackList() {
    return blackListService.getAllBlackListedCars();
  }

  @GetMapping("/{vinNumber}")
  public BlackListResponse findBlackListByVinNumber(@PathVariable String vinNumber) {
    return blackListService.getBlackListedCarByVinNumber(vinNumber);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void addBlackListedCar(@RequestBody BlackListRequest blackListRequest) {
    blackListService.addBlackListedCar(blackListRequest);
  }

  @PatchMapping("/de-activate/{vinNumber}")
  public void deActivateBlackListedCar(@PathVariable String vinNumber) {
    blackListService.deActivateBlackList(vinNumber);
  }
  @PatchMapping("/activate/{vinNumber}")
  public void activateBlackListedCar(@PathVariable String vinNumber) {
    blackListService.activateBlackList(vinNumber);
  }

  @DeleteMapping("/{vinNumber}")
  public void deleteBlackListedCar(@PathVariable String vinNumber) {
    blackListService.deleteBlackList(vinNumber);
  }




}
