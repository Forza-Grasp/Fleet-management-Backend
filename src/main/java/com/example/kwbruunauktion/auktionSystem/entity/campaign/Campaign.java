package com.example.kwbruunauktion.auktionSystem.entity.campaign;

import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Long id;

    @OneToOne (cascade = CascadeType.PERSIST)
    private CampaignCar campaignCar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private CampaignStatus campaignStatus;

    @Column(nullable = false, length = 100)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate activeDate;

    @JoinTable(name = "campaign_lcdvcodes")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<LcdvCodes> lcdvCodes;

    @OneToMany(mappedBy = "campaign")
    @ToString.Exclude
    private List<CampaignBid> campaignBids;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<CampaignColorPrice> campaignColorPrices;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

}
