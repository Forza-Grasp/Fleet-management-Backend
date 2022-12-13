package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LcdvCodeService {
    private final LcdvCodeRepository lcdvCodeRepository;

    public LcdvCodeService(LcdvCodeRepository lcdvCodeRepository) {
        this.lcdvCodeRepository = lcdvCodeRepository;
    }

    public LcdvCodeResponse addLcdvCode(LcdvCodeRequest lcdvCodeRequest){
        LcdvCode lcdvCodeToSave = LcdvCode.builder()
                .lcdvCode(lcdvCodeRequest.getLcdvCode())
                .build();

        lcdvCodeRepository.save(lcdvCodeToSave);
        return new LcdvCodeResponse(lcdvCodeToSave);
    }

    public List<LcdvCodeResponse> getAllLcdvCodes(){
        return lcdvCodeRepository.findAll().stream().map(LcdvCodeResponse::new).collect(Collectors.toList());
    }

    public List<LcdvCodeResponse> getAllLcdvCodes(Pageable p){
        Page<LcdvCode> listOfLcdvCodes = lcdvCodeRepository.findAll(p);
        return listOfLcdvCodes.stream().map(LcdvCodeResponse::new).collect(Collectors.toList());
    }

    public LcdvCodeResponse getLcdvCodeById(Long id){
        return lcdvCodeRepository.findById(id).map(LcdvCodeResponse::new)
                .orElseThrow(() -> new RuntimeException("LcdvCode with id: "+id+" does not exist"));
    }

    public LcdvCodeResponse editLcdvCode(LcdvCodeRequest lcdvCodeRequest){
        LcdvCode lcdvCodeToEdit = lcdvCodeRepository.findById(lcdvCodeRequest.getId())
                .orElseThrow(() -> new RuntimeException("LcdvCode with id: "+lcdvCodeRequest.getId()+" does not exist"));
        lcdvCodeToEdit.setLcdvCode(lcdvCodeRequest.getLcdvCode());
        lcdvCodeRepository.save(lcdvCodeToEdit);
        return new LcdvCodeResponse(lcdvCodeToEdit);
    }

    public LcdvCodeResponse deleteLcdvCodeById(Long id){
        LcdvCode lcdvCodeToDelete = lcdvCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LcdvCode with id: "+id+" does not exist"));
        lcdvCodeRepository.delete(lcdvCodeToDelete);
        return new LcdvCodeResponse(lcdvCodeToDelete);
    }

}
