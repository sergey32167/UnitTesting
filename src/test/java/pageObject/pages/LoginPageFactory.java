package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.BaseEntities.BasePages;

public class LoginPageFactory extends BasePages {

    private final static By loginInput = By.id("passp-field-login");
    private final static By passwordInput = By.id("passp-field-passwd");
    private final static By loginButton = By.id("passp:sign-in");
    private final static By yandexLogo = By.xpath("//div[@class = 'Header-yaLogoBlock']");

    public LoginPageFactory(boolean openPageByURL) {
        super(openPageByURL);
    }

    @Override
    protected void openPage() {
        driver.get("https://passport.yandex.com/auth/add?retpath=https%3A%2F%2Fpassport.yandex.com%2Fprofile&noreturn=1");
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return getYandexLogo().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getLoginInput() {
        return driver.findElement(loginInput);
    }

    private WebElement getPasswordInput() {
        return driver.findElement(passwordInput);
    }

    private WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }

    private WebElement getYandexLogo() {
        return driver.findElement(yandexLogo);
    }

    public LoginPageFactory setEmail(String text) {
        getLoginInput().sendKeys(text);
        return this;
    }

    public LoginPageFactory setPassword(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(text);
        return this;
    }

    public LoginPageFactory loginButton() {
        getLoginButton().click();
        return this;
    }

}
