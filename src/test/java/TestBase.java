import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void commonConfig() {
        RestAssured.baseURI="https://reqres.in";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://reqres.in";
//        Configuration.browser = System.getProperty("browser", "chrome");
//        Configuration.browserVersion = System.getProperty("version", "122.0");
//        Configuration.holdBrowserOpen = true;
//        Configuration.browserSize = System.getProperty("resolution", "1280x1024");
//        Configuration.remote = "https://user1:1234@" + System.getProperty("remote", "selenoid.autotests.cloud") + "/wd/hub";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
//
//    @AfterEach
//    void finalResult() {
//        Attachments.screenshotAs("Last screen");
//        Attachments.pageSource();
//        Attachments.browserConsoleLogs();
//        Attachments.addVideo();
//        closeWebDriver();
//    }
}
