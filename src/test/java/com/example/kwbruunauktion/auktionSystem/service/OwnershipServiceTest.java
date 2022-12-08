package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.OwnershipResponse;
import com.example.kwbruunauktion.auktionSystem.dto.OwnershipRequest;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.repository.OwnershipRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OwnershipServiceTest {
    public static OwnershipRepository ownershipRepository;

    public static OwnershipService ownershipService;

    @BeforeAll
    public static void initData(@Autowired OwnershipRepository ownership_Repository) {
        ownershipRepository = ownership_Repository;
        ownershipRepository.deleteAll();

        Ownership ownership1 = Ownership.builder()
                .id(1L)
                .name("Ford")
                .abbreviation("Mustang med soltag")
                .build();

        Ownership ownership2 = Ownership.builder()
                .id(2L)
                .name("Ford")
                .abbreviation("Fiesta med alu fælge")
                .build();

        ownershipRepository.save(ownership1);
        ownershipRepository.save(ownership2);

    }

    @BeforeEach
    public void setOwnershipServiceUp(){
        ownershipService = new OwnershipService(ownershipRepository);
    }

    @Test
    void addOwnership() {
        List<OwnershipResponse> ownerships = ownershipService.getOwnerships();
        assertEquals(2,ownerships.size());

        Ownership ownership = Ownership.builder()
                .id(3L)
                .name("hundai")
                .abbreviation("gets med 2000w højtalere")
                .build();

        Ownership ownership2 = Ownership.builder()
                .id(4L)
                .name("bmw")
                .abbreviation("tunede ruder")
                .build();


        OwnershipRequest ownershipRequest = new OwnershipRequest(ownership);
        OwnershipRequest ownershipRequest2 = new OwnershipRequest(ownership2);
        ownershipService.addOwnership(ownershipRequest);
        ownershipService.addOwnership(ownershipRequest2);

        List<OwnershipResponse> updatedListOfownerships = ownershipService.getOwnerships();

        assertEquals(4, updatedListOfownerships.size());

        assertEquals(3L, updatedListOfownerships.get(2).getId());
        assertEquals("hundai", updatedListOfownerships.get(2).getName());
        assertEquals("gets med 2000w højtalere", updatedListOfownerships.get(2).getAbbreviation());


        assertEquals(4L, updatedListOfownerships.get(3).getId());
        assertEquals("bmw", updatedListOfownerships.get(3).getName());
        assertEquals("tunede ruder", updatedListOfownerships.get(3).getAbbreviation());

    }
    @Test
    void getOwnership() {
        List<OwnershipResponse> listOfOwnerships = ownershipService.getOwnerships();

        assertEquals(2, listOfOwnerships.size());
        assertEquals("Ford", listOfOwnerships.get(1).getName());

        assertNotEquals(3, listOfOwnerships.size());
        assertNotEquals("Bmw", listOfOwnerships.get(0).getName());

        assertNotSame(listOfOwnerships.get(0), listOfOwnerships.get(1));
    }
    @Test
    void editOwnership() {
        List<OwnershipResponse> listOfOwnerships = ownershipService.getOwnerships();
        assertEquals(2, listOfOwnerships.size());
        assertEquals("Ford", listOfOwnerships.get(0).getName());

        Ownership ownership = Ownership.builder()
                .id(1L)
                .name("Citroen")
                .abbreviation("C500")
                .build();
        OwnershipRequest ownershipRequest = new OwnershipRequest(ownership);
        ownershipService.editOwnership(ownershipRequest, 1L);

        List<OwnershipResponse> listOfOwnershipNew = ownershipService.getOwnerships();
        assertEquals(2, listOfOwnershipNew.size());
        assertNotEquals("Ford", listOfOwnershipNew.get(0).getName());
        assertEquals("C500", listOfOwnershipNew.get(0).getAbbreviation());

    }
    @Test
    void deleteOwnership() {
        List<OwnershipResponse> listOfOwnerships = ownershipService.getOwnerships();
        assertEquals(2, listOfOwnerships.size());
        assertEquals("Ford", listOfOwnerships.get(0).getName());

        ownershipService.deleteOwnership(1L);

        List<OwnershipResponse> listOfOwnershipsNew = ownershipService.getOwnerships();
        assertEquals(1, listOfOwnershipsNew.size());
        assertEquals("Ford", listOfOwnershipsNew.get(0).getName());
        assertEquals("Fiesta med alu fælge",listOfOwnershipsNew.get(0).getAbbreviation());
    }
}