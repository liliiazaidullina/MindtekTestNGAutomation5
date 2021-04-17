package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazeDemoPurchasePage {
    public BlazeDemoPurchasePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[contains(text(),'Price')]")
    public WebElement priceOnPurchasePage;

    @FindBy(id="inputName")
    public WebElement firstName;

    @FindBy(id = "address")
    public WebElement address;

    @FindBy(id="city")
    public WebElement city;

    @FindBy(id="state")
    public WebElement state;

    @FindBy(id="zipCode")
    public WebElement zipCode;

    @FindBy(id="cardType")
    public WebElement cardType;

    @FindBy(id="creditCardNumber")
    public WebElement creditCardNumber;

    @FindBy(id="creditCardMonth")
    public WebElement creditCardMonth;

    @FindBy(id="creditCardYear")
    public WebElement creditCardYear;

    @FindBy(id="nameOnCard")
    public WebElement nameOnCard;

    @FindBy(xpath="//input[@type='submit']")
    public WebElement submitButton;











}
