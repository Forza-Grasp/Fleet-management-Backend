package com.example.kwbruunauktion.auktionSystem.api.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserAdminRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserAdminResponse;
import com.example.kwbruunauktion.auktionSystem.service.users.UserAdminService;
import com.example.kwbruunauktion.auktionSystem.dto.users.request.ResetPasswordRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users/admin")
public class UserAdminController {

  private final UserAdminService userAdminService;

  public UserAdminController(UserAdminService userAdminService) {
    this.userAdminService = userAdminService;
  }

  @GetMapping
  List<UserAdminResponse> getAllUserAdmin(Pageable p) {
    return userAdminService.getAllUserAdmin(p);
  }

  @GetMapping("/all")
  List<UserAdminResponse> getAllUserAdmin() {
    return userAdminService.getAllUserAdmin();
  }

  @GetMapping("/{id}")
  UserAdminResponse getUserAdminById(@PathVariable Long id) {
    return userAdminService.getUserAdminById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserAdminResponse addUserAdmin(@RequestBody UserAdminRequest userAdminRequest) {
    return userAdminService.addUserAdmin(userAdminRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteUserAdmin(@PathVariable Long id) {
    userAdminService.deleteUserAdmin(id);
  }

  @PutMapping
  public void updateUserAdmin(@RequestBody UserAdminRequest userAdminRequest) {
    userAdminService.updateUserAdmin(userAdminRequest);
  }

}
