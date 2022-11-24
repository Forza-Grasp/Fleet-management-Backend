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
    private Long id;

    @Column(nullable = false, length = 400)
    private String lcdvCode;

    @JoinTable(name = "lcdvcodes_campaign")
    @ManyToMany
    @ToString.Exclude
    private List<Campaign> campaign;
}
