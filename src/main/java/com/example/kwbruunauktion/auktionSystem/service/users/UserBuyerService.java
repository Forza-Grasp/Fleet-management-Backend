package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserBuyerRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserBuyerResponse;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBuyerService {

    UserBuyerRepository userBuyerRepository;

    public UserBuyerService(UserBuyerRepository userBuyerRepository) {
        this.userBuyerRepository = userBuyerRepository;
    }

    // View all user buyers
    public List<UserBuyerResponse> getAllUserBuyers() {
        return userBuyerRepository.findAll().stream().map(UserBuyerResponse::new).toList();
    }

    // View user buyer by id
    public UserBuyerResponse getUserBuyerById(Long id) {
        if (userBuyerRepository.existsById(id)) {
            return new UserBuyerResponse(userBuyerRepository.findById(id).orElseThrow(() -> new RuntimeException("User buyer not found")));
        } else {
            throw new RuntimeException("User buyer with this ID does not exist");
        }
    }

    // Create user buyer
    public UserBuyerResponse createUserBuyer(UserBuyerRequest userBuyerRequest) {
        if (userBuyerRepository.existsById(userBuyerRequest.getId())) {
            throw new RuntimeException("User buyer with this ID already exist");
        } if (userBuyerRepository.existsByUsername(userBuyerRequest.getUsername())) {
            throw new RuntimeException("User buyer with this Username already exist");
        }

        UserBuyer newUserBuyer = UserBuyerRequest.getUserBuyerEntity(userBuyerRequest);
        newUserBuyer = userBuyerRepository.save(newUserBuyer);

        return new UserBuyerResponse(newUserBuyer);

    }

    // Edit user buyer
    public void editUserBuyer(UserBuyerRequest userBuyerRequest, Long id) {
        UserBuyer userBuyer = userBuyerRepository.findById(id).orElseThrow(() -> new RuntimeException("User buyer with this ID does not exist"));

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

}