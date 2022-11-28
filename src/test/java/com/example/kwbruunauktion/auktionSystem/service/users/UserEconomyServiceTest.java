package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserAdminRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserEconomyRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserEconomyResponse;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserEconomy;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserAdminRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserEconomyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserEconomyServiceTest {

  public UserEconomyService userEconomyService;

  public static UserEconomyRepository userEconomyRepository;

  @BeforeAll
  public static void setupData(@Autowired UserEconomyRepository userEconomy_Repository) {
    userEconomyRepository = userEconomy_Repository;
    List<UserEconomy> userEconomyList = List.of(
        UserEconomy.userEconomyBuilder()
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
        UserEconomy.userEconomyBuilder()
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
        UserEconomy.userEconomyBuilder()
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
        UserEconomy.userEconomyBuilder()
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
    userEconomyRepository.saveAll(userEconomyList);
  }

  @BeforeEach
  public void setUserEconomyService() {
    userEconomyService = new UserEconomyService(userEconomyRepository);
  }

  @Test
  void getAllUserEconomy() {
    List<UserEconomyResponse> userEconomyResponseList = userEconomyService.getAllUserEconomy();
    assertEquals(4, userEconomyResponseList.size());
  }

  @Test
  void getUserEconomyById() {
    UserEconomyResponse userEconomyResponse = userEconomyService.getUserEconomyById(1L);
    assertEquals("Peter", userEconomyResponse.getFirstName());
  }

  @Test
  void deleteUserEconomy() {
    userEconomyService.deleteUserEconomy(1L);
    assertEquals(3, userEconomyRepository.count());
  }

  @Test
  void addUserEconomy() {
    UserEconomy newUser = UserEconomy.userEconomyBuilder()
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
    UserEconomyRequest newRequest = new UserEconomyRequest(newUser);
    userEconomyService.addUserEconomy(newRequest);
    assertEquals(5, userEconomyRepository.count());
  }

  @Test
  void updateUserEconomy() {
    UserEconomy foundUserEconomy = userEconomyRepository.findById(1L).get();
    assertEquals("Peter", foundUserEconomy.getFirstName());
    foundUserEconomy.setFirstName("MoJens");
    UserEconomyRequest userEconomyRequest = new UserEconomyRequest(foundUserEconomy);
    userEconomyService.updateUserEconomy(userEconomyRequest);
    assertEquals("MoJens", userEconomyRepository.findById(1L).get().getFirstName());

  }
}