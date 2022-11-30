package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.OwnershipRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.service.OwnershipService;
import com.example.kwbruunauktion.auktionSystem.service.SpecificCarModelService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

@Controller
public class OwnershipSetup implements ApplicationRunner {

    OwnershipRepository ownershipRepository;
    OwnershipService ownershipService;

    public OwnershipSetup(OwnershipRepository ownershipRepository, OwnershipService ownershipService) {
        this.ownershipRepository = ownershipRepository;
        this.ownershipService = ownershipService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Ownership ownership1 = Ownership.builder()
                .name("Ford Fiesta")
                .abbreviation("S10")
                .build();

        Ownership ownership2 = Ownership.builder()
                .name("Mazda")
                .abbreviation("F5")
                .build();

        ownershipRepository.save(ownership1);
        ownershipRepository.save(ownership2);
    }
}
