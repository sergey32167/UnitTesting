package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task40 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void start() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest
    @CsvSource({"siarheikost , 1qaz2wsx!QAZ@WSX", "seregeikss, zaq1xsw2ZAQ!XSW@"})
    void login(String name, String psw) throws InterruptedException {
        driver.get("https://passport.yandex.com/auth/add?retpath=https%3A%2F%2Fpassport.yandex.com%2Fprofile&noreturn=1");

        driver.findElement(By.id("passp-field-login")).sendKeys(name);
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
        Thread.sleep(1500);//(ExplicitlyWait)
        driver.findElement(By.xpath("//input[@dir = 'ltr']")).sendKeys(psw);
        driver.findElement(By.cssSelector(".Button2.Button2_size_l.Button2_view_action")).click();
        Thread.sleep(2500);//(ExplicitlyWait)

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".personal-info-login__text")));
    }

    @Test
    void alertBoxTest() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.xpath("//button[@onclick  ='myAlertFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    @Test
    void alertConfirmBoxAccept() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.xpath("//button[@onclick  ='myConfirmFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-demo")));
        Assertions.assertEquals(result.getText(), "You pressed OK!");
    }

    @Test
    void alertConfirmBoxDismiss() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.xpath("//button[@onclick  ='myConfirmFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.dismiss();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-demo")));
        Assertions.assertEquals(result.getText(), "You pressed Cancel!");
    }

    @Test
    void alertPromptBox() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.xpath("//button[@onclick  ='myPromptFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
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
    }

    @Test
    void refreshDownload() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");

        driver.findElement(By.id("cricle-btn")).click();
        while (true) {
            String percent = driver.findElement(By.xpath("//div[@class = 'percenttext']")).getText();
            if (Integer.parseInt(percent.substring(0, percent.length() - 1)) > 50) {
                driver.navigate().refresh();
                break;
            }
        }
    }

    @Test
    void table() {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");

        Select select = new Select(driver.findElement(By.tagName("select")));
        select.selectByIndex(0);

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

    @AfterEach
    void finish() {
        driver.quit();
    }
}
