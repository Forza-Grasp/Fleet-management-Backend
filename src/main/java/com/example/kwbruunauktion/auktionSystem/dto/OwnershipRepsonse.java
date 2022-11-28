package com.example.kwbruunauktion.auktionSystem.dto;

import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.security.PrivateKey;
import java.time.LocalDateTime;

public class OwnershipRepsonse {

    private long id;

    private String name;

    private String abbreviation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated;

    public OwnershipRepsonse(Ownership o) {
        this.id = o.getId();
        this.name = o.getName();
        this.abbreviation = o.getAbbreviation();
    }

}
