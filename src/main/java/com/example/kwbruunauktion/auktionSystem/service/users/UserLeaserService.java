package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserLeaserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserLeaserResponse;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserLeaserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLeaserService {

    UserLeaserRepository userLeaserRepository;

    public UserLeaserService(UserLeaserRepository userLeaserRepository) {
        this.userLeaserRepository = userLeaserRepository;
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
    public void editUserLeaser(UserLeaserRequest userLeaserRequest, Long id) {
        UserLeaser userLeaser = userLeaserRepository.findById(id).orElseThrow(() -> new RuntimeException("User leaser with this ID does not exist"));

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
}