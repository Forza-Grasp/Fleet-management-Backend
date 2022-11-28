package com.example.kwbruunauktion.auktionSystem.entity.users;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.security.entity.Role;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class UserLeaser extends UserWithRoles {

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false)
    private String phoneNumber;

    @Column(length = 100, nullable = false)
    private String companyName;

    @Column(length = 100, nullable = false)
    private String companyEuVatNumber;

    @Column(length = 10, nullable = false)
    private String addressLine1;

    @Column(length = 100, nullable = false)
    private String addressLine2;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String postalCode;

    @Column(length = 100, nullable = false)
    private String country;

    @JoinTable(name = "viewableCarBrands")
    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    List<SpecificCarModel> viewableCarBrands;

    @OneToOne(cascade = CascadeType.ALL)
    private Ownership ownership;

    @Builder(builderMethodName = "userLeaserBuilder")
    public UserLeaser(String user, String password, String email, String firstName, String lastName, String phoneNumber, String companyName, String companyEuVatNumber, String addressLine1, String addressLine2, String city, String postalCode, String country, List<SpecificCarModel> viewableCarBrands, Ownership ownership) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.companyEuVatNumber = companyEuVatNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.viewableCarBrands = viewableCarBrands;
        this.ownership = ownership;
        getRoles().add(Role.LEASER);
    }
}
