package com.example.kwbruunauktion.auktionSystem.dto.blackList;

import com.example.kwbruunauktion.auktionSystem.entity.BlackList;
import com.example.kwbruunauktion.auktionSystem.enums.BlackListStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlackListRequest {

  private Long id;
  private String vinNumber;

  private BlackListStatus status;

  private List<BlackListStatus> statusList;

  public static BlackList getBlackListEntity(BlackListRequest b) {
    return BlackList.builder()
        .id(b.getId())
        .vinNumber(b.getVinNumber())
        .status(b.getStatusList())
        .build();
  }

  public BlackListRequest(BlackList blackList) {
    this.id = blackList.getId();
    this.vinNumber = blackList.getVinNumber();
    this.status = blackList.getStatus().get(0);
  }

}
