package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task30 {
    private WebDriver driver;

    @BeforeEach
    void start() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
    }

    @Test
    void login() throws InterruptedException {
        driver.get("https://passport.yandex.com/auth/add?retpath=https%3A%2F%2Fpassport.yandex.com%2Fprofile&noreturn=1");

        driver.findElement(By.id("passp-field-login")).sendKeys("siarheikost");
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
        Thread.sleep(1500);//(ExplicitlyWait)
        driver.findElement(By.xpath("//input[@dir = 'ltr']")).sendKeys("1qaz2wsx!QAZ@WSX");
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
        Thread.sleep(2500);//(ExplicitlyWait)

        Assertions.assertTrue(driver.findElement(By.cssSelector(".personal-info-login__text")).isDisplayed());
    }

    @AfterEach
    void finish() {
        driver.quit();
    }
}
