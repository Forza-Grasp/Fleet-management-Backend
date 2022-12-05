package com.example.kwbruunauktion.auktionSystem.entity;

import com.example.kwbruunauktion.auktionSystem.enums.BlackListStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString

@Entity(name = "blacklist")
public class BlackList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "VIN_NUMBER")
  private String vinNumber;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('ACTIVE','INACTIVE')", name = "STATUS")
  @ElementCollection(fetch = FetchType.EAGER)
  List<BlackListStatus> status;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  @Builder(builderMethodName = "blackListBuilderActive")
  public BlackList(Long id, String vinNumber, List<BlackListStatus> status, LocalDateTime created, LocalDateTime updated) {
    this.id = id;
    this.vinNumber = vinNumber;
    this.status = status;
    this.created = created;
    this.updated = updated;
  }
}
