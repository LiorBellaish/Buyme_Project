import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Loged
{
    public static WebDriver driver;
    static General general=new General(driver);

    @FindBy(css = "form div.chosen-container.chosen-container-single.form-control.dib:nth-of-type(1)")
    WebElement priceList;
    @FindBy(css = "li[data-option-array-index='2']")
    WebElement price;
    @FindBy(css = "form div.chosen-container.chosen-container-single.form-control.dib:nth-of-type(2)")
    WebElement regionList;
    @FindBy(css = "li[data-option-array-index='4']")
    WebElement region;
    @FindBy(css = "form div.chosen-container.chosen-container-single.form-control.dib:nth-of-type(3)")
    WebElement categoryList;
    @FindBy(css = "li[data-option-array-index='14']")
    WebElement category;
    @FindBy(css = ".ui-btn.search.ember-view")
    WebElement findGiftSearchButton;

    public Loged (WebDriver driver){  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void search() throws InterruptedException {
        priceList.click();
        price.click();
        regionList.click();
        region.click();
        categoryList.click();
        category.click();
        findGiftSearchButton.click();
        Thread.sleep(2000);
    }
    public static void dropByValue(String value, WebElement dropDown){
        Select selectCombo=new Select(dropDown);
        selectCombo.selectByValue(value);
    }


}
