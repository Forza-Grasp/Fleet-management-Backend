package com.example.kwbruunauktion.auktionSystem.api;

import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesRequest;
import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesResponse;
import com.example.kwbruunauktion.auktionSystem.service.ColorTypesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/color-types")
public class ColorTypesController {

    ColorTypesService colorTypesService;

    public ColorTypesController(ColorTypesService colorTypesService) {
        this.colorTypesService = colorTypesService;
    }

    @PostMapping
    ColorTypesResponse addColorType(@RequestBody ColorTypesRequest colorTypesRequest){
        return colorTypesService.addColorType(colorTypesRequest);
    }

    @GetMapping
    List<ColorTypesResponse> getAllColorTypes(){
        return colorTypesService.getAllColorTypes();
    }

    @GetMapping("/{id}")
    ColorTypesResponse getColorTypeById(@PathVariable Long id){
        return colorTypesService.getColorTypeById(id);
    }

    @PutMapping
    ColorTypesResponse editColorTypeId(@RequestBody ColorTypesRequest colorTypesRequest){
        return colorTypesService.editColorType(colorTypesRequest);
    }

    @DeleteMapping("/{id}")
    ColorTypesResponse deleteColorById(@PathVariable Long id){
        return colorTypesService.deleteColorTypeById(id);
    }
}
