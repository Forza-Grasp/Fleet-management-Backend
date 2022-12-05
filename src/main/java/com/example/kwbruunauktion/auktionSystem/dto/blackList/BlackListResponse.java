package com.example.kwbruunauktion.auktionSystem.dto.blackList;

import com.example.kwbruunauktion.auktionSystem.entity.BlackList;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlackListResponse {

  private Long id;

  private String vinNumber;

  private String status;

  public BlackListResponse(BlackList b) {
    this.id = b.getId();
    this.vinNumber = b.getVinNumber();
    this.status = b.getStatus().get(0).toString();
  }


}
