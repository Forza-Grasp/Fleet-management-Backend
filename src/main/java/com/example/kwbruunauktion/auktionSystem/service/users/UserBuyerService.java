package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.AddCarBrandToUserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserBuyerRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserBuyerResponse;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Service
public class UserBuyerService {

  private final UserBuyerRepository userBuyerRepository;
  private final SpecificCarModelRepository specificCarModelRepository;

  public UserBuyerService(UserBuyerRepository userBuyerRepository,
                          SpecificCarModelRepository specificCarModelRepository) {
    this.userBuyerRepository = userBuyerRepository;
    this.specificCarModelRepository = specificCarModelRepository;
  }

  // View all user buyers
  public List<UserBuyerResponse> getAllUserBuyers() {
    return userBuyerRepository.findAll().stream().map(UserBuyerResponse::new).toList();
  }

  // View user buyer by id
  public UserBuyerResponse getUserBuyerById(@PathVariable Long id) {
    if (userBuyerRepository.existsById(id)) {
      return new UserBuyerResponse(userBuyerRepository.findById(id).orElseThrow(() -> new RuntimeException("User buyer not found")));
    } else {
      throw new RuntimeException("User buyer with this ID does not exist");
    }
  }

  // Create user buyer
  public UserBuyerResponse createUserBuyer(UserBuyerRequest userBuyerRequest) {
    if (userBuyerRepository.existsByUsername(userBuyerRequest.getUsername())) {
      throw new RuntimeException("User buyer with this Username already exist");
    }

    UserBuyer newUserBuyer = UserBuyerRequest.getUserBuyerEntity(userBuyerRequest);
    newUserBuyer = userBuyerRepository.save(newUserBuyer);

    return new UserBuyerResponse(newUserBuyer);

  }

  // Edit user buyer
  public void editUserBuyer(UserBuyerRequest userBuyerRequest) {
    UserBuyer userBuyer = userBuyerRepository.findById(userBuyerRequest.getId()).orElseThrow(() -> new RuntimeException("User buyer with this ID does not exist"));

    userBuyer.setEmail(userBuyerRequest.getEmail());
    userBuyer.setUsername(userBuyerRequest.getUsername());
    userBuyer.setFirstName(userBuyerRequest.getFirstName());
    userBuyer.setLastName(userBuyerRequest.getLastName());
    userBuyer.setPhoneNumber(userBuyerRequest.getPhoneNumber());
    userBuyer.setCompanyName(userBuyerRequest.getCompanyName());
    userBuyer.setCompanyEuVatNumber(userBuyerRequest.getCompanyEuVatNumber());
    userBuyer.setAddressLine1(userBuyerRequest.getAddressLine1());
    userBuyer.setAddressLine2(userBuyerRequest.getAddressLine2());

    userBuyer.setCity(userBuyerRequest.getCity());
    userBuyer.setZipCode(userBuyerRequest.getZipCode());
    userBuyer.setCountry(userBuyerRequest.getCountry());
    userBuyer.setOwnership(userBuyerRequest.getOwnership());
    // Missing ViewableCarBrands


    userBuyerRepository.save(userBuyer);
  }

  // Delete user buyer
  public void deleteUserBuyer(Long id) {
    if (!userBuyerRepository.existsById(id)) {
      throw new RuntimeException("User buyer with this ID does not exist");
    } else {
      userBuyerRepository.deleteById(id);
    }
  }

  // add car brand to user buyer
  public UserBuyerResponse addCarBrandToUserBuyer(AddCarBrandToUserRequest addCarBrandToUserRequest) {
    UserBuyer userBuyer = userBuyerRepository.findById(addCarBrandToUserRequest.getUserId()).orElseThrow(() -> new RuntimeException("User buyer with this ID does not exist"));
    SpecificCarModel specificCarModel = specificCarModelRepository.findById(addCarBrandToUserRequest.getCarBrandId()).orElseThrow(() -> new RuntimeException("Car brand with this ID does not exist"));
    System.out.println("\n"+addCarBrandToUserRequest+"\n");
    if (userBuyer.getViewableCarBrands() != null){
      userBuyer.getViewableCarBrands().add(specificCarModel);
      userBuyerRepository.save(userBuyer);
      return new UserBuyerResponse(userBuyer);
    }
    List<SpecificCarModel> specificCarModelList = Collections.singletonList(specificCarModel);
    userBuyer.setViewableCarBrands(specificCarModelList);
    userBuyerRepository.save(userBuyer);
    return new UserBuyerResponse(userBuyer);
  }
}