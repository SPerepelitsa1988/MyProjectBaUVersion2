package ee.bauhof.toadisainer;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OpenScenePage {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @BeforeClass(alwaysRun = true)
        public void setUp() throws Exception {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            baseUrl = "http://toadisainer.bauhof.ee/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testOpenScenePage() throws Exception {
            // open | / |
            driver.get(baseUrl + "/");
            // click | css=div.navbar-item.lightblue > h3.ng-binding |
            driver.findElement(By.cssSelector("div.navbar-item.lightblue > h3.ng-binding")).click();
            // click | css=div.waves-effect > img |
            driver.findElement(By.cssSelector("div.waves-effect > img")).click();
            // click | //div[@id='design-menu-navbar']/div/div[5]/div/img |
            driver.findElement(By.xpath("//div[@id='design-menu-navbar']/div/div[5]/div/img")).click();
            // assertElementPresent | id=mainCanvas |
            Assert.assertTrue(isElementPresent(By.xpath("/html/body/div[1]/div[1]/span/div[1]/div[1]/div[2]/div[2]/img")));
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() throws Exception {
            driver.quit();
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }
