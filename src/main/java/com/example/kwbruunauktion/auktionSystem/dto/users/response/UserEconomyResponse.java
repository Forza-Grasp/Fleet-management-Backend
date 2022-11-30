package com.example.kwbruunauktion.auktionSystem.dto.users.response;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserEconomy;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEconomyResponse {

  private Long id;

  private String email;

  private String userName;

  private String firstName;

  private String lastName;

  private String phoneNumber;

  private Ownership ownership;

  public UserEconomyResponse(UserEconomy userEconomy) {
    this.id = userEconomy.getId();
    this.email = userEconomy.getEmail();
    this.userName = userEconomy.getUsername();
    this.firstName = userEconomy.getFirstName();
    this.lastName = userEconomy.getLastName();
    this.phoneNumber = userEconomy.getPhoneNumber();
    this.ownership = userEconomy.getOwnership();
  }
}
