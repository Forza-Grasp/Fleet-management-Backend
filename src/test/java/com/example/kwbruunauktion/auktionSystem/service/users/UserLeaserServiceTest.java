package com.example.kwbruunauktion.auktionSystem.service.users;

import com.example.kwbruunauktion.auktionSystem.dto.users.request.UserLeaserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.request.AddCarBrandToUserRequest;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserLeaserResponse;
import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserLeaserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @AfterEach
    public void deleteAfterTest() {
        userLeaserRepository.deleteAll();
        specificCarModelRepository.deleteAll();
    }

    @AfterAll
    public static void deleteAfterAll() {
        userLeaserRepository.deleteAll();
        specificCarModelRepository.deleteAll();
    }


    @Test
    public void getAllUserLeasers() {
        int actualResult = userLeaserRepository.findAll().size();
        int expectedResult = userLeaserSize;

        int actualResultService = userLeaserService.getAllUserLeasers().size();

        assertEquals(expectedResult, actualResultService);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void getUserLeaserById() {
        UserLeaserResponse userLeaserResponse = userLeaserService.getUserLeaserById(1L);
        String actualResult = userLeaserResponse.getFirstName();
        String expectedResult = "Peter";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void createUserLeaser() {
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
    public void editUserLeaser() {
       //find userLeaser
        UserLeaser userLeaser = userLeaserRepository.findById(1L).get();
        //edit userLeaser
        userLeaser.setFirstName("PeterEdited");
        //save userLeaser
        UserLeaserRequest userLeaserRequest = new UserLeaserRequest(userLeaser);
        userLeaserService.editUserLeaser(userLeaserRequest);
        //find userLeaser
        UserLeaser userLeaserEdited = userLeaserRepository.findById(1L).get();
        //check if userLeaser is edited
        String actualResult = userLeaserEdited.getFirstName();
        String expectedResult = "PeterEdited";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void deleteUserLeaser() {
        userLeaserService.deleteUserLeaser(1L);
        int actualResult = userLeaserRepository.findAll().size();
        int expectedResult = userLeaserSize - 1;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void addCarBrandToUserLeaser(){
        SpecificCarModel specificCarModel = SpecificCarModel.builder()
            .id(1L)
            .model("model1")
            .brand("brand1")
            .modelYear("2010")
            .build();
        specificCarModelRepository.save(specificCarModel);
        AddCarBrandToUserRequest request = AddCarBrandToUserRequest.builder()
            .carBrandId(specificCarModel.getId())
            .userId(1L)
            .build();
        userLeaserService.addCarBrandToUserLeaser(request);

        assertNotEquals(0, userLeaserRepository.findById(1L).get().getViewableCarBrands().size());
        assertEquals(1, userLeaserRepository.findById(1L).get().getViewableCarBrands().size());
    }
}