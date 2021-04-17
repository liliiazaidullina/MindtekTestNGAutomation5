package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BrowserUtils {

    /**
     * This method will accept webelement of dropdown
     * and value of dropdown and will select that value
     * in dropdown
     * Ex:
     *      .selectDropdownByValue(element, "1");
     */

    public static void selectDropdownByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);


    }


}
