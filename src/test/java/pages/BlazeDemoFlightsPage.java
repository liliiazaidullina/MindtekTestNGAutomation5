package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class BlazeDemoFlightsPage {

    public BlazeDemoFlightsPage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//table[@class='table']//td[6]")
    public List<WebElement> prices;




}
