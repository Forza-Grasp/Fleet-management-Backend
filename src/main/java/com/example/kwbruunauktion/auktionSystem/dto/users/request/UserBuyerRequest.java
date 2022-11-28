package com.example.kwbruunauktion.auktionSystem.dto.users.request;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBuyerRequest {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String companyName;
    private String companyEuVatNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipCode;
    private String country;


    private Ownership ownership;


    public static UserBuyer getUserBuyerEntity(UserBuyerRequest userBuyerRequest) {
        return UserBuyer.userBuyerBuilder()

                .user(userBuyerRequest.getUsername())
                .email(userBuyerRequest.getEmail())
                .firstName(userBuyerRequest.getFirstName())
                .lastName(userBuyerRequest.getLastName())
                .phoneNumber(userBuyerRequest.getPhoneNumber())
                .companyName(userBuyerRequest.getCompanyName())
                .companyEuVatNumber(userBuyerRequest.getCompanyEuVatNumber())
                .addressLine1(userBuyerRequest.getAddressLine1())
                .addressLine2(userBuyerRequest.getAddressLine2())
                .city(userBuyerRequest.getCity())
                .zipCode(userBuyerRequest.getZipCode())
                .country(userBuyerRequest.getCountry())
                .ownership(userBuyerRequest.getOwnership())
                .build();
    }













}
