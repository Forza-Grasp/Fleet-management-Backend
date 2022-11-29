package com.example.kwbruunauktion.auktionSystem.entity.damageMatrix;

import com.example.kwbruunauktion.security.entity.UserWithRoles;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
public class DamageMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserWithRoles userWithRoles;

    @Column(length = 100, nullable = false)
    private String valuta;

    @OneToMany(mappedBy = "damageMatrix",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SpecificDamage> specificDamage;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
