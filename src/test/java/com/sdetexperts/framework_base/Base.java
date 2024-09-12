package com.sdetexperts.framework_base;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Base {

    private static Base instance;
    private WebDriver driver;

    protected Base() {

    }

    public static Base getInstance() {
        if (instance == null) {
            synchronized (Base.class) {
                if (instance == null) {
                    instance = new Base();
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public WebDriver chooseBrowser(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            return driver;

        }
        else if(browser.equalsIgnoreCase("ie")) {
           driver = new InternetExplorerDriver();
           return driver;

        }
        else if(browser.equalsIgnoreCase("safari")) {
           driver =  new SafariDriver();
           return driver;
        }
        else{
            driver = new  ChromeDriver();
            return driver;
        }

    }

    public String readDataFromConfigFile(String value) {
        try {
            Properties prp= new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\config.properties");
            prp.load(fis);
            return prp.getProperty(value);
        } catch (Exception e) {
            System.out.println("Unable to read configure file");
            return "";
        }
    }

    public void clickAnElement (String objectNameString, WebElement objectName) {
        try {
            objectName.click();
            System.out.println("Verified:" +objectNameString+ "is Clicled");
        }
        catch (Exception e) {
            System.out.println("The " +objectNameString+ "is not Displayed to click.");
            Assert.fail("The " +objectNameString+ "is not Displayed to click.");
        }
    }

    public String getTitleOfPage() {
        return driver.getTitle();
    }

    public void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public void verifyAlertTextandClose(String texttobeverified) {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText =alert.getText();
            alert.accept();
            alertText.concat(texttobeverified);
            try {
                System.out.println("The text " +texttobeverified+ "in the Alert is verified");
            }
            catch(Exception e) {
                System.out.println("The Given text" +texttobeverified+ "is not matched in the Alert");
            }
        }
        catch (NoAlertPresentException e){
            System.out.println("The Alert " +texttobeverified+ "is not present to Verify");
        }
    }

    public void verifyAlertTextContainsExpectedTextandClose(String texttobeverified) {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText =alert.getText();
            alert.accept();
            alertText.contains(texttobeverified);
            try {
                System.out.println("The text " +texttobeverified+ "in the Alert is verified");
            }
            catch(Exception e) {
                System.out.println("The Given text" +texttobeverified+ "is not matched in the Alert");
            }
        }
        catch (NoAlertPresentException e){
            System.out.println("The Alert " +texttobeverified+ "is not present to Verify");
        }
    }

    public void launchBrowser(){

        driver.get(readDataFromConfigFile("testURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void startLine(Method methodName) {
        System.out.println(" ************" +methodName.getName() +"Test Starting ************");
    }

    public void endtLine(Method methodName) {
        System.out.println(" ************" +methodName.getName() +"Test Ended  ************");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void enterText(String objectNameStr , WebElement objectName, String value) {
        try {
            objectName.clear();
            objectName.sendKeys(value);
            System.out.println("verified " +objectName.getAttribute("value") + "is entered in" +objectNameStr);
        }
        catch (Exception e) {
            System.out.println( objectNameStr + "Not Displayed");
        }
    }

    public void verifyTextEquals (String actualString, String expectedString) {

        if (actualString.equals(expectedString)) {
            System.out.println("Verified: The expected string is matched with actual");
        }
        else {
            System.out.println("The expected is not matched with Actual String .");
        }
    }

    public void verifyTextContains (String actualString, String expectedString) {

        if (actualString.toLowerCase().contains(expectedString.toLowerCase())) {
            System.out.println("Verified: The expected string is Present in Actual String");
        }
        else {
            System.out.println("The expected is not Present in Actual String .");
        }
    }

    public void sleep(long millis) throws InterruptedException{
        Thread.sleep(millis);
    }

    public void selectFormDropDownByIndex(String objectNameString, WebElement objectName, int valueIndex){
        try {
            Select dropDown = new Select(objectName);
            dropDown.deselectAll();
            dropDown.selectByIndex(valueIndex);
            System.out.println("verified:" +valueIndex+ "selected from dropdown");
        }
        catch(Exception e) {
            System.out.println( +valueIndex+ "not present in dropdown");
        }
    }

    public static void selectTextByVisibleText(String objectNameString, WebElement objectName, String visibleText) {

        try {
            Select select = new Select(objectName);
            select.selectByVisibleText(visibleText);
            System.out.println("verified:" +visibleText+ "selected from dropdown");
        }
        catch(Exception e) {
            System.out.println("The" + visibleText + "not present in dropdown");
        }
    }

    public void selectFormDropDownList(String objNameStr, WebElement objName, String valueToSelect) {
        try	{
            Thread.sleep(2000L);
            Select dropDown = new Select(objName);


            String selected = dropDown.getFirstSelectedOption().getText();
            if (!selected.equalsIgnoreCase(valueToSelect)) {
                List<WebElement> Options = dropDown.getOptions();
                for (WebElement option : Options) {
                    System.out.println("Option Selected -> " + option.getText());
                    if (option.getText().equalsIgnoreCase(valueToSelect)) {
                        option.click();
                        break;
                    }
                }
            }

            System.out.println("Selecting Value '" + valueToSelect + "' from dropdown list '" + objNameStr + "' is successful");
        }
        catch (Exception e) {
            System.out.println("Selecting Value '" + valueToSelect + "' from dropdown list '" + objNameStr + "' Failed" + e);
        }
    }

    public WebDriver getHandleToWindow(String title) {

        WebDriver popup = null;
        Set<String> windowIterator = driver.getWindowHandles();
        System.out.println("No of Windows" +windowIterator.size());
        for (String s : windowIterator) {
            String windowHandle =s;
            popup = driver.switchTo().window(windowHandle);
            if (popup.getTitle().equalsIgnoreCase(title)){
                System.out.println("The Selected window Title" + popup.getTitle());
                return popup;
            }
        }
        System.out.println("The Window Title:" + popup.getTitle());
        return popup;
    }

    public void switchAndHandleMultipleWindows(){

        String parentWindowHandle = driver.getWindowHandle();
        WebDriver popup =null;
        for(String windowHanle: driver.getWindowHandles()) {
            popup= driver.switchTo().window(windowHanle);
            System.out.println("The visible window Title " + popup.getTitle());
        }
        driver.switchTo().window(parentWindowHandle);
    }

    public void switchWindows(String windowOption)    {

        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        System.out.println("Parent URL -> " + driver.getCurrentUrl());
        for (String windowHandle : handles) {
            if (windowOption.equalsIgnoreCase("Child")) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("Child URL -> " + driver.getCurrentUrl());
                    break;
                }
            } else if (windowOption.equalsIgnoreCase("Parent")) {
                driver.switchTo().window(parentWindow);
            }
        }
    }

    public static String getRandomCharNumbers() {

        final String alphanumeric = "ABCDEFG0123456789";
        final int n= alphanumeric.length();
        Random r = new Random();
        String randomString  = Character.toString(alphanumeric.charAt(r.nextInt(n)));
        return randomString;
    }

    public String getCurrentPageURL () {
        return driver.getCurrentUrl();
    }

    public void navigateToPageUsingURL(String currentPageurl) {
        driver.get(readDataFromConfigFile("testURL") +currentPageurl);
    }

    public int getTotalWaitTimeInSeconds() {
        return Integer.parseInt(readDataFromConfigFile("ElementLoad_WaitTime"));
    }

    public void explicitlyWaitForAnElement(WebElement objectName, int waitTimeInSeconds) {

        WebDriverWait webdriverWaitForTask = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        try {
            webdriverWaitForTask.until(ExpectedConditions.elementToBeClickable(objectName));
        } catch(Exception e) {
            System.out.println("could not load the element " +objectName + "in this waitime");
        }
    }

    public void scrollDown() {
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        jse.executeScript("scroll(0,250);");
    }

    public void scrollUp() {
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        jse.executeScript("scroll(0,-250);");
    }

    public void enterValueByJavaScript(String objNameStr, WebElement objName, String valueToEnter)  {

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        try   {
            executor.executeScript("arguments[0].value='" + valueToEnter + "';", new Object[] { objName });
            System.out.println("Entering value '" + valueToEnter + "' in '" + objNameStr + " is successfull");
        } catch (Exception ex) {
            scrollintoview(objName);
            executor.executeScript("arguments[0].value='" + valueToEnter + "';", new Object[] { objName });
            System.out.println("Entering value '" + valueToEnter + "' in '" + objNameStr + " is successfull");
        }
    }

    public void scrollintoview(WebElement e) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new Object[] { e });
        } catch (Exception ex) {
            System.out.println("Failed in Scroll to View: " + e.toString());
        }
    }

    public void deSelectAnWebElement (String visibleText, WebElement objectElement) {

        try {
            Select select = new Select(objectElement);
            select.deselectByVisibleText(visibleText);
            System.out.println("DeSelected the " +visibleText );
        }
        catch (Exception e){
            System.out.println("Not Selected the Visisbile Text");
        }
    }

    public void isTabEnabled(String objectNameString, WebElement objectName) {
        try {
            WebElement link =objectName ;
            String linkClass = link.getAttribute("class");
            if ("active".equals(linkClass)) {
                System.out.println("The Link/Tab" +objectNameString + "appeard to be Active or Enabled State");
            }
            else
                System.out.println("The Link/Tab" +objectNameString + "appeard to In- Active or Disabled State");
        }
        catch (NoSuchElementException e) {
            System.out.println("The Exception is:" +e.getMessage());
        }
    }

    public static void isLinkDisabled (String objectNameString, WebElement objectName) throws Exception {

        try{
            WebElement link =objectName ;
            String linkClass =link.getAttribute("class");
            if ("inactive".equals(linkClass))
                System.out.println("The link appears to be Inactive or Disabled Mode");
            else
                System.out.println("The Link appeard to be Active or Enabled Mode");
        } catch (NoSuchElementException e) {
            System.out.println("The Exception is :" +e.getMessage());
        }
    }

    public void doubleClickAnElement (String objectNameString , WebElement objectName){

        try {
            Actions action = new Actions(driver).doubleClick(objectName);
            action.build().perform();
            System.out.println("Verified" +objectNameString+ "is DoubleClicked");
        }
        catch (NoSuchElementException e) {
            System.out.println("The " +objectNameString+ "not displayed "+ e.getMessage());
        }
    }

    public void switchToiFrame(String iframeObjectString, WebElement iframeObjectName){

        try {
            driver.switchTo().frame(iframeObjectName);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println("Verfied:" + iframeObjectString + "is clicked");
        }
        catch(Exception e) {
            System.out.println("no such element :" +iframeObjectString +"is not displayed"+ e.getMessage());
        }
    }

    public void switchToFrame(String frameNameString, WebElement frameName) {
        try {
            if (frameNameString.equalsIgnoreCase("Default")) {
                driver.switchTo().defaultContent();
                System.out.println("Switch Frame to default Content");
            } else if (frameNameString.equalsIgnoreCase("Google Re-captcha")) {
                driver.switchTo().frame(frameName);
                System.out.println("Switch Frame is successful");
            }
        } catch (Exception e) {
            System.out.println("Function Switch frame failed" + e.getMessage());
        }
    }

    public boolean isElementPresent(String objectNameString, WebElement objectName){

        Boolean flag =false;

        if (objectName.isDisplayed()){
            System.out.println("verified" +objectNameString + "is Displayed");
        }else System.out.println(objectNameString +"not displayed");
        return flag;
    }

    public void uploadFile(String objectNameString, WebElement objectName, String file){
        try {
            objectName.sendKeys(file);
            System.out.println("Verified" +file + "is entered in " +objectNameString);
        }catch (Exception e) {
            System.out.println(objectNameString +"is not displayed" + e.getMessage());
        }
    }

    public void verfiyWindowTitle (String expectedTitle){
        try {
            //Assert.assertEquals(expectedTitle,getTitleOfPage());
            /*getTitleOfPage().equalsIgnoreCase(expectedTitle);*/
            System.out.println("The Actual tile " +getTitleOfPage()+ "matched with Expected");
        } catch (Exception e) {
            System.out.println("The Expected title" + expectedTitle+ "is not matched with the Actual and the Actual one is" + getTitleOfPage());
        }
    }

    public String getAlertMessage(){

        String alertText ="";
        if (isAlertPresent()){
            alertText=driver.switchTo().alert().getText();
        } else {
            alertText = "NO ALERT Is present";
        }
        return alertText;
    }

    public void acceptTheAlertBox(){
        driver.switchTo().alert().accept();
        System.out.println("Alert is Accepted");
    }

    public void dismissAlertBox(){
        driver.switchTo().alert().dismiss();
        System.out.println("Alert is dissmissed");
    }

    public boolean isAlertPresent () {

        try {
            driver.switchTo().alert();
            System.out.println("Alert is displayed");
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
