package pageFactory.test;

import core.ReadProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageFactory.baseEntities.BaseTest;
import pageFactory.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    void login() {
        LoginPage loginPage = new LoginPage(true);
        loginPage.singIn(ReadProperties.getInstance().getUsername(), ReadProperties.getInstance().getPassword())
                .logOut();

        Assertions.assertEquals("Enter your password", loginPage.getText());
    }
}
