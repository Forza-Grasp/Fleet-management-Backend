package com.example.kwbruunauktion.auktionSystem.entity.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class CampaignColorPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_color_mix_id")
    private BrandColorMix brandColorMix;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @Column(length = 100)
    private double price;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

}
