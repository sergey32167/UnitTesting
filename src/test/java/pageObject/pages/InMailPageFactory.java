package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageObject.BaseEntities.BasePages;

public class InMailPageFactory extends BasePages {

    private final static By loginLogo = By.cssSelector(".personal-info-login__text");
    private final static By menuButton = By.xpath("//a/div//img");
    private final static By logOut = By.xpath("//ul[@class = 'menu__group']//span[text() = 'Log out']");

    public InMailPageFactory( boolean openPageByURL) {
        super(openPageByURL);
    }

    @Override
    protected void openPage() {
        driver.get("https://passport.yandex.com/profile");
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return getLoginLogo().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getLoginLogo() {
        return driver.findElement(loginLogo);
    }
    private WebElement getMenuButton() {
        return driver.findElement(menuButton);
    }
    private WebElement getLogOut() {
        return driver.findElement(logOut);
    }

    public InMailPageFactory menuButton() {
        getMenuButton().click();
        return this;
    }

    public InMailPageFactory logOut() {
        getLogOut().click();
        return this;
    }
}
