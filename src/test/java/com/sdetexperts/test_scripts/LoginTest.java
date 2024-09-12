package com.sdetexperts.test_scripts;
import com.sdetexperts.pages.LoginPage;
import com.sdetexperts.reportutils.ReportBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends ReportBuilder {

    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod(){
        loginPage = LoginPage.getInstance();
    }

    @Test
    public void loginTest(){
        loginPage.launchLoginPage("https://katalon-demo-cura.herokuapp.com/");
    }

    @AfterTest
    public void tearDown(){
        loginPage.closeBrowser();
    }
}
