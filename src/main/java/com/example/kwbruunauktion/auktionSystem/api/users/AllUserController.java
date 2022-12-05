package com.example.kwbruunauktion.auktionSystem.api.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.response.AllUserResponse;
import com.example.kwbruunauktion.auktionSystem.service.users.AllUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users/all")
public class AllUserController {

    private final AllUserService allUserService;

    public AllUserController(AllUserService allUserService) {
        this.allUserService = allUserService;
    }

    @GetMapping
    public List<AllUserResponse> getAllUsers(Pageable p){
        return allUserService.getAllUsers(p);
    }
}
