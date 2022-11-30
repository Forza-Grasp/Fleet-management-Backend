package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserAdminRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserAdminResponse;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserAdminRepository;
import com.example.kwbruunauktion.security.repository.UserWithRolesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserAdminServiceTest {


  public UserAdminService userAdminService;

  public static UserAdminRepository userAdminRepository;
  public static UserWithRolesRepository userWithRolesRepository;

  @BeforeAll
  public static void setupData(@Autowired UserAdminRepository userAdmin_Repository,
                               @Autowired UserWithRolesRepository userWithRoles_Repository) {
    userAdminRepository = userAdmin_Repository;
    userWithRolesRepository = userWithRoles_Repository;
    List<UserAdmin> userAdminList = List.of(
        UserAdmin.userAdminBuilder()
            .id(1L)
            .firstName("Peter")
            .lastName("Parker")
            .phoneNumber("12345678")
            .email("peter_parker@admin.dk")
            .password("1234")
            .user("peter_parker_admin")
            .ownership(Ownership.builder()
                .id(1L)
                .name("ownership1")
                .abbreviation("own1")
                .build())
            .build(),
        UserAdmin.userAdminBuilder()
            .id(2L)
            .firstName("Jens")
            .lastName("Hansen")
            .phoneNumber("12345678")
            .email("Jens_Hansen@admin.dk")
            .password("12345")
            .user("Jens_Hansen_admin")
            .ownership(Ownership.builder()
                .id(2L)
                .name("ownership2")
                .abbreviation("own2")
                .build())
            .build(),
        UserAdmin.userAdminBuilder()
            .id(3L)
            .firstName("Kasper")
            .lastName("Olsen")
            .phoneNumber("12345678")
            .email("Kasper_Olsen@admin.dk")
            .password("123456")
            .user("Kasper_Olsen_admin")
            .ownership(Ownership.builder()
                .id(3L)
                .name("ownership3")
                .abbreviation("own3")
                .build())
            .build(),
        UserAdmin.userAdminBuilder()
            .id(4L)
            .firstName("Marie")
            .lastName("Hedegaard")
            .phoneNumber("12345678")
            .email("Marie_Hedegaard@admin.dk")
            .password("1234567")
            .user("Marie_Hedegaard_admin")
            .ownership(Ownership.builder()
                .id(4L)
                .name("ownership4")
                .abbreviation("own4")
                .build())
            .build()
    );
    userAdminRepository.saveAll(userAdminList);
  }

  @BeforeEach
  public void setUserAdminService() {
    userAdminService = new UserAdminService(userAdminRepository, userWithRolesRepository);
  }

  @Test
  void getAllUserAdmin() {
    List<UserAdminResponse> userAdminResponseList = userAdminService.getAllUserAdmin();
    assertEquals(4, userAdminResponseList.size());
  }

  @Test
  void getUserAdminById() {
    UserAdminResponse userAdminResponse = userAdminService.getUserAdminById(1L);
    assertEquals("Peter", userAdminResponse.getFirstName());
  }

  @Test
  void addUserAdmin() {
    UserAdmin userAdmin = UserAdmin.userAdminBuilder()
        .id(5L)
        .firstName("Mohammad")
        .lastName("Kaspersky")
        .phoneNumber("1212122")
        .email("Mohammad_Kaspersky@admin.dk")
        .password("pw")
        .user("Mohammad_Kaspersky_admin")
        .ownership(Ownership.builder()
            .id(5L)
            .name("ownership5")
            .abbreviation("own5")
            .build())
        .build();
    UserAdminRequest userAdminRequest = new UserAdminRequest(userAdmin);
    userAdminService.addUserAdmin(userAdminRequest);
    assertEquals(5, userAdminRepository.count());





  }

  @Test
  void deleteUserAdmin() {
    userAdminService.deleteUserAdmin(1L);
    assertEquals(3, userAdminRepository.count());
  }

  @Test
  void updateUserAdmin() {
    UserAdmin foundUserAdmin = userAdminRepository.findById(1L).get();
    assertEquals("Peter", foundUserAdmin.getFirstName());
    foundUserAdmin.setFirstName("Malthe");
    UserAdminRequest userAdminRequest = new UserAdminRequest(foundUserAdmin);
    userAdminService.updateUserAdmin(userAdminRequest);
    assertEquals("Malthe", userAdminRepository.findById(1L).get().getFirstName());
  }
}