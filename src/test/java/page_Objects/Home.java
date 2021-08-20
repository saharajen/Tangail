package page_Objects;

import command_providers.ActOn;
import command_providers.AssertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends NavigationBar {
    private static final Logger LOGGER = LogManager.getLogger(Home.class);
    private final By HomeValue = By.id("homeval");
    private final By DownPayment = By.id("downpayment");
    private final By DownPaymentInDollar = By.xpath("//*[@id='calc']//input[@name='param[downpayment_type]'][@value='money']");
    private final By LoanAmount = By.id("loanamt");
    private final By InterestRate = By.id("intrstsrate");
    private final By LoanTerm = By.id("loanterm");
    private final By StartDateMonth = By.name("param[start_month]");
    private final By StartDateYear = By.id("start_year");
    private final By PropertyTax = By.id("pptytax");
    private final By Pmi = By.id("pmi");
    private final By Hoi = By.id("hoi");
    private final By Hoa = By.id("hoa");
    private final By LoanType = By.name("param[milserve]");
    private final By BuyOrRefi = By.name("param[refiorbuy]");
    private final By Calculate = By.name("cal");

    public Home(WebDriver driver) {
        super(driver);
    }

    //Enter Home value
    public Home typeHomePrice(String value) {
        LOGGER.debug("Entered home price is " + value);
        ActOn.element(driver, HomeValue).setValue(value);
        return this;
    }

    //Enter Down payment
    public Home typeDownPayment(String value) {
        ActOn.wait(driver, DownPayment).waitForElementToBeVisible();
        LOGGER.debug("Entered down payment is " + value);
        ActOn.element(driver, DownPayment).setValue(value);
        return this;

    }

    //Click on the $ radio button for the down payment option
    public Home clickDownPaymentInDollar() {
        LOGGER.debug("Clicked on the down payment in dollar");
        ActOn.element(driver, DownPaymentInDollar).click();
        return this;
    }

    //Enter loan amount
    public Home typeLoneAmount(String value) {
        LOGGER.debug("Entered loan amount is " + value);
        ActOn.element(driver, LoanAmount).setValue(value);
        return this;
    }

    //Enter interest rate
    public Home typeInterestRate(String value) {
        LOGGER.debug("Entered interest rate is " + value);
        ActOn.element(driver, InterestRate).setValue(value);
        return this;
    }

    //Enter loan term
    public Home typeLoneTermInYears(String value) {
        LOGGER.debug("Entered loan term is " + value);
        ActOn.element(driver, LoanTerm).setValue(value);
        return this;
    }

    //Selection the month
    public Home selectMonth(String month) {
        LOGGER.debug("Selected month is " + month);
        ActOn.element(driver, StartDateMonth).selectValue(month);
        return this;
    }

    //Selection the year
    public Home typeYear(String year) {
        LOGGER.debug("Entered year is " + year);
        ActOn.element(driver, StartDateYear).setValue(year);
        return this;
    }

    //Enter the property tax
    public Home typePropertyTax(String value) {
        LOGGER.debug("Entered property tax is " + value);
        ActOn.element(driver, PropertyTax).setValue(value);
        return this;
    }

    //Enter the pmi "0.5"
    public Home typePmi(String value) {
        LOGGER.debug("Entered PMI is " + value);
        ActOn.element(driver, Pmi).setValue(value);
        return this;
    }

    //Enter the homeowner insurance
    public Home typeHOI(String value) {
        LOGGER.debug("Entered HOI is " + value);
        ActOn.element(driver, Hoi).setValue(value);
        return this;
    }
    //Enter the monthly hoa
    public Home typeHOA(String value) {
        LOGGER.debug("Entered HOA is " + value);
        ActOn.element(driver, Hoa).setValue(value);
        return  this;
    }
    //Selecting the loan type "FHA"
    public Home selectLoanType(String value) {
        LOGGER.debug("Selected Loan type is " + value);
        ActOn.element(driver, LoanType).selectValue(value);
        return this;
    }
    //selecting "Buy" from Buy or Refi dropdown
    public Home selectBuyOrRefi(String value) {
        LOGGER.debug("Selected Buy or Refi is" + value);
        ActOn.element(driver, BuyOrRefi).selectValue(value);
        return this;
    }
    // Click on the calculate button
    public Home clickCalculateButton() {
        LOGGER.debug("Clicked on the Calculate Button");
        ActOn.element(driver, Calculate).click();
        return this;
    }

    //Validating that total monthly payment is accurate
    public Home validateMonthlyPayment(String totalMonthlyPayment) {
        LOGGER.debug("Validating total monthly payment");
        By monthlyPaymentLocator =By.xpath("//*[@id='calc']//h3[text()='"+ totalMonthlyPayment +"']");
        AssertThat.elementAssertions(driver, monthlyPaymentLocator).elementExist();
        return this;
    }


}
