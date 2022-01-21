package pageObject.BaseEntities;

import core.WebDriverSingleton;
import org.junit.jupiter.api.AfterEach;

public abstract class BaseTest {

    @AfterEach
    void finish() {
        WebDriverSingleton.getDriverInstance().quit();
    }

}
