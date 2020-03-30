import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseGift
{
    public static WebDriver driver;
    static General general=new General(driver);

    @FindBy(css = "a[href='https://buyme.co.il/supplier/777671']")
    WebElement chooseMatzmanGift;
    @FindBy(css = "input.form-control.input-theme.input-cash.ember-view.ember-text-field")
    WebElement cashInput;
    @FindBy(css = "div.card-bottom.clearfix button.btn.btn-theme")
    WebElement submitToOrder;

    public ChooseGift (WebDriver driver){  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void chooseBusiness() throws Exception {
        chooseMatzmanGift.click();
        cashInput.sendKeys(general.readFromFile("cash"));
        submitToOrder.click();
    }

}
