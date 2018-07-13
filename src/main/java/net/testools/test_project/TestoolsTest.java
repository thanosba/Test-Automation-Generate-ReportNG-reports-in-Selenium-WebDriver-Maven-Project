/**
 * Created by thanos-imac on 13/7/18.
 */
package net.testools.test_project;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestoolsTest {
    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void setUp() {
        driver.get("https://www.linkedin.com/");
    }

    @Test
    public void simpleTest() throws InterruptedException {
        //Test1 Suite
        assertTrue(isElementPresent(By.xpath("//a[contains(text(),'Forgot password?')]")));
        assertTrue(isElementPresent(By.xpath("//h2[contains(.,'Be great at what you do')]")));
        assertTrue(isElementPresent(By.id("registration-submit")));
        driver.findElement(By.name("first")).sendKeys("test");
        driver.findElement(By.name("last")).sendKeys("test");
        driver.findElement(By.name("search")).click();
        Thread.sleep(3000);
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Find your connection on LinkedIn')]")));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Test Test profiles')]")));
        assertFalse(isElementPresent(By.xpath("//h9[contains(.,'fail assert')]")));
        waitForLoad(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public Boolean isElementPresent(By by) {
        if (driver.findElements(by).size() > 0)
            return true;
        else
            return false;
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(500);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

}
