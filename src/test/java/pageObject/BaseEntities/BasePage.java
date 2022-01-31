package pageObject.BaseEntities;

import core.ReadProperties;
import core.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected abstract void openPage();

    protected abstract boolean isPageOpened();

    public BasePage(boolean openPageByURL) {
        this.driver = WebDriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ReadProperties.getInstance().getTimeOut()));

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
                throw new RuntimeException(e);
            }
            count++;
            isPageOpenedIndicator = isPageOpened();
        }
        if (!isPageOpenedIndicator) {
            throw new RuntimeException("Page was not opened");
        }
    }
}
