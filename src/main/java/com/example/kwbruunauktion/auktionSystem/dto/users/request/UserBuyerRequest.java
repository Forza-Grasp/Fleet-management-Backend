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
    private String password;
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
                .password(userBuyerRequest.getPassword())
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
                .build();
    }

    public UserBuyerRequest(UserBuyer userBuyer) {
        this.id = userBuyer.getId();
        this.username = userBuyer.getUsername();
        this.email = userBuyer.getEmail();
        this.firstName = userBuyer.getFirstName();
        this.lastName = userBuyer.getLastName();
        this.phoneNumber = userBuyer.getPhoneNumber();
        this.companyName = userBuyer.getCompanyName();
        this.companyEuVatNumber = userBuyer.getCompanyEuVatNumber();
        this.addressLine1 = userBuyer.getAddressLine1();
        this.addressLine2 = userBuyer.getAddressLine2();
        this.password = userBuyer.getPassword();
        this.city = userBuyer.getCity();
        this.zipCode = userBuyer.getZipCode();
        this.country = userBuyer.getCountry();
        this.ownership = userBuyer.getOwnership();
    }













}
