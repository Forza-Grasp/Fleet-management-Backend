package com.example.kwbruunauktion.auktionSystem.configuration;

import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

@Controller
public class ConfigurationColormix implements ApplicationRunner {

    ColorMixRepository colorMixRepository;
    ColorTypesRepository colorTypesRepository;

    public ConfigurationColormix(ColorMixRepository colorMixRepository, ColorTypesRepository colorTypesRepository){
        this.colorMixRepository = colorMixRepository;
        this.colorTypesRepository = colorTypesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("test");
        ColorTypes colorType1 = ColorTypes.builder()
                .type("Metallic")
                .build();
        ColorTypes colorType2 = ColorTypes.builder()
                .type("Mat")
                .build();
        ColorTypes colorType3 = ColorTypes.builder()
                .type("Shiny")
                .build();
        colorTypesRepository.save(colorType1);
        colorTypesRepository.save(colorType2);
        colorTypesRepository.save(colorType3);

        ColorMix colorMix = ColorMix.builder()
                .colorType(colorType1)
                .brandColorMix(null)
                .colorName("Silver")
                .colorCode("DFSKL")
                .build();
        colorMixRepository.save(colorMix);


    }

}
