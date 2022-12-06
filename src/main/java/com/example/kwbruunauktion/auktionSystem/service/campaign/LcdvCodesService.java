package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LcdvCodesService {
    private final LcdvCodeRepository lcdvCodeRepository;
    private final CampaignRepository campaignRepository;

    public LcdvCodesService(LcdvCodeRepository lcdvCodeRepository,CampaignRepository campaignRepository) {
        this.lcdvCodeRepository = lcdvCodeRepository;
        this.campaignRepository = campaignRepository;
    }

    public LcdvCodeResponse addLcdvCode(LcdvCodeRequest lcdvCodeRequest){
        if (lcdvCodeRepository.existsById(lcdvCodeRequest.getId())){
            throw new RuntimeException("LcdvCode with id: "+lcdvCodeRequest.getId()+" already exists");
        }
        List<Campaign> attachedCampaigns = campaignRepository.findAllById(lcdvCodeRequest.getCampaignResponseIds());
        LcdvCode lcdvCodeToSave = LcdvCode.builder()
                .lcdvCode(lcdvCodeRequest.getLcdvCode())
                .campaign(attachedCampaigns)
                .build();

        lcdvCodeRepository.save(lcdvCodeToSave);
        return new LcdvCodeResponse(lcdvCodeToSave);
    }

    public List<LcdvCodeResponse> getLcdvCodes(){
        return lcdvCodeRepository.findAll().stream().map(LcdvCodeResponse::new).collect(Collectors.toList());
    }

    public LcdvCodeResponse getLcdvCodeById(Long id){
        return lcdvCodeRepository.findById(id).map(LcdvCodeResponse::new)
                .orElseThrow(() -> new RuntimeException("LcdvCode with id: "+id+" does not exist"));
    }

    public LcdvCodeResponse deleteLcdvCodeById(Long id){
        LcdvCodeResponse lcdvCodeToDelete = getLcdvCodeById(id);
        lcdvCodeRepository.deleteById(id);
        return lcdvCodeToDelete;
    }
}
