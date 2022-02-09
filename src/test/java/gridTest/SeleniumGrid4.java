package gridTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGrid4 {
    public WebDriver driver;
    public DesiredCapabilities capabilities = new DesiredCapabilities();

//java -jar selenium-server-4.1.2.jar standalone

//java -jar selenium-server-4.1.2.jar hub
//java -jar selenium-server-4.1.2.jar node

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void demoTest7() throws MalformedURLException {

        capabilities.setBrowserName("chrome");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);

        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Selenium Grid 4");
        driver.findElement(By.name("q")).submit();

        driver.quit();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void demoTest() throws MalformedURLException {

        capabilities.setBrowserName("firefox");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);

        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Selenium Grid 4");
        driver.findElement(By.name("q")).submit();

        driver.quit();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void demoTest1() throws InterruptedException, MalformedURLException {

        capabilities.setBrowserName("firefox");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);

        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Selenium Grid 4");
        driver.findElement(By.name("q")).submit();

        Thread.sleep(2000);

        driver.quit();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void demoTest2() throws MalformedURLException {

        capabilities.setBrowserName("chrome");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);

        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Selenium Grid 4");
        driver.findElement(By.name("q")).submit();

        driver.quit();
    }
}
