import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Extras {
    public static WebDriver driver;
    static General general = new General(driver);
    HomePage homePage = new HomePage(driver);
    Login login = new Login(driver);
    OrderPage orderPage = new OrderPage(driver);

    @FindBy(css = "div.field:nth-of-type(1) li.parsley-required")
    WebElement mailErrorMessage;
    @FindBy(css = "div.field:nth-of-type(2) li.parsley-required")
    WebElement passwordErrorMessage;
    @FindBy(css = ".bounce1")
    WebElement loadingDot;


    public Extras(WebDriver driver) throws Exception {  //constructor
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Object loadingDots() throws InterruptedException {
        try {
            driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
            driver.get(general.readFromFile("url"));
            return "";
        }
        catch (Exception e) {
            Thread.sleep(3000);
            Object dot = loadingDot.getSize();
            return dot;
        }
    }
}