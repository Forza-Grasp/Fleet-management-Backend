package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class LcdvCodesService {
    private final LcdvCodeRepository lcdvCodeRepository;

    public LcdvCodesService(LcdvCodeRepository lcdvCodeRepository) {
        this.lcdvCodeRepository = lcdvCodeRepository;
    }

    public LcdvCodeResponse addLcdvCode(){

    }
}
