package pageObject.BaseEntities;

import core.WebDriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void start() {
       driver = WebDriverSingleton.getDriverInstance();
    }

    @AfterEach
    void finish() {
        WebDriverSingleton.quit();
    }

}
