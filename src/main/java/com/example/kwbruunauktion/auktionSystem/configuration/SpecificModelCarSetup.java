package com.example.kwbruunauktion.auktionSystem.configuration;


import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.service.SpecificCarModelService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;


@Controller
public class SpecificModelCarSetup implements ApplicationRunner {

    SpecificCarModelRepository specificCarModelRepository;
    SpecificCarModelService specificCarModelService;

    public SpecificModelCarSetup(SpecificCarModelRepository specificCarModelRepository, SpecificCarModelService specificCarModelService) {
        this.specificCarModelRepository = specificCarModelRepository;
        this.specificCarModelService = specificCarModelService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        SpecificCarModel specificCarModel1 = SpecificCarModel.builder()
                .brand("Ford Fiesta")
                .model("S10")
                .modelYear("2001")
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
