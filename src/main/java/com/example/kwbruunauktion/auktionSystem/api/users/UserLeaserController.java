package com.example.kwbruunauktion.auktionSystem.api.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserLeaserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserLeaserResponse;
import com.example.kwbruunauktion.auktionSystem.service.users.UserLeaserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users/leaser")
public class UserLeaserController {

  private final UserLeaserService userLeaserService;

  public UserLeaserController(UserLeaserService userLeaserService) {
    this.userLeaserService = userLeaserService;
  }

  @GetMapping
  public List<UserLeaserResponse> getAllUserLeasers() {
    return userLeaserService.getAllUserLeasers();
  }

  @GetMapping("/{id}")
  public UserLeaserResponse getUserLeaserById(@PathVariable Long id) {
    return userLeaserService.getUserLeaserById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserLeaserResponse addUserLeaser(@RequestBody UserLeaserRequest userLeaserRequest) {
    return userLeaserService.createUserLeaser(userLeaserRequest);
  }

  @PutMapping
  public void editUserLeaser(@RequestBody UserLeaserRequest userLeaserRequest) {
    userLeaserService.editUserLeaser(userLeaserRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteUserLeaser(@PathVariable Long id) {
    userLeaserService.deleteUserLeaser(id);
  }
}
