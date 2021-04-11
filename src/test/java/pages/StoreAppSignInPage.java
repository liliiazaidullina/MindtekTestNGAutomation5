package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppSignInPage {

    public StoreAppSignInPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id ="email_create")
    public WebElement emailInputBox;

    @FindBy(id ="SubmitCreate")
    public WebElement createAccountButton;

    @FindBy(id="email")
    public WebElement emailSignIn;

    @FindBy(id="passwd")
    public WebElement passwordSignIn;

    @FindBy(id="SubmitLogin")
    public WebElement signInButton;




}
