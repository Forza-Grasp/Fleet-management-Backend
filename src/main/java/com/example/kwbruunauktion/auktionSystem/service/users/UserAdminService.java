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

  public List<UserAdminResponse> getAll(){
    List<UserAdmin> allUserAdminsList = userAdminRepository.findAll();
    return allUserAdminsList.stream().map(UserAdminResponse::new).toList();

  }

  public UserAdminResponse getUserAdminById(Long id){
    UserAdmin foundUserAdmin = userAdminRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    return new UserAdminResponse(foundUserAdmin);
  }

  public void addUserAdmin(UserAdminRequest userAdminRequest){

  }

  public void deleteUserAdmin(@PathVariable Long id){
    userAdminRepository.deleteById(id);
  }

  public void updateUserAdmin(Long id, UserAdminRequest userAdminRequest){

  }



}
