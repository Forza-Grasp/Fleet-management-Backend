package com.example.kwbruunauktion.auktionSystem.api.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.service.campaign.LcdvCodeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/lcdv-codes")
public class LcdvCodeController {

    private final LcdvCodeService lcdvCodeService;

    public LcdvCodeController(LcdvCodeService lcdvCodeService) {
        this.lcdvCodeService = lcdvCodeService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LcdvCodeResponse addLcdvCode(@RequestBody LcdvCodeRequest lcdvCodeRequest){
        return lcdvCodeService.addLcdvCode(lcdvCodeRequest);
    }

    @GetMapping("/all")
    public List<LcdvCodeResponse> getAllLcdvCodes(){
        return lcdvCodeService.getAllLcdvCodes();
    }

    @GetMapping()
    public List<LcdvCodeResponse> getAllLcdvCodes(Pageable p){
        return lcdvCodeService.getAllLcdvCodes(p);
    }

    @GetMapping("/{id}")
    public LcdvCodeResponse getLcdvCodeById(@PathVariable Long id){
        return lcdvCodeService.getLcdvCodeById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LcdvCodeResponse updateLcdvCode(@RequestBody LcdvCodeRequest lcdvCodeRequest){
        return lcdvCodeService.editLcdvCode(lcdvCodeRequest);
    }

    @DeleteMapping("/{id}")
    public LcdvCodeResponse deleteLcdvCodeById(@PathVariable Long id){
        return lcdvCodeService.deleteLcdvCodeById(id);
    }
}
