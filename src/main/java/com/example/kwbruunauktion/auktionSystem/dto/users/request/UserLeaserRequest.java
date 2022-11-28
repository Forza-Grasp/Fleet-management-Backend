package com.example.kwbruunauktion.auktionSystem.dto.users.request;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLeaserRequest {

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

    public static UserLeaser getUserLeaserEntity(UserLeaserRequest userLeaserRequest) {
        return UserLeaser.userLeaserBuilder()
                .id(userLeaserRequest.getId())
                .user(userLeaserRequest.getUsername())
                .email(userLeaserRequest.getEmail())
                .password(userLeaserRequest.getPassword())
                .firstName(userLeaserRequest.getFirstName())
                .lastName(userLeaserRequest.getLastName())
                .phoneNumber(userLeaserRequest.getPhoneNumber())
                .companyName(userLeaserRequest.getCompanyName())
                .companyEuVatNumber(userLeaserRequest.getCompanyEuVatNumber())
                .addressLine1(userLeaserRequest.getAddressLine1())
                .addressLine2(userLeaserRequest.getAddressLine2())
                .city(userLeaserRequest.getCity())
                .zipCode(userLeaserRequest.getZipCode())
                .country(userLeaserRequest.getCountry())
                .ownership(userLeaserRequest.getOwnership())
                .build();
    }

    public UserLeaserRequest(UserLeaser userLeaser) {
        this.id = userLeaser.getId();
        this.username = userLeaser.getUsername();
        this.email = userLeaser.getEmail();
        this.firstName = userLeaser.getFirstName();
        this.lastName = userLeaser.getLastName();
        this.phoneNumber = userLeaser.getPhoneNumber();
        this.companyName = userLeaser.getCompanyName();
        this.companyEuVatNumber = userLeaser.getCompanyEuVatNumber();
        this.addressLine1 = userLeaser.getAddressLine1();
        this.addressLine2 = userLeaser.getAddressLine2();
        this.city = userLeaser.getCity();
        this.zipCode = userLeaser.getZipCode();
        this.country = userLeaser.getCountry();
        this.ownership = userLeaser.getOwnership();
    }

}
