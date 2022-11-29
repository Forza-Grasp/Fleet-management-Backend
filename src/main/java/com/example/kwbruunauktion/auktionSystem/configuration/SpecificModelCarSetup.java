package com.example.kwbruunauktion.auktionSystem.configuration;


import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserLeaserRepository;
import com.example.kwbruunauktion.auktionSystem.service.SpecificCarModelService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SpecificModelCarSetup implements ApplicationRunner {

    SpecificCarModelRepository specificCarModelRepository;
    SpecificCarModelService specificCarModelService;
    UserLeaserRepository userLeaserRepository;
    UserBuyerRepository userBuyerRepository;

    public SpecificModelCarSetup(SpecificCarModelRepository specificCarModelRepository, SpecificCarModelService specificCarModelService,
                                 UserLeaserRepository userLeaserRepository,
                                 UserBuyerRepository userBuyerRepository) {
        this.specificCarModelRepository = specificCarModelRepository;
        this.specificCarModelService = specificCarModelService;
        this.userLeaserRepository = userLeaserRepository;
        this.userBuyerRepository = userBuyerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

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
        List<UserBuyer> userBuyerList = new ArrayList<>();
        userBuyerList.add(buyer1);

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
        List<UserLeaser> listOfLeasers = new ArrayList<>();
        listOfLeasers.add(leaser1);

        SpecificCarModel specificCarModel1 = SpecificCarModel.builder()
                .brand("Ford Fiesta")
                .model("S10")
                .modelYear("2001")
                .userLeaser(listOfLeasers)
                .userBuyer(userBuyerList)
                .build();

        SpecificCarModel specificCarModel2 = SpecificCarModel.builder()
                .brand("Mazda")
                .model("F5")
                .modelYear("2008")
                .build();

        specificCarModelRepository.save(specificCarModel1);
        specificCarModelRepository.save(specificCarModel2);

    }
}
