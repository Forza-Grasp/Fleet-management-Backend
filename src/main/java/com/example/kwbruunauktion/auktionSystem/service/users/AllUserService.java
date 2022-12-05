package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.response.AllUserResponse;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserAdmin;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserEconomy;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserAdminRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserEconomyRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserLeaserRepository;
import com.example.kwbruunauktion.security.entity.Role;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import com.example.kwbruunauktion.security.repository.UserWithRolesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllUserService {

    private final UserAdminRepository userAdminRepository;
    private final UserBuyerRepository userBuyerRepository;
    private final UserEconomyRepository userEconomyRepository;
    private final UserLeaserRepository userLeaserRepository;
    private final UserWithRolesRepository userWithRolesRepository;

    public AllUserService(UserAdminRepository userAdminRepository,
                          UserBuyerRepository userBuyerRepository,
                          UserEconomyRepository userEconomyRepository,
                          UserLeaserRepository userLeaserRepository,
                          UserWithRolesRepository userWithRolesRepository) {
        this.userAdminRepository = userAdminRepository;
        this.userBuyerRepository = userBuyerRepository;
        this.userEconomyRepository = userEconomyRepository;
        this.userLeaserRepository = userLeaserRepository;
        this.userWithRolesRepository = userWithRolesRepository;
    }

    public List<AllUserResponse> getAllUsers(Pageable p) {

        Page<UserWithRoles> allUsersList = userWithRolesRepository.findAll(p);

        return fixUserWithRolesList(allUsersList);

    }
    public List<AllUserResponse> fixUserWithRolesList(Page<UserWithRoles> userWithRolesList)
    {
        List<AllUserResponse> responseList = new ArrayList<>();
        for (UserWithRoles
                userWithRoles : userWithRolesList) {
            {
                if (userWithRoles.getRoles().get(0).equals(Role.BUYER)) {
                    UserBuyer userBuyer = userBuyerRepository.findById(userWithRoles.getId()).get();
                    responseList.add(new AllUserResponse(userBuyer));
                } else if (userWithRoles.getRoles().get(0).equals(Role.LEASER)) {
                    UserLeaser userLeaser = userLeaserRepository.findById(userWithRoles.getId()).get();
                    responseList.add(new AllUserResponse(userLeaser));
                } else if (userWithRoles.getRoles().get(0).equals(Role.ADMIN)) {
                    UserAdmin userAdmin = userAdminRepository.findById(userWithRoles.getId()).get();
                    responseList.add(new AllUserResponse(userAdmin));
                } else if (userWithRoles.getRoles().get(0).equals(Role.ECONOMY)) {
                    UserEconomy userEconomy = userEconomyRepository.findById(userWithRoles.getId()).get();
                    responseList.add(new AllUserResponse(userEconomy));
                }

            }

        }
        return responseList;
    }
}
