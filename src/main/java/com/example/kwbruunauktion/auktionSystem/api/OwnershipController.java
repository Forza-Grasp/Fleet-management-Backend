package com.example.kwbruunauktion.auktionSystem.api;

import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRepsonse;
import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRequest;
import com.example.kwbruunauktion.auktionSystem.service.OwnershipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PutMapping(path = "/{id}")
    ResponseEntity<Boolean> editOwnership(@RequestBody OwnershipRequest ownershipRequest, @PathVariable Long id){
        ownershipService.editOwnership(ownershipRequest, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    OwnershipRepsonse addOwnership(@RequestBody OwnershipRequest body) {
        return ownershipService.addOwnership(body);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteOwnership(@PathVariable Long id) {
        ownershipService.deleteOwnership(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
