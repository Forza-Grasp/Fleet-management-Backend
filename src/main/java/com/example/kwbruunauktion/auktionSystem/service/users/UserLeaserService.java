package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.AddCarBrandToUserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserLeaserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserBuyerResponse;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserLeaserResponse;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserLeaserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserLeaserService {

    private final UserLeaserRepository userLeaserRepository;
    private final SpecificCarModelRepository specificCarModelRepository;

    public UserLeaserService(UserLeaserRepository userLeaserRepository,
                             SpecificCarModelRepository specificCarModelRepository) {
        this.userLeaserRepository = userLeaserRepository;
        this.specificCarModelRepository = specificCarModelRepository;
    }

    // view all user leasers
    public List<UserLeaserResponse> getAllUserLeasers() {
        return userLeaserRepository.findAll().stream().map(UserLeaserResponse::new).toList();
    }

    // view user leaser by id
    public UserLeaserResponse getUserLeaserById(Long id) {
        if (userLeaserRepository.existsById(id)) {
            return new UserLeaserResponse(userLeaserRepository.findById(id).orElseThrow(() -> new RuntimeException("User leaser not found")));
        } else {
            throw new RuntimeException("User leaser with this ID does not exist");
        }

    }

    // create user leaser
    public UserLeaserResponse createUserLeaser(UserLeaserRequest userLeaserRequest) {
        if (userLeaserRepository.existsById(userLeaserRequest.getId())) {
            throw new RuntimeException("User leaser with this ID already exist");
        } if (userLeaserRepository.existsByUsername(userLeaserRequest.getUsername())) {
            throw new RuntimeException("User leaser with this Username already exist");
        }

        UserLeaser newUserLeaser = UserLeaserRequest.getUserLeaserEntity(userLeaserRequest);
        newUserLeaser = userLeaserRepository.save(newUserLeaser);

        return new UserLeaserResponse(newUserLeaser);

    }

    // edit user leaser
    public void editUserLeaser(UserLeaserRequest userLeaserRequest) {
        UserLeaser userLeaser = userLeaserRepository.findById(userLeaserRequest.getId()).orElseThrow(() -> new RuntimeException("User leaser with this ID does not exist"));

        userLeaser.setEmail(userLeaserRequest.getEmail());
        userLeaser.setUsername(userLeaserRequest.getUsername());
        userLeaser.setFirstName(userLeaserRequest.getFirstName());
        userLeaser.setLastName(userLeaserRequest.getLastName());
        userLeaser.setPhoneNumber(userLeaserRequest.getPhoneNumber());
        userLeaser.setCompanyName(userLeaserRequest.getCompanyName());
        userLeaser.setCompanyEuVatNumber(userLeaserRequest.getCompanyEuVatNumber());
        userLeaser.setAddressLine1(userLeaserRequest.getAddressLine1());
        userLeaser.setAddressLine2(userLeaserRequest.getAddressLine2());
        userLeaser.setZipCode(userLeaserRequest.getZipCode());
        userLeaser.setCity(userLeaserRequest.getCity());
        userLeaser.setCountry(userLeaserRequest.getCountry());
        userLeaser.setOwnership(userLeaserRequest.getOwnership());

        userLeaserRepository.save(userLeaser);
    }

    // delete user leaser
    public void deleteUserLeaser(Long id) {
        if (!userLeaserRepository.existsById(id)) {
            throw new RuntimeException("User leaser with this ID does not exist");
        } else {
            userLeaserRepository.deleteById(id);
        }
    }

    // add car brand to user leaser
    public UserLeaserResponse addCarBrandToUserLeaser(AddCarBrandToUserRequest addCarBrandToUserRequest) {
        UserLeaser userLeaser = userLeaserRepository.findById(addCarBrandToUserRequest.getUserId()).orElseThrow(() -> new RuntimeException("User leaser with this ID does not exist"));
        SpecificCarModel specificCarModel = specificCarModelRepository.findById(addCarBrandToUserRequest.getCarBrandId()).orElseThrow(() -> new RuntimeException("Car brand with this ID does not exist"));
        if (userLeaser.getViewableCarBrands() != null){
            userLeaser.getViewableCarBrands().add(specificCarModel);
            userLeaserRepository.save(userLeaser);
            return new UserLeaserResponse(userLeaser);
        }
        List<SpecificCarModel> specificCarModelList = Collections.singletonList(specificCarModel);
        userLeaser.setViewableCarBrands(specificCarModelList);
        userLeaserRepository.save(userLeaser);
        return new UserLeaserResponse(userLeaser);
    }
}