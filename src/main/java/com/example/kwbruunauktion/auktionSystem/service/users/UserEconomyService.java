package com.example.kwbruunauktion.auktionSystem.service.users;


import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserEconomyRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserEconomyResponse;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserEconomy;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserEconomyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserEconomyService {

  UserEconomyRepository userEconomyRepository;

  public UserEconomyService(UserEconomyRepository userEconomyRepository) {
    this.userEconomyRepository = userEconomyRepository;
  }

  public List<UserEconomyResponse> getAllUserEconomy() {
    List<UserEconomy> userEconomyList = userEconomyRepository.findAll();
    return userEconomyList.stream().map(UserEconomyResponse::new).toList();
  }

  public List<UserEconomyResponse> getAllUserEconomy(Pageable p) {
    Page<UserEconomy> userEconomyList = userEconomyRepository.findAll(p);
    return userEconomyList.stream().map(UserEconomyResponse::new).toList();
  }

  public UserEconomyResponse getUserEconomyById(Long id) {
    UserEconomy userEconomy = userEconomyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return new UserEconomyResponse(userEconomy);
  }

  public void deleteUserEconomy(@PathVariable Long id) {
    userEconomyRepository.deleteById(id);
  }

  public void addUserEconomy(UserEconomyRequest userEconomyRequest) {
    if (userEconomyRepository.existsById(userEconomyRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this ID already exist");
    }
    if (userEconomyRepository.existsByUsername(userEconomyRequest.getUserName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this username already exist");
    }
    UserEconomy userEconomy = UserEconomyRequest.getUserEconomyEntity(userEconomyRequest);
    userEconomy = userEconomyRepository.save(userEconomy);
    new UserEconomyResponse(userEconomy);
  }

  public void updateUserEconomy(UserEconomyRequest userEconomyRequest) {
    if (!userEconomyRepository.existsById(userEconomyRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this ID does not exist");
    }
    UserEconomy userEconomy = userEconomyRepository.findById(userEconomyRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    if (userEconomyRequest.getUserName() != null) {
      userEconomy.setUsername(userEconomyRequest.getUserName());
    }
    if (userEconomyRequest.getFirstName() != null) {
      userEconomy.setFirstName(userEconomyRequest.getFirstName());
    }
    if (userEconomyRequest.getLastName() != null) {
      userEconomy.setLastName(userEconomyRequest.getLastName());
    }
    if (userEconomyRequest.getEmail() != null) {
      userEconomy.setEmail(userEconomyRequest.getEmail());
    }
    if (userEconomyRequest.getPhoneNumber() != null) {
      userEconomy.setPhoneNumber(userEconomyRequest.getPhoneNumber());
    }
    if (userEconomyRequest.getOwnership() != null) {
      userEconomy.setOwnership(userEconomyRequest.getOwnership());
    }
    userEconomyRepository.save(userEconomy);
  }

}
