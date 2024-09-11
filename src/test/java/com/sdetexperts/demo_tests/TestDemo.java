package com.sdetexperts.demo_tests;

import com.sdetexperts.framework_base.Base;
import com.sdetexperts.reportutils.ReportBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class TestDemo extends ReportBuilder {
    WebDriver driver;

    @Test
    public void testDemo() {
        driver = Base.getInstance().getDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
