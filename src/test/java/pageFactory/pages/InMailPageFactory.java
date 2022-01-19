package pageFactory.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageFactory.baseEntities.BasePages;

public class InMailPageFactory extends BasePages {

    @FindBy(css = ".personal-info-login__text")
    private WebElement loginLogo;
    @FindBy(xpath = "//a/div//img")
    private WebElement menuButton;
    @FindBy(xpath = "//ul[@class = 'menu__group']//span[text() = 'Log out']")
    private WebElement logOut;

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
            return loginLogo.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public InMailPageFactory menuButton() {
        menuButton.click();
        return this;
    }

    public InMailPageFactory logOut() {
        logOut.click();
        return this;
    }
}
