package com.example.kwbruunauktion.auktionSystem.api.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.service.campaign.LcdvCodesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/lcdv-codes")
public class LcdvCodesController {

    private final LcdvCodesService lcdvCodesService;

    public LcdvCodesController(LcdvCodesService lcdvCodesService) {
        this.lcdvCodesService = lcdvCodesService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LcdvCodeResponse addLcdvCode(LcdvCodeRequest lcdvCodeRequest){
        lcdvCodesService.addLcdvCode(lcdvCodeRequest);
    }
}
