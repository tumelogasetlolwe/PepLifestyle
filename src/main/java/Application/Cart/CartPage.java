package Application.Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }



    private final By Cart = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    public void ClickCart()  {
        driver.findElement(Cart).click();
    }

}
