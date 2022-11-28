package com.example.kwbruunauktion.auktionSystem.entity.users;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.security.entity.Role;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class UserAdmin extends UserWithRoles {
    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Ownership ownership;


    @Builder(builderMethodName = "userAdminBuilder")
    public UserAdmin(String user, String password, String email, String firstName, String lastName, String phoneNumber, Ownership ownership) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.ownership = ownership;
        getRoles().add(Role.ADMIN);
    }
}
