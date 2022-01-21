package pageFactory.baseEntities;

import core.WebDriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public abstract class BaseTest {

    @AfterEach
    void finish() {
        WebDriverSingleton.getDriverInstance().quit();
    }
}
