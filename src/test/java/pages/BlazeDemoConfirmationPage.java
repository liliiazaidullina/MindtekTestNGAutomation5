package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazeDemoConfirmationPage {

    public BlazeDemoConfirmationPage (){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    } //we need constructor to create an object.

    @FindBy(xpath="//*[contains(text(),'Thank you for your purchase')]")
    public WebElement successMessage;

    @FindBy(xpath ="//*[contains(text(),'Card Number')]/following-sibling::td")
    public WebElement creditCard;

    @FindBy(xpath="//*[contains(text(),'Date')]/following-sibling::td")
    public WebElement bookedDate;





}
