package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.SpecificDamage;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserEconomy;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.DamageMatrixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.SpecificDamageRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserAdminRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserEconomyRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserLeaserRepository;
import com.example.kwbruunauktion.security.repository.UserWithRolesRepository;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class setupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    UserAdminRepository userAdminRepository;
    UserEconomyRepository userEconomyRepository;
    UserLeaserRepository userLeaserRepository;
    UserBuyerRepository userBuyerRepository;
    SpecificCarModelRepository specificCarModelRepository;
    DamageMatrixRepository damageMatrixRepository;
    SpecificDamageRepository specificDamageRepository;


  public setupDevUsers(UserWithRolesRepository userWithRolesRepository, UserAdminRepository userAdminRepository,
                       UserEconomyRepository userEconomyRepository, UserLeaserRepository userLeaserRepository,
                       UserBuyerRepository userBuyerRepository, DamageMatrixRepository damageMatrixRepository,
                       SpecificDamageRepository specificDamageRepository) {
    this.userWithRolesRepository = userWithRolesRepository;
    this.userAdminRepository = userAdminRepository;
    this.userEconomyRepository = userEconomyRepository;
    this.userLeaserRepository = userLeaserRepository;
    this.userBuyerRepository = userBuyerRepository;
    this.damageMatrixRepository = damageMatrixRepository;
    this.specificDamageRepository = specificDamageRepository;
  }

    @SneakyThrows
  @Override
  public void run(ApplicationArguments args) {
    /*
        UserAdmin admin1 = UserAdmin.userAdminBuilder()
        .user("admin1")
        .password("admin1")
        .email("admin@one.dk")
        .firstName("Jens")
        .lastName("Jensen")
        .phoneNumber("12345678")
        .build();

    userAdminRepository.save(admin1);

    UserBuyer buyer1 = UserBuyer.userBuyerBuilder()
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
        .build();
    userBuyerRepository.save(buyer1);



    UserLeaser leaser1 = UserLeaser.userLeaserBuilder()
        .user("leaser1")
        .password("buyer")
        .email("leaser@one.dk")
        .firstName("Simon")
        .lastName("Igild")
        .phoneNumber("12345678")
        .city("Aarhus")
        .companyEuVatNumber("DK24021422")
        .zipCode("8000")
        .addressLine1("Vej 1")
        .addressLine2("Vej 2")
        .companyName("Mo's Cars")
        .country("Denmark")

        .build();
    userLeaserRepository.save(leaser1);

    UserEconomy economy1 = UserEconomy.userEconomyBuilder()
        .user("economy1")
        .password("economy1")
        .email("economy1@one.dk")
        .firstName("Malthe")
        .lastName("Holm")
        .phoneNumber("12345678")
        .build();

    userEconomyRepository.save(economy1);

    //DamageMatrix & SpecificDamage

    DamageMatrix damageMatrix1 = DamageMatrix.builder()
        .userWithRoles(buyer1)
        .valuta("DKK")
        .build();
    damageMatrixRepository.save(damageMatrix1);
    DamageMatrix damageMatrix2 = DamageMatrix.builder()
        .userWithRoles(buyer1)
        .valuta("DKK")
        .build();
    damageMatrixRepository.save(damageMatrix2);
    List<SpecificDamage> damages = List.of(
        SpecificDamage.builder()
            .damage("Bumper")
            .price(1000)
            .damageMatrix(damageMatrix1)
            .build(),
        SpecificDamage.builder()
            .damage("Front")
            .price(2000)
            .damageMatrix(damageMatrix1)
            .build(),
        SpecificDamage.builder()
            .damage("Front")
            .price(1500)
            .damageMatrix(damageMatrix2)
            .build(),
        SpecificDamage.builder()
            .damage("back")
            .price(3000)
            .damageMatrix(damageMatrix2)
            .build()
    );
    specificDamageRepository.saveAll(damages);

     */


  }

}