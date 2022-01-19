package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowsersService {

    public static WebDriver createDriver() {
        String browserName = "chrome";

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                return new ChromeDriver();

            case "firefox":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                return new FirefoxDriver();

            default:
                System.out.println("Browser " + browserName + " is not supported.");
        }
        return null;
    }
}
