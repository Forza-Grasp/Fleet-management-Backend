package com.example.kwbruunauktion.auktionSystem.entity;

import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


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
    private Long id;

    @OneToOne (cascade = CascadeType.ALL)
    private CampaignCar campaignCar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private CampaignStatus campaignStatus;

    @Column(nullable = false, length = 100)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate activeDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private LcdvCodes lcdvCodes;

    @OneToOne (mappedBy = "campaign")
    private CampaignBid campaignBid;
}
