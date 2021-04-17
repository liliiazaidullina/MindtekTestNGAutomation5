package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BlazeDemoFlightsPage;
import pages.BlazeDemoHomepage;
import utilities.Configuration;
import utilities.TestBase;

import java.util.List;

public class BlazeDemoPriceTest extends TestBase {

    //This test will verify that all flight prices are below $1000
    @Test(groups ={"regression", "smoke"})
    public void priceTest(){
        BlazeDemoHomepage blazeDemoHomepage = new BlazeDemoHomepage();
        BlazeDemoFlightsPage blazeDemoFlightsPage = new BlazeDemoFlightsPage();

        driver.get(Configuration.getProperty("BlazeDemoURL"));
        blazeDemoHomepage.findFlightsButton.click();
        List <WebElement> prices = blazeDemoFlightsPage.prices;

        for(WebElement price : prices){
            String strPrice = price.getText().substring(1); //$472.56 ->472.56
            double doublePrice = Double.parseDouble(strPrice);
            Assert.assertTrue(doublePrice<1000); // 472.56<1000 ->true;
            System.out.println(doublePrice);
        }

    }

    //Verify that test result having only two Virgin America Flight
    //San Diego to New York that the text on the top have right city names




}
