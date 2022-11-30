package com.example.kwbruunauktion.auktionSystem.dto.users.response;

import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLeaserResponse {

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

    private List<SpecificCarModelResponse> viewableCarBrands;
    private Ownership ownership;


    public UserLeaserResponse(UserLeaser userLeaser){
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
        if (userLeaser.getViewableCarBrands() != null) {
            this.viewableCarBrands = userLeaser.getViewableCarBrands().stream().map(SpecificCarModelResponse::new).toList();
        }
        this.ownership = userLeaser.getOwnership();

    }
}
