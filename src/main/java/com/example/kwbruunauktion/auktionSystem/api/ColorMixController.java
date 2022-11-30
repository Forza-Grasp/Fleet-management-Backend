package com.example.kwbruunauktion.auktionSystem.api;

import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.service.ColorMixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/colormix")
public class ColorMixController {

    ColorMixService colorMixService;

    public ColorMixController(ColorMixService colorMixService){
        this.colorMixService = colorMixService;
    }

    @RequestMapping
    public List<ColorMixResponse> getAll(){
        return colorMixService.getAll();
    }

    @RequestMapping("/{id}")
    public ColorMixResponse getSpecific(@PathVariable Long id){
        return colorMixService.getSpecificById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ColorMixResponse addColorMix(@RequestBody ColorMixRequest colorMix){
        return colorMixService.addColorMix(colorMix);
    }

    @PutMapping
    public ResponseEntity<Boolean> editColorMix(@RequestBody ColorMixRequest color){
        colorMixService.editColorMix(color);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteColorMix(@PathVariable Long id){
        colorMixService.deleteColorMix(id);
    }



}

