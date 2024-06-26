package tests;

import models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static specs.UserSpecs.*;

@DisplayName("API tests")
public class ApiTests extends TestBase {

    @ValueSource(ints = {1, 2})
    @DisplayName("List users on the page ")
    @ParameterizedTest(name = "№ {0} with GET API")
    void pagesAreNotEmptyTest(int page) {
        ListUsersResponseModel response = step("Send request", () -> given(sendEmptyBodyRequestSpec)
                .when()
                .get("/users?page=" + page)
                .then()
                .spec(updateFetchResponseSpec)
                .extract().as(ListUsersResponseModel.class)
        );
        step("Validate response", () -> {
            Assertions.assertEquals(page, response.getPage());
            Assertions.assertTrue(response.getData().length > 0);
        });
    }

    @Test
    @DisplayName("Create user with POST API")
    void postUserApiTest() {
        SendUserRequestModel requestBody = new SendUserRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("leader");
        CreateUserResponseModel reponse = step("Send request", () -> {
            return given(sendRequestWithBodySpec)
                    .body(requestBody)
                    .when()
                    .post("/users")
                    .then()
                    .spec(createResponseSpec)
                    .extract().as(CreateUserResponseModel.class);
        });
        step("Validate response", () -> {
            Assertions.assertEquals("morpheus", reponse.getName());
            Assertions.assertEquals("leader", reponse.getJob());
            Assertions.assertNotNull(reponse.getId());
        });
    }

    @Test
    @DisplayName("Update User with PUT API")
    void putUpdateUserPositiveTest() {
        SendUserRequestModel requestBody = new SendUserRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("caller");
        UpdateUserResponseModel response = step("Send request", () -> given(sendRequestWithBodySpec)
                .body(requestBody)
                .when()
                .put("/users/2")
                .then()
                .spec(updateFetchResponseSpec)
                .extract().as(UpdateUserResponseModel.class)
        );
        step("Validate response", () -> {
            Assertions.assertEquals("morpheus", response.getName());
            Assertions.assertEquals("caller", response.getJob());
            Assertions.assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @DisplayName("Update user with PATCH API")
    void patchUserPositiveTest() {
        SendUserRequestModel requestBody = new SendUserRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("zion resident");
        UpdateUserResponseModel response = step("Send request", () ->
                given(sendRequestWithBodySpec)
                        .body(requestBody)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(updateFetchResponseSpec)
                        .extract().as(UpdateUserResponseModel.class)
        );
        step("Validate response", () -> {
            Assertions.assertEquals("morpheus", response.getName());
            Assertions.assertEquals("zion resident", response.getJob());
            Assertions.assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @DisplayName("Get not existing user")
    void getNotExistingUserFailsTest() {
        SingleUserModel response =
                step("Send request", () -> given(sendEmptyBodyRequestSpec)
                        .when()
                        .get("/users/23")
                        .then()
                        .spec(notFoundResponseSpec)
                        .extract().as(SingleUserModel.class)
                );
        step("Validate response", () -> {
            Assertions.assertEquals(0, response.getId());
            Assertions.assertNull(response.getEmail());
            Assertions.assertNull(response.getAvatar());
            Assertions.assertNull(response.getLastName());
            Assertions.assertNull(response.getFirstName());
        });
    }
}
