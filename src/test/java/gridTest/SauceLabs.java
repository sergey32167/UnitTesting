package gridTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SauceLabs {
    public WebDriver driver;

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void demoTest() throws MalformedURLException {

        EdgeOptions browserOptions = new EdgeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest-1");
        Map<String, Object> sauceOptions = new HashMap<>();
        browserOptions.setCapability("sauce:options", sauceOptions);

        driver = new RemoteWebDriver(new URL("https://siarheikost:28413c41-f1b9-47ed-863c-698a77626b32@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), browserOptions);
        driver.manage().window().maximize();

        logInOut();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void demoTest1() throws MalformedURLException {

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 8.1");
        browserOptions.setBrowserVersion("79");
        Map<String, Object> sauceOptions = new HashMap<>();
        browserOptions.setCapability("sauce:options", sauceOptions);

        driver = new RemoteWebDriver(new URL("https://siarheikost:28413c41-f1b9-47ed-863c-698a77626b32@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), browserOptions);
        driver.manage().window().maximize();

        logInOut();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void demoTest2() throws MalformedURLException {

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Linux");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        browserOptions.setCapability("sauce:options", sauceOptions);

        driver = new RemoteWebDriver(new URL("https://siarheikost:28413c41-f1b9-47ed-863c-698a77626b32@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), browserOptions);
        driver.manage().window().maximize();

        logInOut();
    }

    private void logInOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://passport.yandex.com/auth/add?retpath=https%3A%2F%2Fpassport.yandex.com%2Fprofile&noreturn=1");
        driver.findElement(By.id("passp-field-login")).sendKeys("siarheikost");
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@dir = 'ltr']")));
        driver.findElement(By.xpath("//input[@dir = 'ltr']")).sendKeys("3edcvfr4#EDCVFR$");
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
    }

    @AfterEach
    void end() {
        driver.quit();
    }
}
