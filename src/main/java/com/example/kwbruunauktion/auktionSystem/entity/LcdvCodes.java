package com.example.kwbruunauktion.auktionSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "lcdvCodes")
    @ToString.Exclude
    private List<Campaign> campaign = new ArrayList<>();
}
