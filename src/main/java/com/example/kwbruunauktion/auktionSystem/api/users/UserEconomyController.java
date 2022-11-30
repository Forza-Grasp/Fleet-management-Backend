package com.example.kwbruunauktion.auktionSystem.api.users;


import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserEconomyRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserEconomyResponse;
import com.example.kwbruunauktion.auktionSystem.service.users.UserEconomyService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users/economy")
public class UserEconomyController {

  private final UserEconomyService userEconomyService;

  public UserEconomyController(UserEconomyService userEconomyService) {
    this.userEconomyService = userEconomyService;
  }

  @GetMapping
  List<UserEconomyResponse> getAllUserEconomy(Pageable p) {
    return userEconomyService.getAllUserEconomy(p);
  }

  @GetMapping("/all")
  List<UserEconomyResponse> getAllUserEconomy() {
    return userEconomyService.getAllUserEconomy();
  }

  @GetMapping("/{id}")
  UserEconomyResponse getUserEconomyById(@PathVariable Long id) {
    return userEconomyService.getUserEconomyById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void addUserEconomy(@RequestBody UserEconomyRequest userEconomyRequest) {
    userEconomyService.addUserEconomy(userEconomyRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteUserEconomy(@PathVariable Long id) {
    userEconomyService.deleteUserEconomy(id);
  }

  @PutMapping()
  public void updateUserEconomy(@RequestBody UserEconomyRequest userEconomyRequest) {
    userEconomyService.updateUserEconomy(userEconomyRequest);
  }

}
