package com.example.kwbruunauktion.auktionSystem.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class LcdvCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 400)
    private String lcdvCode;

    @ManyToOne
    private Campaign campaign;
}
