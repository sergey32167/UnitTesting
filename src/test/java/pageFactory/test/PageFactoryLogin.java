package pageFactory.test;

import org.junit.jupiter.api.Test;
import pageFactory.baseEntities.BaseTest;
import pageFactory.pages.InMailPageFactory;
import pageFactory.pages.LoginPageFactory;

public class PageFactoryLogin extends BaseTest {

    @Test
    void loginWithPageFactory() {
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
