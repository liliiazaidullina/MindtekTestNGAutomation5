package tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StoreAppHomePage;
import pages.StoreAppRegisterPage;
import pages.StoreAppSignInPage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.DataUtils;
import utilities.TestBase;

public class StoreAppSighUpTest extends TestBase {

    String email;
    String password;

    @DataProvider(name = "signUpTestData")
    public static Object[][]testData(){
        return new Object[][]{
                {"John","Foe","1234567","1","1","2000","123 Washington ave","Chicago","13","12345","1234567890"},
                {"John","Mt.Doe","ghdfjgfdjnhgjdf","1","4","2020","33 Dee Rd", "New York","32","54321","96384638311"},
                {"Patel", "Hrash","fkldsfks","1","7","1950","4536 Degree Rode", "Chicago", "32","34567", "9876543210"}
        };
    }

    @Test(dataProvider = "signUpTestData", priority = 1, groups = {"regression","smoke"})
    public void signUpTest(String firstName, String lastName, String password, String day, String month, String year,
                           String address, String city, String state, String postcode, String mobileNumber){

        StoreAppHomePage storeAppHomePage = new StoreAppHomePage();
        StoreAppSignInPage storeAppSignInPage = new StoreAppSignInPage();
        StoreAppRegisterPage storeAppRegisterPage = new StoreAppRegisterPage();

        driver.get(Configuration.getProperty("StoreURL"));
        storeAppHomePage.signInButton.click();
        email = DataUtils.getRandomEmail();

        storeAppSignInPage.emailInputBox.sendKeys(email);
        storeAppSignInPage.createAccountButton.click();
        storeAppRegisterPage.firstName.sendKeys(firstName);
        storeAppRegisterPage.lastName.sendKeys(lastName);
        this.password=password;
        storeAppRegisterPage.password.sendKeys(password);
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.days, day); //dropdown, we created method in Browser Utils class.
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.months, month );
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.years,year);
        storeAppRegisterPage.address1.sendKeys(address);
        storeAppRegisterPage.cities.sendKeys(city);
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.states,state);
        storeAppRegisterPage.postcode.sendKeys(postcode);
        storeAppRegisterPage.mobile.sendKeys(mobileNumber);
        storeAppRegisterPage.registerButton.click();
        String expectedTitle = "My account - My Store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle,"Actual title: "+actualTitle+" didn't match with expected title: "+expectedTitle);
    }

    @Test(priority=2, groups = {"regression", "smoke"})
    public void signInTest(){

        StoreAppHomePage storeAppHomePage = new StoreAppHomePage();
        StoreAppSignInPage storeAppSignInPage = new StoreAppSignInPage();


        driver.get(Configuration.getProperty("StoreURL"));
        storeAppHomePage.signInButton.click();
        storeAppSignInPage.emailSignIn.sendKeys(email);
        storeAppSignInPage.passwordSignIn.sendKeys(password);
        storeAppSignInPage.signInButton.click();
        String expectedTitle = "My account - My Store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle,"Actual title: "+actualTitle+" didn't match with expected title: "+expectedTitle);



    }
}
