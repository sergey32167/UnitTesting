package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task40 {
    private WebDriver driver;
    private WebDriverWait wait;
    private Alert alert;

    @BeforeEach
    void start() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @ParameterizedTest
    @CsvSource({"siarheikost , 1qaz2wsx!QAZ@WSX", "seregeikss, zaq1xsw2ZAQ!XSW@"})
    void login(String name, String psw) {
        driver.get("https://passport.yandex.com/auth/");

        driver.findElement(By.id("passp-field-login")).sendKeys(name);
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@dir = 'ltr']")));
        driver.findElement(By.xpath("//input[@dir = 'ltr']")).sendKeys(psw);
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".personal-info-login__text")));

        Assertions.assertTrue(driver.findElement(By.cssSelector(".personal-info-login__text")).isDisplayed());
    }

    @Test
    void alertConfirmBoxAccept() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.xpath("//button[@onclick  ='myConfirmFunction()']")).click();
        switchToAlert();
        alert.accept();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-demo")));
        Assertions.assertEquals(result.getText(), "You pressed OK!");
    }

    @Test
    void alertConfirmBoxDismiss() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.xpath("//button[@onclick  ='myConfirmFunction()']")).click();
        switchToAlert();
        alert.dismiss();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-demo")));
        Assertions.assertEquals(result.getText(), "You pressed Cancel!");
    }

    @Test
    void alertPromptBox() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.xpath("//button[@onclick  ='myPromptFunction()']")).click();
        switchToAlert();
        alert.sendKeys("HELLO");
        alert.accept();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prompt-demo")));
        Assertions.assertEquals(result.getText(), "You have entered 'HELLO' !");
    }

    @Test
    void waitsForUser() {
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");
        driver.findElement(By.id("save")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'loading']/img")));

        Assertions.assertTrue(driver.findElement(By.xpath("//div[@id = 'loading']/img")).isDisplayed());
    }

    @Test
    void multiselect() {
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        Select select = new Select(driver.findElement(By.xpath("//select[@id = 'multi-select']")));
        select.selectByVisibleText("California");
        select.selectByVisibleText("Pennsylvania");
        select.selectByIndex(2);
        driver.findElement(By.xpath("//button[@value = 'Print All']")).click();
        select.selectByValue("New York");
        driver.findElement(By.xpath("//button[@value = 'Print First']")).click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.getall-selected")));
        Assertions.assertEquals(result.getText(), "First selected option is : New York");
    }

    @Test
    void refreshDownload() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");

        driver.findElement(By.id("cricle-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'percenttext'][text() = '50%']")));
        driver.navigate().refresh();
    }

    @Test
    void table() {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");

        Select select = new Select(driver.findElement(By.tagName("select")));
        select.selectByIndex(0);
        Assertions.assertEquals(10, driver.findElements(By.xpath("//tbody//tr")).size());

        listOfSortedObjects();
    }

    private void listOfSortedObjects() {
        List<String> finalList = new ArrayList<>();
        Select select = new Select(driver.findElement(By.tagName("select")));
        select.selectByIndex(2);
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
        List<WebElement> nameColumns = driver.findElements(By.xpath("//thead//th"));
        List<List<WebElement>> cellsList = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> cellList = row.findElements(By.tagName("td"));
            cellsList.add(cellList);
        }
        List<Map<String, WebElement>> cellsByNameColumns = new ArrayList<>();
        Map<String, WebElement> cellByNameColumns;
        for (List<WebElement> row : cellsList) {
            cellByNameColumns = new HashMap<>();
            for (int j = 0; j < nameColumns.size(); j++) {
                String columnName = nameColumns.get(j).getText();
                WebElement cell = row.get(j);
                cellByNameColumns.put(columnName, cell);
            }
            cellsByNameColumns.add(cellByNameColumns);
        }
        for (Map<String, WebElement> row : cellsByNameColumns) {
            int age = Integer.parseInt(row.get("Age").getText());
            String salary = row.get("Salary").getText();
            int salaryInt = Integer.parseInt((salary.substring(1, salary.length() - 2).replace(",", "")));
            if (age > 40 && salaryInt < 350000) {
                String result = "\n" + row.get("Name").getText() + " " + row.get("Position").getText() + " " + row.get("Office").getText();
                finalList.add(result);
            }
        }
        System.out.println(finalList);
    }

    private void switchToAlert() {
        alert = driver.switchTo().alert();
    }

    @AfterEach
    void finish() {
        driver.quit();
    }
}
