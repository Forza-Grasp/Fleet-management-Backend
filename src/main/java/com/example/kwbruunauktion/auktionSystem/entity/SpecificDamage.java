package com.example.kwbruunauktion.auktionSystem.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class SpecificDamage {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 450,nullable = false)
    private String damages;

    @Column(length = 50,nullable = false)
    private double price;

    @ManyToOne
    private DamageMatrix damageMatrix;
}
