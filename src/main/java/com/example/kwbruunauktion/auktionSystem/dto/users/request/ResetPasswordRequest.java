package com.example.kwbruunauktion.auktionSystem.dto.users.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResetPasswordRequest {

  @NonNull
  private String userName;

  @NonNull
  private String newPassword;

}
