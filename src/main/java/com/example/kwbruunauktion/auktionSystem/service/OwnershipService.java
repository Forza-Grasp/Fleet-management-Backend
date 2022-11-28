package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRepsonse;
import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRequest;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.repository.OwnershipRepository;
import org.springframework.stereotype.Service;

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

    public void editOwnership (OwnershipRequest ownershipRequest, long id){
        Ownership ownership = ownershipRepository.findById(id).orElseThrow(()-> new RuntimeException("Ownership with this ID does not exist"));
        ownership.setOwnerships(ownershipRepository.getOwnershipById(ownershipRequest.getOwnershipId()));
        ownership.setOwnership(ownershipRepository.getOwnershipById)(ownershipRequest.getOwnershipId()));
        ownershipRepository.save(ownership);
    }


}
