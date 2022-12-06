package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignCarRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignCarService {

    private final CampaignRepository campaignRepository;

    private final CampaignCarRepository campaignCarRepository;

    public CampaignCarService(CampaignRepository campaignRepository, CampaignCarRepository campaignCarRepository) {
        this.campaignRepository = campaignRepository;
        this.campaignCarRepository = campaignCarRepository;
    }

    public List<CampaignCarResponse> getAllCampaignCars() {
        return campaignCarRepository.findAll().stream().map(CampaignCarResponse::new).collect(Collectors.toList());
    }

    public List<CampaignCarResponse> getAllCampaignCars(Pageable p) {
        Page<CampaignCar> listOfCampaignCars = campaignCarRepository.findAll(p);
        return listOfCampaignCars.stream().map(CampaignCarResponse::new).collect(Collectors.toList());
    }

    public CampaignCarResponse getCampaignCarById(Long id) {
        return new CampaignCarResponse(campaignCarRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignCar with that ID not found")));
    }

    public CampaignCarResponse addCampaignCar(CampaignCarRequest campaignCarRequest) {
        if (campaignRepository.existsById(campaignCarRequest.getCampaignId())) {
            Campaign newCampaign = campaignRepository.findById(campaignCarRequest.getCampaignId())
                    .orElseThrow(() -> new RuntimeException("Campaign with that ID not found"));
            CampaignCar newCampaignCar = CampaignCar.builder()
                    .brand(campaignCarRequest.getBrand())
                    .model(campaignCarRequest.getModel())
                    .modelText(campaignCarRequest.getModelText())
                    .description(campaignCarRequest.getDescription())
                    .exceptedRegistrationFromDate(campaignCarRequest.getExceptedRegistrationFromDate())
                    .exceptedRegistrationToDate(campaignCarRequest.getExceptedRegistrationToDate())
                    .monthsRegistered(campaignCarRequest.getMonthsRegistered())
                    .earliestExceptedReturnDate(campaignCarRequest.getEarliestExceptedReturnDate())
                    .latestExceptedReturnDate(campaignCarRequest.getLatestExceptedReturnDate())
                    .mileage(campaignCarRequest.getMileage())
                    .depositPerCar(campaignCarRequest.getDepositPerCar())
                    .damageAndMileage(campaignCarRequest.getDamageAndMileage())
                    .supplyingConditions(campaignCarRequest.getSupplyingConditions())
                    .campaignPictureOne(campaignCarRequest.getCampaignPictureOne())
                    .campaign(newCampaign)
                    .build();
            campaignCarRepository.save(newCampaignCar);
            return new CampaignCarResponse(newCampaignCar);
        }
        CampaignCar newCampaignCar = CampaignCar.builder()
                .brand(campaignCarRequest.getBrand())
                .model(campaignCarRequest.getModel())
                .modelText(campaignCarRequest.getModelText())
                .description(campaignCarRequest.getDescription())
                .exceptedRegistrationFromDate(campaignCarRequest.getExceptedRegistrationFromDate())
                .exceptedRegistrationToDate(campaignCarRequest.getExceptedRegistrationToDate())
                .monthsRegistered(campaignCarRequest.getMonthsRegistered())
                .earliestExceptedReturnDate(campaignCarRequest.getEarliestExceptedReturnDate())
                .latestExceptedReturnDate(campaignCarRequest.getLatestExceptedReturnDate())
                .mileage(campaignCarRequest.getMileage())
                .depositPerCar(campaignCarRequest.getDepositPerCar())
                .damageAndMileage(campaignCarRequest.getDamageAndMileage())
                .supplyingConditions(campaignCarRequest.getSupplyingConditions())
                .campaignPictureOne(campaignCarRequest.getCampaignPictureOne())
                .build();
        campaignCarRepository.save(newCampaignCar);
        return new CampaignCarResponse(newCampaignCar);
    }

    public void deleteCampaignCar(@PathVariable Long id) {
        campaignCarRepository.deleteById(id);
    }

    public CampaignCarResponse editCampaignCar(CampaignCarRequest campaignCarRequest) {
        CampaignCar campaignCar = campaignCarRepository.findById(campaignCarRequest.getId()).orElseThrow(() -> new RuntimeException("CampaignCar with that ID not found"));
        campaignCar.setBrand(campaignCarRequest.getBrand());
        campaignCar.setModel(campaignCarRequest.getModel());
        campaignCar.setModelText(campaignCarRequest.getModelText());
        campaignCar.setDescription(campaignCarRequest.getDescription());
        campaignCar.setExceptedRegistrationFromDate(campaignCarRequest.getExceptedRegistrationFromDate());
        campaignCar.setExceptedRegistrationToDate(campaignCarRequest.getExceptedRegistrationToDate());
        campaignCar.setMonthsRegistered(campaignCarRequest.getMonthsRegistered());
        campaignCar.setEarliestExceptedReturnDate(campaignCarRequest.getEarliestExceptedReturnDate());
        campaignCar.setLatestExceptedReturnDate(campaignCarRequest.getLatestExceptedReturnDate());
        campaignCar.setMileage(campaignCarRequest.getMileage());
        campaignCar.setDepositPerCar(campaignCarRequest.getDepositPerCar());
        campaignCar.setDamageAndMileage(campaignCarRequest.getDamageAndMileage());
        campaignCar.setSupplyingConditions(campaignCarRequest.getSupplyingConditions());
        campaignCar.setCampaignPictureOne(campaignCarRequest.getCampaignPictureOne());
        return new CampaignCarResponse(campaignCar);
    }

}

