package com.example.kwbruunauktion.auktionSystem.api.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.AddCarBrandToUserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserBuyerRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserBuyerResponse;
import com.example.kwbruunauktion.auktionSystem.service.users.UserBuyerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users/buyer")
public class UserBuyerController {

  private final UserBuyerService userBuyerService;

  public UserBuyerController(UserBuyerService userBuyerService) {
    this.userBuyerService = userBuyerService;
  }

  @GetMapping
  public List<UserBuyerResponse> getAllUserBuyers() {
    return userBuyerService.getAllUserBuyers();
  }

  @GetMapping("/{id}")
  public UserBuyerResponse getUserBuyerById(@PathVariable Long id) {
    return userBuyerService.getUserBuyerById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserBuyerResponse addUserBuyer(@RequestBody UserBuyerRequest userBuyerRequest) {
    return userBuyerService.createUserBuyer(userBuyerRequest);
  }

  @PutMapping
  public void editUserBuyer(@RequestBody UserBuyerRequest userBuyerRequest) {
    userBuyerService.editUserBuyer(userBuyerRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteUserBuyer(@PathVariable Long id) {
    userBuyerService.deleteUserBuyer(id);
  }

  @PostMapping(value = "/addCarBrand",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserBuyerResponse addCarBrandToUserBuyer(@RequestBody AddCarBrandToUserRequest addCarBrandToUserRequest) {
    return userBuyerService.addCarBrandToUserBuyer(addCarBrandToUserRequest);
  }

}
