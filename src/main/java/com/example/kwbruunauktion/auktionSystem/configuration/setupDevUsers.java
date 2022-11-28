package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.UserAdminRepository;
import com.example.kwbruunauktion.auktionSystem.repository.UserBuyerRepository;
import com.example.kwbruunauktion.auktionSystem.repository.UserEconomyRepository;
import com.example.kwbruunauktion.auktionSystem.repository.UserLeaserRepository;
import com.example.kwbruunauktion.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class setupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    UserAdminRepository userAdminRepository;
    UserEconomyRepository userEconomyRepository;
    UserLeaserRepository userLeaserRepository;
    UserBuyerRepository userBuyerRepository;


    public setupDevUsers(UserWithRolesRepository userWithRolesRepository, UserAdminRepository userAdminRepository, UserEconomyRepository userEconomyRepository, UserLeaserRepository userLeaserRepository, UserBuyerRepository userBuyerRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.userAdminRepository = userAdminRepository;
        this.userEconomyRepository = userEconomyRepository;
        this.userLeaserRepository = userLeaserRepository;
        this.userBuyerRepository = userBuyerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        UserAdmin admin1 = UserAdmin.userAdminBuilder()
                .user("admin1")
                .password("admin1")
                .email("admin@one.dk")
                .firstName("Jens")
                .lastName("Jensen")
                .phoneNumber("12345678")
                .build();

        userAdminRepository.save(admin1);

        UserBuyer buyer = UserBuyer.userBuyerBuilder()
                .user("buyer")
                .password("buyer")
                .email("buyer@one.dk")
                .firstName("Mo")
                .lastName("Adel")
                .phoneNumber("12345678")
                .city("Aarhus")
                .zipCode("8000")
                .addressLine1("Vej 1")
                .addressLine2("Vej 2")
                .companyName("Mo's Cars")
                .build();
        userBuyerRepository.save(buyer);




    }
}
