package tests;

import io.restassured.http.ContentType;
import models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

@DisplayName("API tests from reqres.in")
public class ApiTests extends TestBase {

    @ValueSource(ints = {1, 2})
    @DisplayName("List users on the page ")
    @ParameterizedTest(name = "№ {0} with GET API")
    void pagesAreNotEmptyTest(int page) {
        ListUsersResponseModel response =
                given()
                        .log().method()
                        .log().uri()
                .when()
                        .get("/api/users?page=" + page)
                .then()
                        .log().body()
                        .assertThat()
                        .statusCode(200)
                        .extract().as(ListUsersResponseModel.class);
        Assertions.assertEquals(page, response.getPage());
        Assertions.assertTrue(response.getData().length>0);
    }

    @Test
    @DisplayName("Create user with POST API")
    void postUserApiTest() {

        CreateUserRequestModel requestBody = new CreateUserRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("leader");
        CreateUserResponseModel reponse =

        given()
                .request()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .log().method()
                .log().uri()
                .log().body()
        .when()
                .post("/api/users")
        .then()
                .log().body()
                .assertThat()
                .statusCode(201)
                .extract().as(CreateUserResponseModel.class);

        Assertions.assertEquals("morpheus", reponse.getName());
        Assertions.assertEquals("leader", reponse.getJob());
        Assertions.assertNotNull(reponse.getId());
    }

    @Test
    @DisplayName("Update User with PUT API")
    void putUpdateUserPositiveTest() {

        UpdateUserRequestModel requestBody = new UpdateUserRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("caller");
        UpdateUserResponseModel response =
        given()
                .request()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .log().method()
                .log().uri()
        .when()
                .put("/api/users/2")
        .then()
                .log().body()
                .assertThat()
                .statusCode(200)
                .extract().as(UpdateUserResponseModel.class);

        Assertions.assertEquals("morpheus",response.getName());
        Assertions.assertEquals("caller", response.getJob());
        Assertions.assertNotNull("updatedAt");
//                .body("name", equalTo("morpheus"))
//                .body("job", equalTo("caller"))
//                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Update user with PATCH API")
    void patchUserPositiveTest() {
        UpdateUserRequestModel requestBody = new UpdateUserRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("zion resident");
        UpdateUserResponseModel response =
        given()
                .request()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .log().method()
                .log().uri()
                .log().body()
        .when()
                .patch("/api/users/2")
        .then()
                .log().body()
                .assertThat()
                .statusCode(200)
                .extract().as(UpdateUserResponseModel.class);

        Assertions.assertEquals("morpheus",response.getName());
        Assertions.assertEquals("zion resident", response.getJob());
        Assertions.assertNotNull("updatedAt");
    }

    @Test
    @DisplayName("Delete user with DELETE API")
    void deleteUserPositiveTest() {
        given()
                .log().method()
                .log().uri()
                .when()
                .delete("/api/users/2")
                .then()
                .log().body()
                .assertThat()
                .statusCode(204);
    }
}
