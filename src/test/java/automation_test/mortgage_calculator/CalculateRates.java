package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.LoggerForParallelTests;
import listeners.RetryFailedTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_Objects.Home;
import utilities.ReadConfigFiles;


public class CalculateRates extends LoggerForParallelTests {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRates.class);
    WebDriver driver;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("-------Start Test CalculateRates ------- ");
        String url = ReadConfigFiles.getPropertyValues("MortgageCalculatorUrl");
        ActOn.browser(driver).openBrowser(url);
        LOGGER.info("Browser is successfully initiated with the URL: " + url);
    }

    @Test(retryAnalyzer = RetryFailedTests.class)
    public void calculateRealApr() {
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageLoad()
                .typeHomeValue("200000")
                .clickDownPaymentInDollar()
                .typeDownPayment("15000")
                .typeInterestRate("3")
                .clickCalculateButton()
                .validateRealAprRate("3.130%");
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("Browser is closed");
        LOGGER.info("~~~End Test CalculateRates~~~ ");
    }
}







//public class CalculateRates {
//    private final By RatesLink = By.linkText("Rates");
//    private final By RealAprLink = By.linkText("Real APR");
//    private final By CalculatorTab = By.xpath("//*[@id='CalcForm']//label[text()='Calculator']");
//    private final By HomeValue = By.name("HomeValue");
//    private final By DownPayment = By.name("DownPayment");
//    private final By DownPaymentInDollar = By.id("DownPaymentSel0");
//    private final By InterestRate = By.name("Interest");
//    private final By CalculateButton = By.name("calculate");
//    private final By ActualAprValue = By.xpath("//*[@id='analysisDiv']/table/tbody/tr/td/strong[contains(text(),'Actual APR')]/../../td[2]/strong");
//
//    WebDriver driver;
//
//    @BeforeMethod
//    public void browserInitialization() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");
//    }
//
//    public void navigateToRealAprPage() {
//        //Mouse Hover to the rates link
//        ActOn.element(driver, RatesLink).mouseHover();
//
//        //Click on Real Apr Link
//        ActOn.element(driver, RealAprLink).click();
//    }
//    public void enterRealAprData() {
//        ActOn.element(driver, HomeValue).setValue("200000");
//        ActOn.element(driver, DownPaymentInDollar).click();
//        ActOn.element(driver, DownPayment).setValue("15000");
//        ActOn.element(driver, InterestRate).setValue("3");
//    }
//
//    @Test
//    public void calculateRealApr() {
//        //Navigating to Real APR Page
//        navigateToRealAprPage();
//
//        //Wait for the calculator tab to exist
//        ActOn.wait(driver, CalculatorTab).waitForElementToBeVisible();
//
//        //Enter data to calculate real APR
//        enterRealAprData();
//
//        //Click on Calculate Button
//        ActOn.element(driver, CalculateButton).click();
//
//        //Validate the APR
//        String aprRate = ActOn.element(driver, ActualAprValue).getTextValue();
//        Assert.assertEquals(aprRate, "3.130%");
//    }
//
//    @AfterMethod
//    public void closeBrowser() {
//        ActOn.browser(driver).closeBrowser();
//    }
//}

