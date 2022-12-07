package com.example.kwbruunauktion.auktionSystem.entity.campaign;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class LcdvCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 400)
    private String lcdvCode;

    @OneToMany(mappedBy = "lcdvCode", cascade = CascadeType.ALL)
    List<CampaignLcdvCodeJoin> campaignLcdvCodeJoins;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
