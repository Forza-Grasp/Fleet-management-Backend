package com.example.kwbruunauktion.auktionSystem.entity;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100, nullable = false)
    String name;

    @Column(length = 100, nullable = false)
    String abbreviation;

    @OneToOne(mappedBy = "ownership")
    private UserAdmin userAdmin;

    @OneToOne(mappedBy = "ownership")
    private UserEconomy userEconomy;

    @OneToOne(mappedBy = "ownership")
    private UserLeaser userLeaser;

    @OneToOne(mappedBy = "ownership")
    private UserBuyer userBuyer;


}
