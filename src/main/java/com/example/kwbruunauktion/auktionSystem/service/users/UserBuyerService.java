package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.repository.UserBuyerRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBuyerService {

    UserBuyerRepository userBuyerRepository;

    public UserBuyerService(UserBuyerRepository userBuyerRepository) {
        this.userBuyerRepository = userBuyerRepository;
    }

    // View all users


}
