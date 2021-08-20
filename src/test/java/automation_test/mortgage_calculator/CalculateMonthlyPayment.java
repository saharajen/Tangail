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
import utilities.DateUtils;
import utilities.ReadConfigFiles;

public class CalculateMonthlyPayment extends LoggerForParallelTests {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMonthlyPayment.class);
    WebDriver driver;

    @BeforeMethod
    public void browserInitializations() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("-------Start Test CalculateMonthlyPayment ------- ");
        String url = ReadConfigFiles.getPropertyValues("MortgageCalculatorUrl");
        ActOn.browser(driver).openBrowser(url);
        LOGGER.info("Browser is successfully initiated with the URL: " + url);
    }

    @Test(retryAnalyzer = RetryFailedTests.class)
    public void calculateMonthlyPayment() {
        String Date = DateUtils.returnNextMonth();
        String[] dateArray = Date.split("-");
        String month = dateArray [0];
        String year = dateArray[1];
        new Home(driver)                            //      Home home = new Home(driver)
                .typeHomePrice("300000")
                .typeDownPayment("60000")
                .clickDownPaymentInDollar()
                .typeLoneAmount("240000")
                .typeInterestRate("3")
                .typeLoneTermInYears("30")
                .selectMonth(month)
                .typeYear(year)
                .typePropertyTax("5000")
                .typePmi("0.5")
                .typeHOI("1000")
                .typeHOA("100")
                .selectLoanType("FHA")
                .selectBuyOrRefi("Buy")
                .clickCalculateButton()
                .validateMonthlyPayment("$1,611.85");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        ActOn.browser(driver);
        LOGGER.info("Browser is closed");
        LOGGER.info("==End Test CalculateMonthlyPayment==");
    }

}







//public class CalculateMonthlyPayment {
//    private final By HomeValue = By.id("homeval");
//    private final By DownPayment = By.id("downpayment");
//    private final By DownPaymentInDollar = By.xpath("//*[@id='calc']//input[@name='param[downpayment_type]'][@value='money']");
//    private final By LoanAmount = By.id("loanamt");
//    private final By InterestRate = By.id("intrstsrate");
//    private final By LoanTerm = By.id("loanterm");
//    private final By StartDateMonth = By.name("param[start_month]");
//    private final By StartDateYear = By.id("start_year");
//    private final By PropertyTax = By.id("pptytax");
//    private final By Pmi = By.id("pmi");
//    private final By Hoi = By.id("hoi");
//    private final By Hoa = By.id("hoa");
//    private final By LoanType = By.name("param[milserve]");
//    private final By BuyOrRefi = By.name("param[refiorbuy]");
//    private final By Calculate = By.name("cal");
//    private final By TotalMonthlyPayment = By.xpath("//*[@id='calc']//h3[text()='$1,611.85']");
//
//    WebDriver driver;
//
//    @BeforeMethod
//    public void browserInitializations() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");
//    }
//
//    public  void enterMortgageData() {
//        //Enter Home value "300000"
//        ActOn.element(driver, HomeValue).setValue("300000");
//
//        //Enter down payment "60000"
//        ActOn.wait(driver, DownPayment).waitForElementToBeVisible();
//        ActOn.element(driver, DownPayment).setValue("60000");
//
//        //Click on the $ radio button for the down payment option
//        ActOn.element(driver, DownPaymentInDollar).click();
//
//        //Enter loan amount "240000"
//        ActOn.element(driver, LoanAmount).setValue("240000");
//
//        //Enter interest rate "3%"
//        ActOn.element(driver, InterestRate).setValue("3");
//
//        //Enter loan term "30" years
//        ActOn.element(driver, LoanTerm).setValue("30");
//
//        //Selection the month and year
//        String Date = DateUtils.returnNextMonth();
//        String[] dateArray = Date.split("-");
//        String month = dateArray [0];
//        String year = dateArray[1];
//
//        ActOn.element(driver, StartDateMonth).selectValue(month);
//        ActOn.element(driver, StartDateYear).setValue(year);
//
//        //Enter the property tax "5000"
//        ActOn.element(driver, PropertyTax).setValue("5000");
//
//        //Enter the pmi "0.5"
//        ActOn.element(driver, Pmi).setValue("0,5");
//
//        //Enter the homeowner insurance "1000"
//        ActOn.element(driver, Hoi).setValue("1000");
//
//        //Enter the monthly hoa "100"
//        ActOn.element(driver, Hoa).setValue("100");
//
//        //Selecting the loan type "FHA"
//        ActOn.element(driver, LoanType).selectValue("FHA");
//
//        //selecting "Buy" from Buy or Refi dropdown
//        ActOn.element(driver, BuyOrRefi).selectValue("Buy");
//
//    }
//    @Test
//    public void calculateMonthlyPayment() {
//        enterMortgageData();
//
//        // Click on the calculate button
//        ActOn.element(driver, Calculate).click();
//
//        //Validating that total monthly payment is accurate
//        AssertThat.elementAssertions(driver, TotalMonthlyPayment).elementExist();
//    }
//
//    @AfterMethod
//    public void closeBrowser() {
//        driver.quit();
//        ActOn.browser(driver);
//    }
//
//}






//    @BeforeMethod
//    public void openBrowser() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://www.mortgagecalculator.org/");
//        driver.manage().window().maximize();
//    }
//
//    public  void enterMortgageData() throws InterruptedException {
//        //Enter Home value "300000"
//        driver.findElement(HomeValue).clear();
//        driver.findElement(HomeValue).sendKeys("300000");
//        Thread.sleep(3000); //try to don't use this.
//
//        //Enter down payment "60000"
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(DownPayment));
//        driver.findElement(DownPayment).clear();
//        driver.findElement(DownPayment).sendKeys("60000");
//
//        //Click on the $ radio button for the downpayment option
//        driver.findElement(DownPaymentInDollar).click();
//
//        //Enter loan amount "240000"
//        driver.findElement(LoanAmount).clear();
//        driver.findElement(LoanAmount).sendKeys("240000");
//
//        //Enter interest rate "3%"
//        driver.findElement(InterestRate).clear();
//        driver.findElement(InterestRate).sendKeys("3");
//
//        //Enter loan term "30" years
//        driver.findElement(LoanTerm).clear();
//        driver.findElement(LoanTerm).sendKeys("30");
//
//        //Selection the month
//        select = new Select(driver.findElement(StartDateMonth));
//        select.selectByVisibleText("Nov");
//
//        //Enter the year
//        driver.findElement(StartDateYear).clear();
//        driver.findElement(StartDateYear).sendKeys("2021");
//
//        //Enter the property tax "5000"
//        driver.findElement(PropertyTax).clear();
//        driver.findElement(PropertyTax).sendKeys("5000");
//
//        //Enter the pmi "0.5"
//        driver.findElement(Pmi).clear();
//        driver.findElement(Pmi).sendKeys("0.5");
//
//        //Enter the homeowner insurance "1000"
//        driver.findElement(Hoi).clear();
//        driver.findElement(Hoi).sendKeys("1000");
//
//        //Enter the monthly hoa "100"
//        driver.findElement(Hoa).clear();
//        driver.findElement(Hoa).sendKeys("100");
//
//        //Selecting the loan type "FHA"
//        select = new Select(driver.findElement(LoanType));
//        select.selectByVisibleText("FHA");
//
//        //selecting "Buy" from Buy or Refi dropdown
//        select = new Select(driver.findElement(BuyOrRefi));
//        select.selectByVisibleText("Buy");
//
//    }
//    @Test
//    public void calculateMonthlyPayment() throws InterruptedException {
//
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//        enterMortgageData();
//
//        // Click on the calculate button
//        driver.findElement(Calculate).click();
//
//        boolean monthlyPaymentExist = driver.findElements(TotalMonthlyPayment). size() > 0;
//        Assert.assertTrue(monthlyPaymentExist, "Total Monthly Payment is Wrong");
//    }
//
//    @AfterMethod
//    public void closeBrowser() {
//        driver.quit();
//    }
//
//}