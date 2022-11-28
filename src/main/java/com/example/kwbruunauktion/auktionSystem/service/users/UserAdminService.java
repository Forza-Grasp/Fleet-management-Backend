package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserAdminRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserAdminResponse;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserAdminService {
  private final UserAdminRepository userAdminRepository;

  public UserAdminService(UserAdminRepository userAdminRepository) {
    this.userAdminRepository = userAdminRepository;
  }

  public List<UserAdminResponse> getAllUserAdmin(){
    List<UserAdmin> allUserAdminsList = userAdminRepository.findAll();
    return allUserAdminsList.stream().map(UserAdminResponse::new).toList();

  }

  public UserAdminResponse getUserAdminById(Long id){
    UserAdmin foundUserAdmin = userAdminRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    return new UserAdminResponse(foundUserAdmin);
  }

  public void addUserAdmin(UserAdminRequest userAdminRequest){
    if (userAdminRepository.existsById(userAdminRequest.getId())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with this ID already exist");
    }if (userAdminRepository.existsByUsername(userAdminRequest.getUserName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this username already exist");
    }
    UserAdmin newUserAdmin = UserAdminRequest.getUserAdminEntity(userAdminRequest);
    newUserAdmin = userAdminRepository.save(newUserAdmin);
    new UserAdminResponse(newUserAdmin);
  }

  public void deleteUserAdmin(@PathVariable Long id){
    userAdminRepository.deleteById(id);
  }

  public void updateUserAdmin(Long id, UserAdminRequest userAdminRequest){
    if (!userAdminRepository.existsById(userAdminRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this ID doest not exist");
    }
    UserAdmin userAdmin = userAdminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    userAdmin.setUsername(userAdminRequest.getUserName());
    userAdmin.setFirstName(userAdminRequest.getFirstName());
    userAdmin.setLastName(userAdminRequest.getLastName());
    userAdmin.setEmail(userAdminRequest.getEmail());
    userAdmin.setPhoneNumber(userAdminRequest.getPhoneNumber());
    userAdmin.setOwnership(userAdminRequest.getOwnerShip());
    userAdminRepository.save(userAdmin);
  }



}
