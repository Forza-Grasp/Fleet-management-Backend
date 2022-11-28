package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserAdminRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserAdminServiceTest {


  public UserAdminService userAdminService;

  public static UserAdminRepository userAdminRepository;

  @BeforeAll
  public static void setupData(@Autowired UserAdminRepository userAdmin_Repository){
    userAdminRepository = userAdmin_Repository;
    List<UserAdmin> userAdminList = List.of(
        UserAdmin.userAdminBuilder()
            .firstName("Peter")
            .lastName("Parker")
            .phoneNumber("12345678")
            .email("peter_parker@admin.dk")
            .password("1234")
            .user("peter_parker_admin")
            .ownership(Ownership.builder()
                .name("ownership1")
                .abbreviation("own1")
                .build())
            .build(),
        UserAdmin.userAdminBuilder()
            .firstName("Jens")
            .lastName("Hansen")
            .phoneNumber("12345678")
            .email("Jens_Hansen@admin.dk")
            .password("12345")
            .user("Jens_Hansen_admin")
            .ownership(Ownership.builder()
                .name("ownership2")
                .abbreviation("own2")
                .build())
            .build(),
        UserAdmin.userAdminBuilder()
            .firstName("Kasper")
            .lastName("Olsen")
            .phoneNumber("12345678")
            .email("Kasper_Olsen@admin.dk")
            .password("123456")
            .user("Kasper_Olsen_admin")
            .ownership(Ownership.builder()
                .name("ownership3")
                .abbreviation("own3")
                .build())
            .build(),
        UserAdmin.userAdminBuilder()
            .firstName("Marie")
            .lastName("Hedegaard")
            .phoneNumber("12345678")
            .email("Marie_Hedegaard@admin.dk")
            .password("1234567")
            .user("Marie_Hedegaard_admin")
            .ownership(Ownership.builder()
                .name("ownership4")
                .abbreviation("own4")
                .build())
            .build()
    );
  }

  @Test
  void getAll() {
  }

  @Test
  void getUserAdminById() {
  }

  @Test
  void addUserAdmin() {
  }

  @Test
  void deleteUserAdmin() {
  }

  @Test
  void updateUserAdmin() {
  }
}