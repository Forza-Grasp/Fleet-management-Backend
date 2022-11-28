package com.example.kwbruunauktion.auktionSystem.entity.users;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.security.entity.Role;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "user_economy")
public class UserEconomy extends UserWithRoles {

    @Column(length = 100, nullable = false,columnDefinition = "varchar(255) default 'no value'")
    private String firstName;

    @Column(length = 100, nullable = false,columnDefinition = "varchar(255) default 'no value'")
    private String lastName;

    @Column(length = 100, nullable = false,columnDefinition = "varchar(255) default 'no value'")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Ownership ownership;


    @Builder(builderMethodName = "userEconomyBuilder")
    public UserEconomy(Long id, String user, String password, String email, String firstName, String lastName, String phoneNumber, Ownership ownership) {
        super(id, user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.ownership = ownership;

        getRoles().add(Role.ECONOMY);
    }
}
