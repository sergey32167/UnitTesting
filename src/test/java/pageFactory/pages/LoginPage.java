package pageFactory.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageFactory.baseEntities.BasePages;

public class LoginPage extends BasePages {

    @FindBy(id = "passp-field-login")
    private WebElement loginInput;
    @FindBy(id = "passp-field-passwd")
    private WebElement passwordInput;
    @FindBy(id = "passp:sign-in")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@class = 'Header-yaLogoBlock']")
    private WebElement yandexLogo;
    @FindBy(tagName = "label")
    private WebElement labelText;

    public LoginPage(boolean openPageByURL) {
        super(openPageByURL);
    }

    @Override
    protected void openPage() {
        driver.get("https://passport.yandex.com/auth/add?retpath=https%3A%2F%2Fpassport.yandex.com%2Fprofile&noreturn=1");
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return yandexLogo.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public InMailPage singIn(String name, String psw){
        setEmail(name);
        loginButton();
        setPassword(psw);
        loginButton();
        return new InMailPage(false);
    }

    public void setEmail(String text) {
        loginInput.sendKeys(text);
    }

    public void setPassword(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(text);
    }

    public void loginButton() {
        loginButton.click();
    }

    public String getText(){
        return labelText.getText();
    }

}
