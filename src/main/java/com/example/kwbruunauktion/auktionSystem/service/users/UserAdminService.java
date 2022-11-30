package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.ResetPasswordRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserAdminRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserAdminResponse;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserAdminRepository;
import com.example.kwbruunauktion.security.repository.UserWithRolesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserAdminService {
  private final UserAdminRepository userAdminRepository;
  private final UserWithRolesRepository userWithRolesRepository;

  public UserAdminService(UserAdminRepository userAdminRepository, UserWithRolesRepository userWithRolesRepository) {
    this.userAdminRepository = userAdminRepository;
    this.userWithRolesRepository = userWithRolesRepository;
  }

  public List<UserAdminResponse> getAllUserAdmin() {
    List<UserAdmin> allUserAdminsList = userAdminRepository.findAll();
    return allUserAdminsList.stream().map(UserAdminResponse::new).toList();

  }

  public List<UserAdminResponse> getAllUserAdmin(Pageable p) {
    Page<UserAdmin> allUserAdminsList = userAdminRepository.findAll(p);
    return allUserAdminsList.stream().map(UserAdminResponse::new).toList();

  }

  public UserAdminResponse getUserAdminById(Long id) {
    UserAdmin foundUserAdmin = userAdminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return new UserAdminResponse(foundUserAdmin);
  }

  public UserAdminResponse addUserAdmin(UserAdminRequest userAdminRequest) {
    if (userAdminRepository.existsById(userAdminRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with this ID already exist");
    }if (userAdminRepository.existsByUsername(userAdminRequest.getUserName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this username already exist");
    }
    UserAdmin newUserAdmin = UserAdminRequest.getUserAdminEntity(userAdminRequest);
    newUserAdmin = userAdminRepository.save(newUserAdmin);
    return new UserAdminResponse(newUserAdmin);
  }

  public void deleteUserAdmin(@PathVariable Long id){
    userAdminRepository.deleteById(id);
  }

  public void updateUserAdmin(UserAdminRequest userAdminRequest) {
    if (!userAdminRepository.existsById(userAdminRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this ID doest not exist");
    }
    UserAdmin userAdmin = userAdminRepository.findById(userAdminRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    if (userAdminRequest.getUserName() != null) {
      userAdmin.setUsername(userAdminRequest.getUserName());
    }
    if (userAdminRequest.getFirstName() != null) {
      userAdmin.setFirstName(userAdminRequest.getFirstName());
    }
    if (userAdminRequest.getLastName() != null) {
      userAdmin.setLastName(userAdminRequest.getLastName());
    }
    if (userAdminRequest.getEmail() != null) {
      userAdmin.setEmail(userAdminRequest.getEmail());
    }
    if (userAdminRequest.getPhoneNumber() != null) {
      userAdmin.setPhoneNumber(userAdminRequest.getPhoneNumber());
    }
    if (userAdminRequest.getOwnerShip() != null) {
      userAdmin.setOwnership(userAdminRequest.getOwnerShip());
    }
    userAdminRepository.save(userAdmin);
  }

  public void resetPassword(ResetPasswordRequest resetPasswordRequest) {


  }

}
