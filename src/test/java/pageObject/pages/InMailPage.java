package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageObject.BaseEntities.BasePage;

public class InMailPage extends BasePage {

    private final static By loginLogo = By.cssSelector(".personal-info-login__text");
    private final static By menuButton = By.xpath("//a/div//img");
    private final static By logOut = By.xpath("//a[@class = 'menu__item menu__item_type_link legouser__menu-item legouser__menu-item_action_exit']/span");

    public InMailPage(boolean openPageByURL) {
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

    public LoginPage logOut(){
        openMenu();
        logOutClick();
        return new LoginPage(false);
    }

    public void openMenu() {
        getMenuButton().click();
    }

    public void logOutClick() {
        getLogOut().click();
    }
}
