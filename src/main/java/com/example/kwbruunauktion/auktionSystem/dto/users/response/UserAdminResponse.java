package com.example.kwbruunauktion.auktionSystem.dto.users.response;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAdminResponse {


  private Long id;

  private String firstName;

  private String lastName;

  private String phoneNumber;

  private String email;

  private Ownership ownerShip;

  private String userName;

  public UserAdminResponse(UserAdmin userAdmin) {
    this.id = userAdmin.getId();
    this.firstName = userAdmin.getFirstName();
    this.lastName = userAdmin.getLastName();
    this.phoneNumber = userAdmin.getPhoneNumber();
    this.email = userAdmin.getEmail();
    this.ownerShip = userAdmin.getOwnership();
    this.userName = userAdmin.getUsername();
  }
}
