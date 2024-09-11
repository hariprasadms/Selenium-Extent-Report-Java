package com.sdetexperts.pages;

import com.sdetexperts.framework_base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Base {

    private final WebDriver driver;

    private static LoginPage instance;

    protected LoginPage() {

        this.driver = Base.getInstance().getDriver();
    }

    public static LoginPage getInstance() {
        if (instance == null) {
            synchronized (LoginPage.class) {
                if (instance == null) {
                    instance = new LoginPage();
                }
            }
        }
        return instance;
    }

    @FindBy(xpath = "username")
    private WebElement usernameField;

    @FindBy(xpath = "password")
    private WebElement passwordField;

    @FindBy(xpath = "submit")
    private WebElement submitField;

    public void launchLoginPage(String appUurl) {
        driver.get(appUurl);
    }

    public  void closeBrowser() {
        driver.quit();
    }

}
