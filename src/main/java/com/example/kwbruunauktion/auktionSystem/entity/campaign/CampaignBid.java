package com.example.kwbruunauktion.auktionSystem.entity.campaign;

import com.example.kwbruunauktion.auktionSystem.enums.CampaignBidStatus;

import com.example.kwbruunauktion.security.entity.UserWithRoles;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class CampaignBid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserWithRoles userWithRoles;

    @ManyToOne(cascade = CascadeType.ALL)
    private Campaign campaign;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private CampaignBidStatus campaignBidStatus;

    @Column(nullable = false, length = 100)
    private int minAmountOfCars;

    @Column(nullable = false, length = 100)
    private int maxAmountOfCars;

    @Column(nullable = false, length = 100)
    private double bidPrice;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;


}
