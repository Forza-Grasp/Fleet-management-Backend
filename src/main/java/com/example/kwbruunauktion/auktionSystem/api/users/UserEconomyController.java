package com.example.kwbruunauktion.auktionSystem.api.users;

import com.example.kwbruunauktion.auktionSystem.service.users.UserEconomyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/users/economy")
public class UserEconomyController {

  private final UserEconomyService userEconomyService;

  public UserEconomyController(UserEconomyService userEconomyService) {
    this.userEconomyService = userEconomyService;
  }


}
