package pageFactory.baseEntities;

import core.ReadProperties;
import core.WebDriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class BasePages {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected abstract void openPage();

    protected abstract boolean isPageOpened();

    public BasePages( boolean openPageByURL) {
        this.driver = WebDriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ReadProperties.getInstance().getTimeOut()));

        PageFactory.initElements(this.driver, this);

        if (openPageByURL){
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
    public void screenshot(){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy hh-mm-ss");
        String fileName = format.format(dateNow) + ".png";

        try {
            FileUtils.copyFile(screenshot, new File("D://Screenshots//" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
