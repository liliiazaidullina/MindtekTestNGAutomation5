package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BlazeDemoHomepage;
import utilities.Configuration;
import utilities.Driver;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class BlazeDemoTest extends TestBase {
    //BlazeDemoTest class will inherit all methods from TestBase class.
    //methods being inherited: setUp(), tearDown();
    //attributes being inherited:driver;




    @Test
    public void test(){
        BlazeDemoHomepage blazeDemoHomepage = new BlazeDemoHomepage();
        driver.get(Configuration.getProperty("BlazeDemoURL"));
        blazeDemoHomepage.findFlightsButton.click();
        String expectedTitle = "BlazeDemo - reserve";
        String actualTitle =  driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

}
