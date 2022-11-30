package com.example.kwbruunauktion.auktionSystem.dto.users.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddCarBrandToUserRequest {

  private Long carBrandId;

  private Long userId;

}
