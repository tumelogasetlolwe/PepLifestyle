package Application;

import Application.Cart.CartPage;
import Application.Checkout.CheckoutPage;
import Application.Confirmation.ConfirmationPage;
import Application.Products.ProductsPage;
import Utilities.Credential;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.poiji.bind.Poiji;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import Application.Login.LoginPage;
import Utilities.BrowserUtils;
import com.relevantcodes.extentreports.LogStatus;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ApplicationTest {
    private WebDriver driver;
    ExtentReports reports = new ExtentReports();
    ExtentTest test ;
    ExtentSparkReporter spark;

    @BeforeTest
    @Parameters({"browser", "url"})
    public void setUp(String browser, String url) {


        switch(browser) {

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
                //System.setProperty("webdriver.chrome.driver", "C:\\Users\\tumelog\\Desktop\\chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }
        if(url != null && !url.isEmpty()){
            driver.get(url);
        }
        spark = new ExtentSparkReporter("src/main/java/Reporting/reports.html");
        reports.attachReporter(spark);

    }




    @Test
        @Parameters({"url"})
    public void execute( String url){
        ClassLoader classLoader = ApplicationTest.class.getClassLoader();
        URL resource = classLoader.getResource("Credentials.xlsx");
        assert resource != null;

        List<Credential> credentials = Poiji.fromExcel(new File(resource.getPath()), Credential.class);
        try {

            ProductsPage productsPage = new ProductsPage(driver);
            CartPage cartPage = new CartPage(driver);
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            ConfirmationPage confirmationPage = new ConfirmationPage(driver);
            Thread.sleep(4000);
            driver.manage().window().maximize();
            //LoginPage.LoadWebsite(driver, url);
            String pageTitle = driver.getTitle();



           // File DCp = new File("src/main/java/utilitis/OverViewPage.png");
            test = reports.createTest("Homepage", "Verify Homepage");

                //By homepageLocator = By.xpath("//*[@id=\"logkin-button\"]");
            WebElement SwayName = driver.findElement(By.className("form_group"));
            WebElement credit = driver.findElement(RelativeLocator.with(By.className("login_logo")).above(SwayName));
           // WebElement PR = driver.findElement(By.cssSelector("#header_container > div.primary_header > div.header_label > div"));
            String Webname = "Swag Labhhs";
            if(Webname.matches(credit.getText())) {
                test.log(Status.PASS, "Welcome to the home page");
            }else {
                test.log(Status.FAIL, "it FAILED to locate the home page");
            }
                LoginPage loginPage = new LoginPage(driver);
           loginPage.username(credentials.get(0).getUserName());

            loginPage.password(credentials.get(0).getPassword());

            loginPage.clickLoginButton();
            test = reports.createTest("Products", "Verify Products");
            WebElement PR = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
            if(PR.getText().equals("Produczzts")){
                test.log(Status.PASS,"it is on the Product page");
            }else{
                test.log(Status.FAIL,"it is not in the Product page");
            }



            String expectedTitle = "Swag Las"; // Modify this to match your application's title after login


            String screenshotPath =  ("C:\\Users\\tumelog\\Desktop\\UITestAutomationAssessmentsrc\\main\\java\\Reporting\\Screenshorts");
            Thread.sleep(2000);

           // test = reports.createTest("Purchase Specified Onsie", "Verify purchase functionality");
            //test.log(Status.PASS, "Test passed");
            /*productsPage.SelectLabsOnesie();
            Thread.sleep(2000);
            productsPage.ClickAddToCart();
            Thread.sleep(2000);
            cartPage.ClickCart();
            Thread.sleep(2000);

checkoutPage.ClickCheckout();
            Thread.sleep(2000);
            checkoutPage.firstname("TeeBabe");
            checkoutPage.lastname("DladlaTee");
            checkoutPage.zipCode("2017");
            Thread.sleep(2000);
            checkoutPage.ClickContinue();
            confirmationPage.ClickFinish();
            Thread.sleep(3000); */




        }catch (Exception e) {
            System.out.println(e.getMessage());

        }}


    @AfterMethod
    public void tearDwn() throws IOException {

        reports.flush();
        File html = new File("src/main/java/Reporting/reports.html");
        if(html.exists()&& Desktop.isDesktopSupported()){
            Desktop.getDesktop().browse(html.toURI());
        }else {
            System.out.println("Desktop is not supported");
        }
        driver.quit();
    }
}
