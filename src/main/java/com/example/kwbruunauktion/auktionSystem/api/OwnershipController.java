package com.example.kwbruunauktion.auktionSystem.api;

import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRepsonse;
import com.example.kwbruunauktion.auktionSystem.service.OwnershipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("/api/ownership")
public class OwnershipController {
    private final OwnershipService ownershipService;

    public OwnershipController(OwnershipService ownershipService){
        this.ownershipService = ownershipService;
    }


     public List<OwnershipRepsonse> getOwnerships(){
      return ownershipService.getOwnerships();
    }
}
