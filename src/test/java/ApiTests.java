import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("API tests from reqres.in")
public class ApiTests extends TestBase {

    @ValueSource(ints = {1, 2})
    @DisplayName("List users on the page ")
    @ParameterizedTest(name = "№ {0} with GET API")
    void pagesAreNotEmptyTest(int page) {
        given()
                .log().method()
                .log().uri()
        .when()
                .get("/api/users?page=" + page)
        .then()
                .log().body()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(page))
                .body("data", hasSize(not(0)));
    }
    @Test
    @DisplayName("Create user with POST API")
    void postUserApiTest() {
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
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
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", notNullValue());
    }
    @Test
    @DisplayName("Update User with PUT API")
    void putUpdateUserPositiveTest() {
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        given()
                .request()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .log().method()
                .log().uri()
        .when()
                .post("/api/users")
        .then()
                .log().body()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", notNullValue());
    }
    @Test
    @DisplayName("Update user with PATCH API")
    void patchUserPositiveTest() {
        String requestBody="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
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
                .body("updatedAt", notNullValue())
                .body("name", equalTo("morpheus"))
                .body("job",equalTo("zion resident"));
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
