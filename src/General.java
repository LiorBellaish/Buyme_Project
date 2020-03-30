import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

public class General
{
    public static WebDriver driver;

    public General (WebDriver driver){  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public static String readFromFile(String keyData) throws Exception{
        File xmlFile=new File("C:\\Users\\טניה וליאור\\IdeaProjects\\Buyme_Project\\BuymeFile.xml");
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder=dbFactory.newDocumentBuilder();
        Document doc=  dbBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyData).item(0).getTextContent();
    }

    public static void radioButtonSelect(WebElement radioButton){
        boolean selectedValue=radioButton.isSelected();
        if(!selectedValue)
            radioButton.click();
    }

    public static void dropByValue(String value, WebElement dropDown){
        Select selectCombo=new Select(dropDown);
        selectCombo.selectByValue(value);
    }
    public static void dropByIndex(int index, WebElement dropDown){
        Select selectCombo=new Select(dropDown);
        selectCombo.selectByIndex(index);
    }

    public static void clickButton(WebElement element){
        Actions oneClick=new Actions(driver);
        oneClick.click(element);
        oneClick.build().perform();
    }

    public static String takeScreenShot(String ImagesPath,WebDriver driver) {   //take screenshot
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }



}
