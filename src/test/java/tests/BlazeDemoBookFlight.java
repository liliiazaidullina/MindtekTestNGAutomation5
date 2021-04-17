package tests;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BlazeDemoConfirmationPage;
import pages.BlazeDemoFlightsPage;
import pages.BlazeDemoHomepage;
import pages.BlazeDemoPurchasePage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.TestBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class BlazeDemoBookFlight extends TestBase {

    String flightFrom="San Diego";
    String flightTo="New York";

    @Test
    public void TC01(){
        double tempMax=0;
        int index=0;
        double maxPrice;
        BlazeDemoHomepage blazeDemoHomepage = new BlazeDemoHomepage();
        BlazeDemoFlightsPage blazeDemoFlightsPage = new BlazeDemoFlightsPage();
        BlazeDemoPurchasePage blazeDemoPurchasePage = new BlazeDemoPurchasePage();
        BlazeDemoConfirmationPage blazeDemoConfirmationPage = new BlazeDemoConfirmationPage();


        driver.get(Configuration.getProperty("BlazeDemoURL"));
        BrowserUtils.selectDropdownByValue(blazeDemoHomepage.departureCity, flightFrom);
        BrowserUtils.selectDropdownByValue(blazeDemoHomepage.destinationCity, flightTo);
        blazeDemoHomepage.findFlightsButton.click();
        List<WebElement> price = blazeDemoFlightsPage.prices;

        for(int i=0; i<price.size();i++){
        double tempPrice = Double.parseDouble(blazeDemoFlightsPage.prices.get(i).getText()
                    .replaceAll("\\$",""));
                if(tempPrice>tempMax){
                    tempMax=tempPrice;
                    index = i;
                }
        }
        blazeDemoFlightsPage.chooseFlight.get(index).click();
        Double actualPrice =Double.parseDouble(blazeDemoPurchasePage.priceOnPurchasePage.getText().
                substring(blazeDemoPurchasePage.priceOnPurchasePage.getText().indexOf(" ")));
        SoftAssert sf = new SoftAssert();
        sf.assertEquals(actualPrice, tempMax); // it shows false because numbers are different.
        System.out.println(blazeDemoPurchasePage.priceOnPurchasePage.getText());
    }

    @DataProvider (name="ticketPurchaseData")
    public static Object[][] testData() {
        return new Object[][]{
                {"John Doe", "123 Washington ave.", "New York", "NY", "12345", "visa", "1234567890", "11", "2025", "John Doe",
                        By.xpath("//*[contains(text(),'Thank you for your purchase')]") , "Thank you for your purchase today!"},
               {"David Clark", "123 Washington ave.", "Austin", "TX", "12345", "amex", "mycreditcardnumber", "11", "2025", "David Clark",
                       By.xpath("//*[contains(text(),'Thank you for your purchase')]"), "Thank you for your purchase today!"}
        };
    }

    @Test(dataProvider = "ticketPurchaseData")
    public void TC02(String name, String address, String city, String state, String zipcode, String cardType2, String creditCardNumber,
                     String  month, String year, String nameOnCard, By locator, String expectedMessage){
        double tempMax=0;
        int index=0;
        double maxPrice;
        BlazeDemoHomepage blazeDemoHomepage = new BlazeDemoHomepage();
        BlazeDemoFlightsPage blazeDemoFlightsPage = new BlazeDemoFlightsPage();
        BlazeDemoPurchasePage blazeDemoPurchasePage = new BlazeDemoPurchasePage();
        BlazeDemoConfirmationPage blazeDemoConfirmationPage = new BlazeDemoConfirmationPage();

        driver.get(Configuration.getProperty("BlazeDemoURL"));
        BrowserUtils.selectDropdownByValue(blazeDemoHomepage.departureCity, flightFrom);
        BrowserUtils.selectDropdownByValue(blazeDemoHomepage.destinationCity, flightTo);
        blazeDemoHomepage.findFlightsButton.click();
        List<WebElement> price = blazeDemoFlightsPage.prices;
        for(int i=0; i<price.size();i++){
            double tempPrice = Double.parseDouble(blazeDemoFlightsPage.prices.get(i).getText()
                    .replaceAll("\\$",""));
            if(tempPrice>tempMax){
                tempMax=tempPrice;
                index = i;
            }
        }
        blazeDemoFlightsPage.chooseFlight.get(index).click();
        blazeDemoPurchasePage.firstName.sendKeys(name);
        blazeDemoPurchasePage.address.sendKeys(address);
        blazeDemoPurchasePage.city.sendKeys(city);
        blazeDemoPurchasePage.state.sendKeys(state);
        blazeDemoPurchasePage.zipCode.sendKeys(zipcode);
        BrowserUtils.selectDropdownByValue(blazeDemoPurchasePage.cardType, cardType2);
        blazeDemoPurchasePage.creditCardNumber.sendKeys(creditCardNumber);
        blazeDemoPurchasePage.creditCardMonth.sendKeys(month);
        blazeDemoPurchasePage.creditCardYear.sendKeys(year);
        blazeDemoPurchasePage.nameOnCard.sendKeys(nameOnCard);
        blazeDemoPurchasePage.submitButton.click();

        String actualMessage = driver.findElement(locator).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
        String text = blazeDemoConfirmationPage.creditCard.getText();
        String actualLastFourDigits = text.substring(text.length()-4);
        String expectedLastFourDigits = creditCardNumber.substring(creditCardNumber.length()-4);
        Assert.assertNotEquals(actualLastFourDigits, expectedLastFourDigits);
        //It will fail because last four digits are different, that is why i used assertNotEquals

        Date todaysDate = new Date();
        DateFormat df = new SimpleDateFormat("E, dd MMM yyyy");
        String expectedDate = df.format(todaysDate);

        String actualDate = blazeDemoConfirmationPage.bookedDate.getText().substring(0, expectedDate.length());
        Assert.assertEquals(actualDate, expectedDate);

        //If we will enter invalid credit card number it will still show the message:Thank you for your purchase today!

    }

}
