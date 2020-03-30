import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage
{
    public static WebDriver driver;
    static General general=new General(driver);

    @FindBy(css = "label[data='forSomeone']")
    WebElement radioForSomeone;
    @FindBy(css = "label.ember-view.ui-field:nth-of-type(1) input.ember-view.ember-text-field")
    WebElement forWho;
    @FindBy(css = "label.ember-view.ui-field:nth-of-type(2) input.ember-view.ember-text-field")
    WebElement whoSend;
    @FindBy(css = "div.mx2.md1 label.ember-view.ui-field.ui-select div.chosen-container.chosen-container-single.ember-view.ember-chosenselect.form-control.chosen-rtl.chosen-rtl")
    WebElement eventList;
    @FindBy(css = "textarea[data-parsley-group='main']")
    WebElement blessing;
    @FindBy(name = "fileUpload")
    WebElement uploadPic;
    @FindBy(css = "div ul li[data-option-array-index='7']")    //choose wedding
            WebElement event;
    @FindBy(css = ".send-now")
    WebElement payNow;
    @FindBy(css = ".icon.icon-envelope")
    WebElement sendGiftByMail;
    @FindBy(css = ".form-control.input-theme")
    WebElement receiverMail;
    @FindBy(css = ".btn.btn-theme.btn-save")
    WebElement saveReceiverMail;
    @FindBy(css = ".ui-btn.orange.large ")
    WebElement submit;
    @FindBy(css = ".step-title.highlighted")
    WebElement receiver;

    @FindBy(css = ".receiver span.name")
    WebElement finalForWho;
    @FindBy(css = ".sender span.name")
    WebElement finalSender;
    @FindBy(css = ".card-text.cut-greeting")
    WebElement finalBlessing;


    public OrderPage (WebDriver driver){  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void fillOrderForm() throws Exception {
        general.radioButtonSelect(radioForSomeone);
        forWho.clear();
        forWho.sendKeys(general.readFromFile("forwho"));
        whoSend.clear();
        whoSend.sendKeys(general.readFromFile("sender"));
        blessing.clear();
        blessing.sendKeys(general.readFromFile("blessing"));
        uploadPic.sendKeys("C:\\pic\\pic.png");
        eventList.click();
        event.click();
        general.radioButtonSelect(payNow);
        sendGiftByMail.click();
        receiverMail.clear();
        receiverMail.sendKeys(general.readFromFile("receiverMail"));
        saveReceiverMail.click();
        submit.click();
    }
    public void fillInfoOnly() throws Exception {
        general.radioButtonSelect(radioForSomeone);
        forWho.clear();
        forWho.sendKeys(general.readFromFile("forwho"));
        whoSend.clear();
        whoSend.sendKeys(general.readFromFile("sender"));
        blessing.clear();
        blessing.sendKeys(general.readFromFile("blessing"));
        uploadPic.sendKeys("C:\\pic\\pic.png");
        eventList.click();
        event.click();
        general.radioButtonSelect(payNow);
        sendGiftByMail.click();
        receiverMail.clear();
        receiverMail.sendKeys(general.readFromFile("receiverMail"));
        saveReceiverMail.click();
    }
}
