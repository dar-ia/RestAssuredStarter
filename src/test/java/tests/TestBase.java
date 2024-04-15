package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void commonConfig() {
        RestAssured.baseURI="https://reqres.in";
    }
}
