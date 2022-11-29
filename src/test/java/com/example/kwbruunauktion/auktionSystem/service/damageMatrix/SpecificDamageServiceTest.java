package com.example.kwbruunauktion.auktionSystem.service.damageMatrix;


import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request.SpecificDamageRequest;
import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.SpecificDamageResponse;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.SpecificDamage;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.DamageMatrixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.SpecificDamageRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
class SpecificDamageServiceTest {

  public SpecificDamageService specificDamageService;

  public static DamageMatrixRepository damageMatrixRepository;
  public static UserBuyerRepository userBuyerRepository;
  public static SpecificDamageRepository specificDamageRepository;

  @BeforeAll
  public static void setupData(@Autowired DamageMatrixRepository damageMatrix_Repository,
                               @Autowired UserBuyerRepository userBuyer_Repository,
                               @Autowired SpecificDamageRepository specificDamage_Repository) {
    damageMatrixRepository = damageMatrix_Repository;
    userBuyerRepository = userBuyer_Repository;
    specificDamageRepository = specificDamage_Repository;
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
        .id(2L)
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
    DamageMatrix damageMatrix1 = DamageMatrix.builder()
        .id(1L)
        .valuta("DKK")
        .userWithRoles(userBuyer1)
        .build();
    DamageMatrix damageMatrix2 = DamageMatrix.builder()
        .id(2L)
        .userWithRoles(userBuyer2)
        .valuta("EUR")
        .build();
    DamageMatrix damageMatrix3 = DamageMatrix.builder()
        .id(3L)
        .userWithRoles(userBuyer3)
        .valuta("USD")
        .build();
    List<DamageMatrix> damageMatrixList = List.of(
        damageMatrix1,
        damageMatrix2,
        damageMatrix3);
    damageMatrixRepository.saveAll(damageMatrixList);

    List<SpecificDamage> specificDamageList = List.of(
        SpecificDamage.builder()
            .id(1L)
            .damageMatrix(damageMatrix1)
            .damage("damage1")
            .price(1000)
            .build(),
        SpecificDamage.builder()
            .id(2L)
            .damageMatrix(damageMatrix1)
            .damage("damage2")
            .price(2000)
            .build(),
        SpecificDamage.builder()
            .id(3L)
            .damageMatrix(damageMatrix2)
            .damage("damage3")
            .price(3000)
            .build(),
        SpecificDamage.builder()
            .id(4L)
            .damageMatrix(damageMatrix2)
            .damage("damage4")
            .price(4000)
            .build(),
        SpecificDamage.builder()
            .id(5L)
            .damageMatrix(damageMatrix3)
            .damage("damage5")
            .price(5000)
            .build(),
        SpecificDamage.builder()
            .id(6L)
            .damageMatrix(damageMatrix3)
            .damage("damage6")
            .price(6000)
            .build()
    );
    specificDamageRepository.saveAll(specificDamageList);

  }

  @BeforeEach
  public void setService() {
    specificDamageService = new SpecificDamageService(specificDamageRepository, damageMatrixRepository);
  }


  @Test
  void getAllSpecificDamages() {
    List<SpecificDamageResponse> specificDamageList = specificDamageService.getAllSpecificDamages();
    assertEquals(6, specificDamageRepository.count());
  }

  @Test
  void getSpecificDamageById() {
    SpecificDamageResponse specificDamage = specificDamageService.getSpecificDamageById(1L);
    assertEquals("damage1", specificDamage.getDamages());
  }

  @Test
  void getAllSpecificDamagesByDamageMatrixId() {
    List<SpecificDamageResponse> specificDamageList = specificDamageService.getAllSpecificDamagesByDamageMatrixId(1L);
    assertEquals(2, specificDamageList.size());
    assertNotEquals(6, specificDamageList.size());
  }

  @Test
  void addSpecificDamage() {
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
    damageMatrixRepository.save(newDamageMatrix4);


    SpecificDamage damage1 = SpecificDamage.builder()
        .id(7L)
        .damageMatrix(newDamageMatrix4)
        .damage("damage1")
        .price(7000)
        .build();
    SpecificDamage damage2 = SpecificDamage.builder()
        .id(8L)
        .damageMatrix(newDamageMatrix4)
        .damage("damage2")
        .price(8000)
        .build();
    SpecificDamageRequest request1 = new SpecificDamageRequest(damage1);
    SpecificDamageRequest request2 = new SpecificDamageRequest(damage2);
    specificDamageService.addSpecificDamage(request1);
    specificDamageService.addSpecificDamage(request2);
    assertEquals(8, specificDamageRepository.count());
  }

  @Test
  void deleteSpecificDamage() {
    specificDamageService.getAllSpecificDamages();
    assertEquals(6, specificDamageRepository.count());
    specificDamageService.deleteSpecificDamage(1L);
    specificDamageService.deleteSpecificDamage(2L);
    assertEquals(4, specificDamageRepository.count());
  }

  @Test
  void updateSpecificDamage() {
    SpecificDamage specificDamage = specificDamageRepository.findById(1L).get();
    assertEquals("damage1", specificDamage.getDamage());
    specificDamage.setDamage("damage7");
    SpecificDamageRequest request = new SpecificDamageRequest(specificDamage);
    specificDamageService.updateSpecificDamage(request);
    SpecificDamageResponse specificDamage2 = specificDamageService.getSpecificDamageById(1L);
    assertEquals("damage7", specificDamage2.getDamages());
    assertNotEquals("damage1", specificDamage2.getDamages());
  }
}