import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login
{
    public static WebDriver driver;
    static General general=new General(driver);

    private static ExtentTest liorTests;
    String imagePath="C:\\Users\\טניה וליאור\\IdeaProjects\\Buyme_Project\\screenshots";


    @FindBy(css = "span.text-btn")
    WebElement RegistrationButton;
    @FindBy(css = "label input[data-parsley-type='email']")
    WebElement email;
    @FindBy(css = "input[type='password']")
    WebElement password;
    @FindBy(css = ".ui-btn.orange.large")
    WebElement submit;

    public Login (WebDriver driver){  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void loginSite() throws Exception
    {
        email.sendKeys(general.readFromFile("email"));
        password.sendKeys(general.readFromFile("password"));
        submit.click();
        Thread.sleep(1500);
    }
}
