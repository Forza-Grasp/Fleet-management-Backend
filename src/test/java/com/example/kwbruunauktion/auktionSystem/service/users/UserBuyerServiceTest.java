package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserBuyerRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserBuyerResponse;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserLeaserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserBuyerServiceTest {

    UserBuyerService userBuyerService;

    public static UserBuyerRepository userBuyerRepository;
    public static SpecificCarModelRepository specificCarModelRepository;

    static int userBuyerSize;

    @BeforeAll
    public static void setupData(@Autowired UserBuyerRepository userBuyer_Repository,
                                 @Autowired SpecificCarModelRepository specificCarModel_Repository) {
        userBuyerRepository = userBuyer_Repository;
        specificCarModelRepository = specificCarModel_Repository;
        List<UserBuyer> userBuyerList = List.of(
                UserBuyer.userBuyerBuilder()
                        .id(1L)
                        .firstName("Peter")
                        .lastName("Parker")
                        .phoneNumber("12345678")
                        .email("buyer@one.com")
                        .password("1234")
                        .user("buyer1")
                        .addressLine1("address1")
                        .addressLine2("address2")
                        .city("Copenhagen NV")
                        .zipCode("2400")
                        .country("Denmark")
                        .companyName("CPH Business")
                        .companyEuVatNumber("DK20442425")
                        .ownership(Ownership.builder()
                                .id(1L)
                                .name("ownership1")
                                .abbreviation("own1")
                                .build())
                        .build(),
                UserBuyer.userBuyerBuilder()
                        .id(2L)
                        .firstName("Jens")
                        .lastName("Hansen")
                        .phoneNumber("44221122")
                        .email("buyer2@one.com")
                        .password("4321")
                        .user("buyer2")
                        .addressLine1("address1")
                        .addressLine2("address2")
                        .city("Copenhagen NV")
                        .zipCode("2400")
                        .country("Denmark")
                        .companyName("CPH Business")
                        .companyEuVatNumber("DK20442425")
                        .ownership(Ownership.builder()
                                .id(2L)
                                .name("ownership2")
                                .abbreviation("own2")
                                .build())
                        .build(),
                UserBuyer.userBuyerBuilder()
                        .id(3L)
                        .firstName("Kasper")
                        .lastName("Olsen")
                        .phoneNumber("12345678")
                        .email("buyer3@one.dk")
                        .password("123456")
                        .user("buyer3")
                        .addressLine1("address1")
                        .addressLine2("address2")
                        .city("Copenhagen NV")
                        .zipCode("2400")
                        .country("Denmark")
                        .companyName("CPH Business")
                        .companyEuVatNumber("DK20442425")
                        .ownership(Ownership.builder()
                                .id(3L)
                                .name("ownership3")
                                .abbreviation("own3")
                                .build())
                        .build());


        userBuyerRepository.saveAll(userBuyerList);
        userBuyerSize = userBuyerRepository.findAll().size();

    }

    @BeforeEach
    public void setUserBuyerService() {
        userBuyerService = new UserBuyerService(userBuyerRepository, specificCarModelRepository);
    }

    @Test
    void getAllUserBuyers() {
        int actualResult = userBuyerRepository.findAll().size();
        int expectedResult = userBuyerSize;

        int actualResultService = userBuyerService.getAllUserBuyers().size();

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult, actualResultService);
    }

    @Test
    void getUserBuyerById() {
        UserBuyerResponse userBuyerResponse = userBuyerService.getUserBuyerById(1L);
        String actualResult = userBuyerResponse.getFirstName();
        String expectedResult = "Peter";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createUserBuyer() {
        String expectedFirstName = "Laura";
        UserBuyer newUserBuyer = UserBuyer.userBuyerBuilder()
                .id(4L)
                .firstName(expectedFirstName)
                .lastName("Nielsen")
                .phoneNumber("55442233")
                .email("leaser4@one")
                .password("123456")
                .user("leaser4")
                .addressLine1("address1")
                .addressLine2("address2")
                .city("Copenhagen NV")
                .zipCode("2400")
                .country("Denmark")
                .companyName("CPH Business")
                .companyEuVatNumber("DK20442425")
                .ownership(Ownership.builder()
                        .id(4L)
                        .name("ownership4")
                        .abbreviation("own4")
                        .build())
                .build();

        UserBuyer addedUserBuyer = userBuyerRepository.save(newUserBuyer);
        int actualResult = userBuyerRepository.findAll().size();
        int expectedResult = userBuyerSize + 1;
        String actualFirstName = addedUserBuyer.getFirstName();

        int actualResultService = userBuyerService.getAllUserBuyers().size();
        String actualFirstNameService = userBuyerService.getUserBuyerById(4L).getFirstName();


        assertEquals(expectedResult, actualResult);
        assertEquals(expectedFirstName, actualFirstName);
        assertEquals(expectedResult, actualResultService);
        assertEquals(expectedFirstName, actualFirstNameService);
    }

    @Test
    void editUserBuyer() {
        UserBuyer findUserBuyer = userBuyerRepository.findAll().get(0);
        String firstNameBefore = findUserBuyer.getFirstName();
        String newFirstName = "Karl";
        Optional<UserBuyer> userBuyerToEdit = userBuyerRepository.findById(1L);


        if (userBuyerToEdit.isPresent()) {
            UserBuyer userBuyer = userBuyerToEdit.get();

            userBuyer.setFirstName(newFirstName);
            UserBuyerRequest userBuyerRequest = new UserBuyerRequest(userBuyer);
            userBuyerService.editUserBuyer(userBuyerRequest);
        }

        String firstNameAfter = userBuyerRepository.findById(1L).get().getFirstName();
        assertNotEquals(firstNameBefore, firstNameAfter);
        assertEquals(newFirstName, firstNameAfter);
    }

    @Test
    void deleteUserBuyer() {
        userBuyerService.deleteUserBuyer(1L);
        int actualResult = userBuyerRepository.findAll().size();
        int expectedResult = userBuyerSize - 1;
        assertEquals(expectedResult, actualResult);

    }
}