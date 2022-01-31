package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

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
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", "d://default_directory");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.setExperimentalOption("prefs", prefs);
                return new ChromeDriver(options);

            case FIREFOX:
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                return new FirefoxDriver(firefoxOptions);

            default:
                System.out.println("Browser " + browserName + " is not supported.");
        }
        return null;
    }

    public static void quit(){
        WebDriverSingleton.getDriverInstance().quit();
        driver.set(null);
    }
}