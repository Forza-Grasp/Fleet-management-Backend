package com.example.kwbruunauktion.auktionSystem.service.damageMatrix;

import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request.DamageMatrixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.DamageMatrixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.DamageMatrixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DamageMatrixServiceTest {

  public DamageMatrixService damageMatrixService;

  public static DamageMatrixRepository damageMatrixRepository;
  public static UserBuyerRepository userBuyerRepository;

  @BeforeAll
  public static void setupData(@Autowired DamageMatrixRepository damageMatrix_Repository,
                               @Autowired UserBuyerRepository userBuyer_Repository) {
    damageMatrixRepository = damageMatrix_Repository;
    userBuyerRepository = userBuyer_Repository;
    UserBuyer userBuyer1 = userBuyerRepository.save(UserBuyer.userBuyerBuilder()
        .id(1L)
        .user("buyer1")
        .password("buyer")
        .email("buyer@one.dk")
        .firstName("Mo")
        .lastName("Adel")
        .phoneNumber("12345678")
        .city("Aarhus")
        .companyEuVatNumber("DK29233133")
        .zipCode("8000")
        .addressLine1("Vej 1")
        .addressLine2("Vej 2")
        .companyName("Mo's Cars")
        .companyEuVatNumber("12345678")
        .country("Denmark")
        .build());
    UserBuyer userBuyer2 = userBuyerRepository.save(UserBuyer.userBuyerBuilder()
        .id(1L)
        .user("buyer2")
        .password("buyer")
        .email("buyer@two.dk")
        .firstName("Mo")
        .lastName("Adel")
        .phoneNumber("12345678")
        .city("Aarhus")
        .companyEuVatNumber("DK29233133")
        .zipCode("8000")
        .addressLine1("Vej 1")
        .addressLine2("Vej 2")
        .companyName("Mo's Cars")
        .companyEuVatNumber("12345678")
        .country("Denmark")
        .build());
    UserBuyer userBuyer3 = userBuyerRepository.save(UserBuyer.userBuyerBuilder()
        .id(3L)
        .user("buyer3")
        .password("buyer")
        .email("buyer@three.dk")
        .firstName("Mo")
        .lastName("Adel")
        .phoneNumber("12345678")
        .city("Aarhus")
        .companyEuVatNumber("DK29233133")
        .zipCode("8000")
        .addressLine1("Vej 1")
        .addressLine2("Vej 2")
        .companyName("Mo's Cars")
        .companyEuVatNumber("12345678")
        .country("Denmark")
        .build());
    List<DamageMatrix> damageMatrixList = List.of(
        DamageMatrix.builder()
            .id(1L)
            .valuta("DKK")
            .userWithRoles(userBuyer1)
            .build(),
        DamageMatrix.builder()
            .id(2L)
            .userWithRoles(userBuyer2)
            .valuta("EUR")
            .build(),
        DamageMatrix.builder()
            .id(3L)
            .userWithRoles(userBuyer3)
            .valuta("USD")
            .build()
    );
    damageMatrixRepository.saveAll(damageMatrixList);
  }

  @BeforeEach
  public void setDamageMatrixService() {
    damageMatrixService = new DamageMatrixService(damageMatrixRepository);
  }

  @Test
  void getAllDamageMatrix() {
    List<DamageMatrixResponse> damageMatrixResponseList = damageMatrixService.getAllDamageMatrix();
    assertEquals(3, damageMatrixResponseList.size());
  }

  @Test
  void getDamageMatrixById() {
    DamageMatrixResponse damageMatrixResponse = damageMatrixService.getDamageMatrixById(2L);
    assertEquals("EUR", damageMatrixResponse.getValuta());
  }

  @Test
  void addDamageMatrix() {
    UserBuyer userBuyer4 = userBuyerRepository.save(UserBuyer.userBuyerBuilder()
        .id(4L)
        .user("buyer4")
        .password("buyer")
        .email("buyer@four.dk")
        .firstName("Mo")
        .lastName("Adel")
        .phoneNumber("12345678")
        .city("Aarhus")
        .companyEuVatNumber("DK29233133")
        .zipCode("8000")
        .addressLine1("Vej 1")
        .addressLine2("Vej 2")
        .companyName("Mo's Cars")
        .companyEuVatNumber("12345678")
        .country("Denmark")
        .build());
    DamageMatrix newDamageMatrix4 = DamageMatrix.builder()
        .id(4L)
        .userWithRoles(userBuyer4)
        .valuta("KOR")
        .build();
    DamageMatrixRequest newDamageMatrixRequest = new DamageMatrixRequest(newDamageMatrix4);
    damageMatrixService.addDamageMatrix(newDamageMatrixRequest);
    assertEquals(4, damageMatrixRepository.count());
  }

  @Test
  void deleteDamageMatrix() {
    damageMatrixService.deleteDamageMatrix(3L);
    assertEquals(2, damageMatrixRepository.count());
  }

  @Test
  void updateDamageMatrix() {
    DamageMatrix damageMatrix = damageMatrixRepository.findById(2L).get();
    assertEquals("EUR", damageMatrix.getValuta());
    DamageMatrixRequest damageMatrixRequest = new DamageMatrixRequest(damageMatrix);
    damageMatrixRequest.setValuta("USD");
    damageMatrixService.updateDamageMatrix(damageMatrixRequest);
    DamageMatrixResponse damageMatrixResponse2 = damageMatrixService.getDamageMatrixById(2L);
    assertEquals("USD", damageMatrixResponse2.getValuta());
  }
}