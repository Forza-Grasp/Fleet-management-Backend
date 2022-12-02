package com.example.kwbruunauktion.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

  private Long userId;
  private String username;
  private String token;
  private List<String> roles;
  private String eMail;

  public LoginResponse(String userName, String token, List<String> roles, Long userId, String eMail) {
    this.username = userName;
    this.token = token;
    this.roles = roles;
    this.userId = userId;
    this.eMail = eMail;
  }
}