package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SaucedemoHomePage;
import pages.SaucedemoLoginPage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.TestBase;

import java.util.List;

public class SaucedemoAppTest extends TestBase {

    @Test(groups = {"regression","smoke"})
    public void filterTest(){
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();

        driver.get(Configuration.getProperty("SaucedemoURL"));
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        SoftAssert softAssert = new SoftAssert();
    //    Assert.assertEquals(actualTitle, expectedTitle);
        softAssert.assertEquals(actualTitle, expectedTitle);

        saucedemoLoginPage.username.sendKeys(Configuration.getProperty("SaucedemoUsername"));
        saucedemoLoginPage.password.sendKeys(Configuration.getProperty("SaucedemoPassword"));
        saucedemoLoginPage.loginButton.click();

        BrowserUtils.selectDropdownByValue(saucedemoHomePage.filterDropdown, "lohi");


        List<WebElement> prices = saucedemoHomePage.prices;
        for(int i=0; i<prices.size()-1;i++){
            double tempPrice = Double.parseDouble(prices.get(i).getText().replaceAll("\\$",""));
            double tempPrice2 = Double.parseDouble(prices.get(i+1).getText().replaceAll("\\$",""));
           // Assert.assertTrue(tempPrice2>=tempPrice);
            softAssert.assertTrue(tempPrice2>=tempPrice);
        }

        softAssert.assertAll();




    }


}
