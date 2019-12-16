package Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase {
    LoginPage loginPage;

    @Test(priority = 1)
    public void AddUserNameAndPasswordEmpty() {

        loginPage = new LoginPage(driver);
        loginPage.AddUserName("");
        loginPage.AddPassWord("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.errorMsg.getText().contains("Your username is invalid!"));
    }

    @Test(priority = 2)
    public void AddPasswordEmpty() {

        loginPage = new LoginPage(driver);
        loginPage.AddUserName("tomsmith");
        loginPage.AddPassWord("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.errorMsg.getText().contains("Your password is invalid!"));
    }

    @Test(priority = 3)
    public void AddUserNameEmpty() {

        loginPage = new LoginPage(driver);
        loginPage.AddUserName("");
        loginPage.AddPassWord("SuperSecretPassword!");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.errorMsg.getText().contains("Your username is invalid!"));
    }

    @Test(priority = 4)
    public void AddValidAccount() {

        loginPage = new LoginPage(driver);
            loginPage.AddUserName("tomsmith");
        loginPage.AddPassWord("SuperSecretPassword!");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.errorMsg.getText().contains("You logged into a secure area!"));
    }

    @Test(priority = 5)
    public void AddValidAccountAndClickBack() {

        loginPage = new LoginPage(driver);
        loginPage.clickLogout();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        Assert.assertTrue(loginPage.errorMsg.getText().contains("You logged out of the secure area!"));
    }
}