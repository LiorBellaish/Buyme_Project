import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage
{
    public static WebDriver driver;

    @FindBy(css = "span.seperator-link")
    WebElement enterRegistrationButton;

    public HomePage (WebDriver driver){  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

}
