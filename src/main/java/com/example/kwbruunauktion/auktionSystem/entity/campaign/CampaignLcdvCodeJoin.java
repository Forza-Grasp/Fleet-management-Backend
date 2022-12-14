package com.example.kwbruunauktion.auktionSystem.entity.campaign;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CampaignLcdvCodeJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Campaign campaign;

    @ManyToOne
    LcdvCode lcdvCode;
}
