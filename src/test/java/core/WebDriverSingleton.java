package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static  WebDriver getDriverInstance() {
        if (WebDriverSingleton.getDriver() == null) {
            driver.set(createDriver());
        }
        return WebDriverSingleton.getDriver();
    }

    private static WebDriver createDriver() {
        String browserName = ReadProperties.getInstance().getBrowserName();

        switch (Browsers.getBrowser(browserName)) {
            case CHROME:
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                return new ChromeDriver();

            case FIREFOX:
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                return new FirefoxDriver();

            default:
                System.out.println("Browser " + browserName + " is not supported.");
        }
        return null;
    }
}