package pageFactory.baseEntities;

import core.ReadProperties;
import core.WebDriverSingleton;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final SimpleDateFormat format;

    protected abstract void openPage();

    protected abstract boolean isPageOpened();

    public BasePage(boolean openPageByURL) {
        this.driver = WebDriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ReadProperties.getInstance().getTimeOut()));
        this.format = new SimpleDateFormat("d MMM yyyy hh-mm-ss");

        PageFactory.initElements(this.driver, this);

        if (openPageByURL) {
            openPage();
        }
        waitForOpen();
    }

    protected void waitForOpen() {
        int count = 0;
        boolean isPageOpenedIndicator = isPageOpened();

        while (!isPageOpenedIndicator && count < ReadProperties.getInstance().getTimeOut()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            isPageOpenedIndicator = isPageOpened();
        }
        if (!isPageOpenedIndicator) {
            throw new RuntimeException("Page was not opened");
        }
    }

    public void screenshot() {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Date dateNow = new Date();
        String fileName = format.format(dateNow) + ".png";

        try(FileOutputStream file = new FileOutputStream("./screenshots" + " " + fileName)){
            file.write(screenshot);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File does not exist or is not available", ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
