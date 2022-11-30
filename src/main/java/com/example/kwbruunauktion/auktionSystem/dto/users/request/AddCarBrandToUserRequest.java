package com.example.kwbruunauktion.auktionSystem.dto.users.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddCarBrandToUserRequest {

  private Long carBrandId;

  private Long userId;

}
