package Application.Confirmation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    private WebDriver driver;
    private By orderSuccessMessage = By.id("order-success-msg");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderSuccessful() {
        return driver.findElement(orderSuccessMessage).isDisplayed();
    }

    private final By Finish = By.xpath(" //*[@id=\"finish\"]");
    public void ClickFinish()  {
        driver.findElement(Finish).click();
    }


}
