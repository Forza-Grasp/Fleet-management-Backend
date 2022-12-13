package com.example.kwbruunauktion.auktionSystem.api.campaign;


import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarResponse;
import com.example.kwbruunauktion.auktionSystem.service.campaign.CampaignCarService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/campaign/car")
public class CampaignCarController {
  private final CampaignCarService campaignCarService;

  public CampaignCarController(CampaignCarService campaignCarService) {
    this.campaignCarService = campaignCarService;
  }

  @GetMapping("/all")
  public List<CampaignCarResponse> getAllCampaignCars() {
    return campaignCarService.getAllCampaignCars();
  }

  @GetMapping
  public List<CampaignCarResponse> getAllCampaignCars(Pageable p) {
    return campaignCarService.getAllCampaignCars(p);
  }

  @GetMapping("/{id}")
  public CampaignCarResponse getCampaignCarById(@PathVariable Long id) {
    return campaignCarService.getCampaignCarById(id);
  }

  @GetMapping("/campaign/{id}")
  public CampaignCarResponse getCampaignCarByCampaignId(@PathVariable Long id) {
    return campaignCarService.getCampaignCarByCampaignId(id);
  }

  @DeleteMapping("/{id}")
  public void deleteCampaignCarById(@PathVariable Long id) {
    campaignCarService.deleteCampaignCar(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public CampaignCarResponse addCampaignCar(@RequestBody CampaignCarRequest campaignCarRequest) {
    return campaignCarService.addCampaignCar(campaignCarRequest);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public CampaignCarResponse updateCampaignCar(@RequestBody CampaignCarRequest campaignCarRequest) {
    return campaignCarService.editCampaignCar(campaignCarRequest);
  }


}
