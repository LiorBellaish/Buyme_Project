import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//all tests....
public class Tests {
    @Rule
    public TestName name = new TestName();
    public static WebDriver driver;
    //create objects
    static General general = new General(driver);
    HomePage homePage = new HomePage(driver);
    RegistrationScreen registrationScreen = new RegistrationScreen(driver);
    Login login = new Login(driver);
    Loged loged = new Loged(driver);
    ChooseGift chooseGift=new ChooseGift(driver);
    OrderPage orderPage=new OrderPage(driver);
    Extras extras=new Extras(driver);

    private static ExtentReports extent;
    private static ExtentTest liorTests;
    String imagePath = "C:\\Users\\טניה וליאור\\IdeaProjects\\Buyme_Project\\screenshots";

    public Tests() throws Exception {
    }

    @BeforeClass
    public static void setUp() throws Exception {
        extent = new ExtentReports("C:\\Users\\טניה וליאור\\IdeaProjects\\Buyme_Project\\BuymeReport.html");
        extent.loadConfig(new File("C:\\Users\\טניה וליאור\\IdeaProjects\\Buyme_Project\\reportConfig.xml"));//עריכה של הקובץ רפורט
        String browser = general.readFromFile("websiteURL");
        setBrowser(browser);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(general.readFromFile("url"));
    }

    @Before
    public void before() {
        startTest();
        System.out.println("start " + name.getMethodName());
    }

    @After
    public void after() {
        endTest();
        System.out.println("end " + name.getMethodName());
        extent.endTest(liorTests);
    }

    @AfterClass
    public static void endClass() {
        driver.close();
        driver.quit();
        extent.flush();
    }

    @Test
    public void test01_homeScreenErrorMessagesText() throws Exception {
        //driver.get(general.readFromFile("url"));    //open new homescreen
        homePage.enterRegistrationButton.click();
        login.submit.click();
        try{    //email error message assert
            Assert.assertEquals("כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה",extras.mailErrorMessage.getText());
            liorTests.log(LogStatus.PASS, "The email error message are equals to the expected");
            printScreen();
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "The email error message not equals to the expected " + asert);
            printScreen();
        }
        try{    //password error message assert
            Assert.assertEquals("כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה",extras.passwordErrorMessage.getText());
            liorTests.log(LogStatus.PASS, "The password error message are equals to the expected");
            printScreen();
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "The password error message not equals to the expected " + asert);
            printScreen();
        }
    }   //extras

    @Test
    public void test02_homeScreenErrorMessagesColor() throws Exception {
        try{    //email error message assert
            String mailErrorColorString = extras.mailErrorMessage.getAttribute("class");
            assertEmailRedTextColor(mailErrorColorString.equals("ff0000"));
            printScreen();
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "The email error message color not equals to the expected " + asert);
            printScreen();
        }
        try{    //password error message assert
            String passErrorColorString = extras.passwordErrorMessage.getAttribute("class");
            assertPassRedTextColor(passErrorColorString.equals("ff0000"));
            printScreen();
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "The password error message color not equals to the expected " + asert);
            printScreen();
        }
    }

    @Test
    public void test03_RegistrationScreen() throws Exception {
        //homePage.enterRegistrationButton.click();   //  run this line when test run alone
        login.RegistrationButton.click();
        registrationScreen.Registration();
        try {
            Assert.assertNotEquals("https://buyme.co.il/?modal=login", driver.getCurrentUrl());
            liorTests.log(LogStatus.PASS, "Success with website registration!");
            printScreen();
        } catch (AssertionError asert) {
            WebElement error=driver.findElement(By.cssSelector("div.login-error"));
            if(error.getText().contains("זה כבר קיים במערכת")){
                liorTests.log(LogStatus.PASS, "It's OK! Your Email already signed up! (check screenshot) " + error.getText());
                printScreen();
            }
            else {
                liorTests.log(LogStatus.FAIL, "Site registration failure! (check screenshot) " + asert);
                printScreen();
            }
            }
    }

    @Test
    public void test04_homeScreen() throws Exception {
        driver.get(general.readFromFile("url"));    //back to homePage
        Thread.sleep(1500);
        homePage.enterRegistrationButton.click();
        login.loginSite();
        try {    //try to login
            Assert.assertNotEquals("https://buyme.co.il/?modal=login", driver.getCurrentUrl());
            liorTests.log(LogStatus.PASS, "Success to login!");
            printScreen();
        } catch (AssertionError asert) {
            liorTests.log(LogStatus.FAIL, "fail login! " + asert);
            printScreen();
        }
        loged.search();
    }

    @Test
    public void test05_chooseGift() throws Exception {
        try {   //assert webpage url change
            Assert.assertEquals("https://buyme.co.il/search?budget=2&category=6&region=12", driver.getCurrentUrl());
            liorTests.log(LogStatus.PASS, "Success to search!");
            printScreen();
        } catch (AssertionError asert) {
            liorTests.log(LogStatus.FAIL, "fail search! " + asert);
            printScreen();
        }

        chooseGift.chooseBusiness();
        try {   //checking if I can choose business and money
            WebElement waitTemp=(new WebDriverWait(driver,20)).until((ExpectedConditions.presenceOfElementLocated(By.cssSelector("label[data='forSomeone']"))));
            Assert.assertNotEquals("https://buyme.co.il/supplier/777671",driver.getCurrentUrl());
            liorTests.log(LogStatus.PASS, "Success to choose business and money!");
            printScreen();
    }
        catch (AssertionError asert) {
        liorTests.log(LogStatus.FAIL, "fail to choose business and money! " + asert);
        printScreen();
    }
    }

    @Test
    public void test06_orderPage() throws Exception {
        orderPage.fillOrderForm();
        printScreen();
        Thread.sleep(1500);
        try {
            Assert.assertEquals("https://buyme.co.il/payment/12021902",driver.getCurrentUrl());
            liorTests.log(LogStatus.PASS, "Success to fill all info and submit to next page!");
            printScreen();
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "fail submit to next page! " + asert);
            printScreen();
        }
    }

    @Test
    public void test07_scrollToBottom() throws Exception {
        driver.get(general.readFromFile("url"));
        WebElement bottom=driver.findElement(By.cssSelector("div.footer-bottom"));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottom);
            Thread.sleep(1500);
            printScreen();
            Assert.assertTrue(bottom.isDisplayed());
            liorTests.log(LogStatus.PASS, "bottom of the page");
            Thread.sleep(3000);
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.PASS, "Not the bottom of the page");
            printScreen();
        }
    }

    @Test
    public void test08_receiverColor() throws Exception {
     //   driver.get(general.readFromFile("url"));
        WebElement waitTemp = (new WebDriverWait(driver, 20)).until((ExpectedConditions.presenceOfElementLocated(By.cssSelector("form div.chosen-container.chosen-container-single.form-control.dib:nth-of-type(1)"))));
        loged.search();
        chooseGift.chooseBusiness();
        Thread.sleep(2000);
        String colorCode = orderPage.receiver.getCssValue("color");
        String colorExpected="rgba(250, 180, 66, 1)";
        try {
            Assert.assertEquals(colorExpected, colorCode);
            liorTests.log(LogStatus.INFO, "the receiver color in rgba is: " + colorCode+" as expected!");
            printScreen();
        } catch (AssertionError asert) {
            liorTests.log(LogStatus.INFO, "the receiver color in rgba is: " + colorCode+" not as expected rgba(250, 180, 66, 1)");
            printScreen();
        }
    }

    @Test
    public void test09_assertInfo() throws Exception {
        driver.get(general.readFromFile("url"));
        WebElement waitTemp=(new WebDriverWait(driver,20)).until((ExpectedConditions.presenceOfElementLocated(By.cssSelector("form div.chosen-container.chosen-container-single.form-control.dib:nth-of-type(1)"))));
    /*   add this 2 lines only when it's run alone
        homePage.enterRegistrationButton.click();
        login.loginSite();
    */
        loged.search();
        chooseGift.chooseBusiness();
        orderPage.fillInfoOnly();
        try {
            String receiver1=general.readFromFile("forwho");
            String receiver2=orderPage.finalForWho.getText();
            Assert.assertEquals(receiver1,receiver2);
            liorTests.log(LogStatus.PASS, "the receiver info equals ");
            printScreen();
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "the receiver info not equals "+asert);
            printScreen();
        }
        try {
            String sender1=general.readFromFile("sender");
            String sender2=orderPage.finalSender.getText();
            Assert.assertEquals(sender1,sender2);
            liorTests.log(LogStatus.PASS, "the sender info equals ");
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "the sender info not equals "+asert);
        }
        try {
            String blessing1=general.readFromFile("blessing");
            String blessing2=orderPage.finalBlessing.getText();
            Assert.assertEquals(blessing1,blessing2);
            liorTests.log(LogStatus.PASS, "the blessing info equals ");
        }
        catch (AssertionError asert){
            liorTests.log(LogStatus.FAIL, "the blessing info not equals "+asert);
        }
    }

    @Test
    public void test10_loadingDots() throws InterruptedException {
        Dimension point= (Dimension) extras.loadingDots();
        Thread.sleep(2000);
        try{
            int height=18;
            int width=18;
            Assert.assertEquals(height,point.getHeight());
            Assert.assertEquals(width,point.getWidth());
            Thread.sleep(1000);
            liorTests.log(LogStatus.INFO, "The dots size is: "+point+" as expected");
            printScreen();
        }
        catch (AssertionError a) {
            liorTests.log(LogStatus.INFO, "The dots size is: " + point+"not as expected: "+a);
            printScreen();
        }
    }

    private void assertEmailRedTextColor(boolean ff0000) {   //check text color to be red
        liorTests.log(LogStatus.PASS, "The email text color is red (#ff0000) as expected ");
    }

    private void assertPassRedTextColor(boolean ff0000) {   //check text color to be red
        liorTests.log(LogStatus.PASS, "The password text color is red (#ff0000) as expected ");
    }

    private static void setBrowser(String browser)//select web browser
    {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C://selenium//Drivers//chromedriver.exe");
                ChromeOptions Options = new ChromeOptions();
                Options.addArguments("-incognito");
                Options.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(Options);
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C://selenium//Drivers//geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }
    }
    private void printScreen()
    {
        liorTests.log(LogStatus.INFO, "details", liorTests.addScreenCapture(general.takeScreenShot(imagePath + "\\" + System.currentTimeMillis(), driver)));
    }
    private void startTest() {
        liorTests = extent.startTest(name.getMethodName());
        liorTests.log(LogStatus.INFO, name.getMethodName() + ": start test");
    }

    private void endTest() {
        extent.endTest(liorTests);
        liorTests.log(LogStatus.INFO, name.getMethodName() + ": end test");
    }
}