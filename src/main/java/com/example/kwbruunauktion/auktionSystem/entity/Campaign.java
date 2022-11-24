package com.example.kwbruunauktion.auktionSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne (cascade = CascadeType.ALL)
    private CampaignCar campaignCar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private CampaignStatus campaignStatus;

    @Column(nullable = false, length = 100)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate activeDate;

    @OneToMany (mappedBy = "campaign")
    private List<LcdvCodes> lcdvCodes = new ArrayList<>();
}
