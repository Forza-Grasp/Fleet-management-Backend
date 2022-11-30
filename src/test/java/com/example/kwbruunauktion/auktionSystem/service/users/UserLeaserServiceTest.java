package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserLeaserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserLeaserResponse;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
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
class UserLeaserServiceTest {

    UserLeaserService userLeaserService;

    public static UserLeaserRepository userLeaserRepository;
    public static SpecificCarModelRepository specificCarModelRepository;

    static int userLeaserSize;

    @BeforeAll
    public static void setupData(@Autowired UserLeaserRepository userLeaser_Repository,
                                 @Autowired SpecificCarModelRepository specificCarModel_Repository) {
        userLeaserRepository = userLeaser_Repository;
        specificCarModelRepository = specificCarModel_Repository;
        List<UserLeaser> userLeaserList = List.of(
                UserLeaser.userLeaserBuilder()
                        .id(1L)
                        .firstName("Peter")
                        .lastName("Parker")
                        .phoneNumber("12345678")
                        .email("leaser1@one.com")
                        .password("1234")
                        .user("leaser1")
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
                UserLeaser.userLeaserBuilder()
                        .id(2L)
                        .firstName("Jens")
                        .lastName("Hansen")
                        .phoneNumber("44221122")
                        .email("leaser2@one.com")
                        .password("4321")
                        .user("leaser2")
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
                UserLeaser.userLeaserBuilder()
                        .id(3L)
                        .firstName("Kasper")
                        .lastName("Olsen")
                        .phoneNumber("12345678")
                        .email("leaser3@one.dk")
                        .password("123456")
                        .user("leaser3")
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

        userLeaserRepository.saveAll(userLeaserList);
        userLeaserSize = userLeaserRepository.findAll().size();

    }

    @BeforeEach
    public void setUserLeaserServiceUp() {
        userLeaserService = new UserLeaserService(userLeaserRepository, specificCarModelRepository);
    }

    @Test
    void getAllUserLeasers() {
        int actualResult = userLeaserRepository.findAll().size();
        int expectedResult = userLeaserSize;

        int actualResultService = userLeaserService.getAllUserLeasers().size();

        assertEquals(expectedResult, actualResultService);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getUserLeaserById() {
        UserLeaserResponse userLeaserResponse = userLeaserService.getUserLeaserById(1L);
        String actualResult = userLeaserResponse.getFirstName();
        String expectedResult = "Peter";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createUserLeaser() {
        String expectedFirstName = "Laura";
        UserLeaser newUserLeaser = UserLeaser.userLeaserBuilder()
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

        UserLeaser addedUserLeaser = userLeaserRepository.save(newUserLeaser);
        int actualResult = userLeaserRepository.findAll().size();
        int expectedResult = userLeaserSize + 1;
        String actualFirstName = addedUserLeaser.getFirstName();

        int actualResultService = userLeaserService.getAllUserLeasers().size();
        String actualFirstNameService = userLeaserService.getUserLeaserById(4L).getFirstName();

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedFirstName, actualFirstName);
        assertEquals(expectedResult, actualResultService);
        assertEquals(expectedFirstName, actualFirstNameService);

    }

    @Test
    void editUserLeaser() {
        // find first userLeaser
        UserLeaser findUserLeaser = userLeaserRepository.findAll().get(0);
        String firstNameBefore = findUserLeaser.getFirstName();
        String newFirstName = "Karl";
        Optional<UserLeaser> userLeaserToEdit = userLeaserRepository.findById(1L);

        if (userLeaserToEdit.isPresent()) {
            UserLeaser userLeaser = userLeaserToEdit.get();

            userLeaser.setFirstName(newFirstName);
            UserLeaserRequest userLeaserRequest = new UserLeaserRequest(userLeaser);
            userLeaserService.editUserLeaser(userLeaserRequest);
        }

        String firstNameAfter = userLeaserRepository.findAll().get(0).getFirstName();
        assertNotEquals(firstNameBefore, firstNameAfter);
        assertEquals(newFirstName, firstNameAfter);

    }

    @Test
    void deleteUserLeaser() {
        userLeaserService.deleteUserLeaser(1L);
        int actualResult = userLeaserRepository.findAll().size();
        int expectedResult = userLeaserSize - 1;
        assertEquals(expectedResult, actualResult);

    }
}