package pageObject.test;

import core.ReadProperties;
import core.Watcher;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageObject.pages.LoginPage;

@ExtendWith(Watcher.class)
public class LoginTest{

    @Epic(value = "Login in mail")
    @Feature(value = "Correct test")
    @AllureId(value = "1")
    @Description("Login with correct data")
    @Link(value = "yandex mail", url = "https://passport.yandex.com/auth/add?retpath=https%3A%2F%2Fpassport.yandex.com%2Fprofile&noreturn=1")
    @Test
    void login() {
        LoginPage loginPage = new LoginPage(true);
        loginPage.singIn(ReadProperties.getInstance().getUsername(), ReadProperties.getInstance().getPassword())
                .logOut();

        Assertions.assertEquals("Enter your password", loginPage.getText());
    }

    @Epic(value = "Login in mail")
    @Feature(value = "Fail test")
    @AllureId(value = "2")
    @Description("Login with correct data - fail test")
    @Link(value = "yandex mail", url = "https://passport.yandex.com/auth/add?retpath=https%3A%2F%2Fpassport.yandex.com%2Fprofile&noreturn=1")
    @Test
    void loginFail() {
        LoginPage loginPage = new LoginPage(true);
        loginPage.singIn(ReadProperties.getInstance().getUsername(), ReadProperties.getInstance().getPassword())
                .logOut();

        Assertions.assertTrue(false);
    }
}
