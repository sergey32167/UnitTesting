package core;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    private static WebDriver driver;

    public static  WebDriver getDriverInstance() {
        if (driver == null) {
            driver = BrowsersService.createDriver();
        }
        return driver;
    }

    public static void quit() {
        driver.quit();
        driver = null;
    }
}