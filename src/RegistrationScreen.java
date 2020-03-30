import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationScreen
{
    public static WebDriver driver;
    static General general=new General(driver);

    @FindBy(css= "input[type='text']:nth-of-type(1)")
    WebElement firstName;
    @FindBy(css = "input[type='email']:nth-of-type(1)")
    WebElement email;
    @FindBy(css = "input[type='password']:nth-of-type(1)")
    WebElement password;
    @FindBy(css = "input[data-parsley-equalto='#valPass']")
    WebElement passwordConfirm;
    @FindBy(css = ".ui-btn.orange.large")
    WebElement submitReg;

    public RegistrationScreen (WebDriver driver){  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void Registration() throws Exception {
        firstName.sendKeys(general.readFromFile("firstName"));
        email.sendKeys(general.readFromFile("email"));
        password.sendKeys(general.readFromFile("password"));
        passwordConfirm.sendKeys(general.readFromFile("password"));
        submitReg.click();
    }
}
