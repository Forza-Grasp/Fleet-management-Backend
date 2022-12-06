package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse;

import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class CampaignCarResponse {

    private Long id;

    private String brand;

    private String model;

    private String modelText;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate exceptedRegistrationFromDate;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate exceptedRegistrationToDate;

    private int monthsRegistered;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate earliestExceptedReturnDate;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate latestExceptedReturnDate;

    private String mileage;

    private String depositPerCar;

    private String damageAndMileage;

    private String supplyingConditions;

    private String campaignPictureOne;

    private Long campaignId;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated;





    public CampaignCarResponse(CampaignCar c){
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.modelText = c.getModelText();
        this.description = c.getDescription();
        this.exceptedRegistrationFromDate = c.getExceptedRegistrationFromDate();
        this.exceptedRegistrationToDate = c.getExceptedRegistrationToDate();
        this.monthsRegistered = c.getMonthsRegistered();
        this.earliestExceptedReturnDate = c.getEarliestExceptedReturnDate();
        this.latestExceptedReturnDate = c.getLatestExceptedReturnDate();
        this.mileage = c.getMileage();
        this.depositPerCar = c.getDepositPerCar();
        this.damageAndMileage = c.getDamageAndMileage();
        this.supplyingConditions = c.getSupplyingConditions();
        this.campaignPictureOne = c.getCampaignPictureOne();
        if (c.getCampaign() != null){
            this.campaignId = c.getCampaign().getId();
        }
        this.created = c.getCreated();
        this.updated = c.getUpdated();
    }




    }
