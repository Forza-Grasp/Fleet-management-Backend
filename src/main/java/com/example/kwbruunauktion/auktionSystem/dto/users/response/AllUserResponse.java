package com.example.kwbruunauktion.auktionSystem.dto.users.response;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserEconomy;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.security.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) der er udkommenteret fordi så kan vi kontrollerer om der er null eller ej i frontend
public class AllUserResponse {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String companyName;

    private String companyEuVatNumber;

    private String adressLine1;

    private String adressLine2;

    private String city;

    private String zipCode;

    private String country;

    private String userName;

    private String eMail;

    private Role role;


    public AllUserResponse(UserBuyer u) {
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.phoneNumber = u.getPhoneNumber();
        this.companyName = u.getCompanyName();
        this.companyEuVatNumber = u.getCompanyEuVatNumber();
        this.adressLine1 = u.getAddressLine1();
        this.adressLine2 = u.getAddressLine2();
        this.city = u.getCity();
        this.zipCode = u.getZipCode();
        this.country = u.getCountry();
        this.userName = u.getUsername();
        this.eMail = u.getEmail();
        this.role = u.getRoles().get(0);
    }

    public AllUserResponse(UserLeaser u) {
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.phoneNumber = u.getPhoneNumber();
        this.companyName = u.getCompanyName();
        this.companyEuVatNumber = u.getCompanyEuVatNumber();
        this.adressLine1 = u.getAddressLine1();
        this.adressLine2 = u.getAddressLine2();
        this.city = u.getCity();
        this.zipCode = u.getZipCode();
        this.country = u.getCountry();
        this.userName = u.getUsername();
        this.eMail = u.getEmail();
        this.role = u.getRoles().get(0);
    }

    public AllUserResponse(UserAdmin u) {
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.phoneNumber = u.getPhoneNumber();
        this.userName = u.getUsername();
        this.eMail = u.getEmail();
        this.role = u.getRoles().get(0);
    }
    public AllUserResponse(UserEconomy u) {
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.phoneNumber = u.getPhoneNumber();
        this.userName = u.getUsername();
        this.eMail = u.getEmail();
        this.role = u.getRoles().get(0);
    }
}
