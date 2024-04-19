package tests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void commonConfig() {
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
