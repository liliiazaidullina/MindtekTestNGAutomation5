package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class StoreAppContactUsPage {


    public StoreAppContactUsPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="email")
    public WebElement emailAddress;

    @FindBy(id ="id_contact")
    public WebElement subjectHeading;

    @FindBy(id ="message")
    public WebElement message;

    @FindBy(id="submitMessage")
    public WebElement sendButton;

    @FindBy(xpath = "//*[@class=\"alert alert-success\"]")
    public WebElement successMessage;

    @FindBy(xpath = "//*[@class=\"alert alert-danger\"]//li")
    public WebElement errorMessage;



}
