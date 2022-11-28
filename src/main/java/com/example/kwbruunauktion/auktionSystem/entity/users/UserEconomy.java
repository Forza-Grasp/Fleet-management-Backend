package com.example.kwbruunauktion.auktionSystem.entity.users;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
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

@Entity
public class UserEconomy extends UserWithRoles {

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Ownership ownership;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;


}
