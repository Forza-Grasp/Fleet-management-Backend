package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRepsonse;
import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRequest;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelRequest;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.OwnershipRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnershipService {
    private final OwnershipRepository ownershipRepository;

    public OwnershipService(OwnershipRepository ownershipRepository) {
        this.ownershipRepository = ownershipRepository;
    }

    public List<OwnershipRepsonse> getOwnerships(){
        List<Ownership> ownerships = ownershipRepository.findAll();
        return ownerships.stream().map(o->new OwnershipRepsonse(o)).collect(Collectors.toList());
    }
    public OwnershipRepsonse addOwnership(OwnershipRequest ownershipRequest) {
        if(ownershipRepository.existsById(ownershipRequest.getId())) {
            throw new RuntimeException("Ownership with this ID already exist");
        }
        Ownership ownership = Ownership.builder()

                .name(ownershipRequest.getName())
                .abbreviation(ownershipRequest.getAbbreviation())
                .build();

        ownershipRepository.save(ownership);
        return new OwnershipRepsonse(ownership);
    }

    public void editOwnership(OwnershipRequest ownershipRequest, Long id) {
        Ownership ownership = ownershipRepository.findById(id).orElseThrow(() -> new RuntimeException("Ownership with this ID does not exist"));
        ownership.setId(ownershipRequest.getId());
        ownership.setName(ownershipRequest.getName());
        ownership.setAbbreviation(ownershipRequest.getAbbreviation());
        ownershipRepository.save(ownership);
    }

    public void deleteOwnership(@PathVariable Long id) {
        Ownership ownership = ownershipRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ownership with this ID does not exist"));
        ownershipRepository.delete(ownership);
    }

}
