package Application.Checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By Checkout = By.xpath("//*[@id=\"checkout\"]");
    public void ClickCheckout()  {
        driver.findElement(Checkout).click();
    }

    public void firstname( String firstname) {
        //driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys(firstname);
    }


    public void lastname( String lastname) {
        //driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys(lastname);
    }


    public void zipCode( String zipcode) {
        //driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys(zipcode);
    }

    private final By Continue = By.xpath("//*[@id=\"continue\"]");
    public void ClickContinue()  {
        driver.findElement(Continue).click();
    }


}
