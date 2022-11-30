package com.example.kwbruunauktion.auktionSystem.dto.users.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResetPasswordRequest {

  @NonNull
  private String userName;

  @NonNull
  private String newPassword;

}
