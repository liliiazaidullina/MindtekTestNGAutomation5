package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Configuration;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class TestNewUserButton extends TestBase {

    @Test //
    public void TO01() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Configuration.getProperty("SeleniumEasyURL"));

        driver.findElement(By.id("save")).click();
        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading")));
        String expectedFirstName = driver.findElement(By.xpath("//*[contains(text(),'First Name')]")).getText();

        driver.findElement(By.id("save")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading")));
        String actualFirstName = driver.findElement(By.xpath("//*[contains(text(),'First Name')]")).getText();

        Assert.assertNotEquals(actualFirstName,expectedFirstName);

    }
}
