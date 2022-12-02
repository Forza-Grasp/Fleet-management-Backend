package com.example.kwbruunauktion.auktionSystem.dto.users.request;

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
public class UserAdminRequest {

  private Long id;

  private String firstName;

  private String lastName;

  private String phoneNumber;

  private String email;

  private Ownership ownerShip;

  private String password;

  private String userName;

  public static UserAdmin getUserAdminEntity(UserAdminRequest u){
    return UserAdmin.userAdminBuilder()
        .firstName(u.getFirstName())
        .lastName(u.getLastName())
        .password(u.getPassword())
        .phoneNumber(u.getPhoneNumber())
        .email(u.getEmail())
        .ownership(u.getOwnerShip())
        .user(u.getUserName())
        .build();
  }

  public UserAdminRequest(UserAdmin userAdmin) {
    this.id = userAdmin.getId();
    this.firstName = userAdmin.getFirstName();
    this.lastName = userAdmin.getLastName();
    this.phoneNumber = userAdmin.getPhoneNumber();
    this.email = userAdmin.getEmail();
    this.ownerShip = userAdmin.getOwnership();
    this.userName = userAdmin.getUsername();
    this.password = userAdmin.getPassword();
  }

}
