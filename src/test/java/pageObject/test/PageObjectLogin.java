package pageObject.test;

import org.junit.jupiter.api.Test;
import pageObject.BaseEntities.BaseTest;
import pageObject.pages.InMailPageFactory;
import pageObject.pages.LoginPageFactory;

public class PageObjectLogin extends BaseTest {

    @Test
    void loginWithPageObject() {
        LoginPageFactory loginPageFactory = new LoginPageFactory(true);
        loginPageFactory.setEmail("siarheikost");
        loginPageFactory.loginButton();
        loginPageFactory.setPassword("1qaz2wsx!QAZ@WSX");
        loginPageFactory.loginButton();

        InMailPageFactory inMailPageFactory = new InMailPageFactory(false);
        inMailPageFactory.menuButton();
        inMailPageFactory.logOut();
    }
}
