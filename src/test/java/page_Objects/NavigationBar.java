package page_Objects;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {
    private static final Logger LOGGER = LogManager.getLogger(NavigationBar.class);
    private final By MortgageCalculatorLog = By.xpath("//a/img[@alt='Logo']");
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");

    public WebDriver driver;

    public NavigationBar(WebDriver driver){
        this.driver = driver;
    }

    //Mouse Hover to the rates link
    public NavigationBar mouseHoverToRates() {
        LOGGER.debug("Mouse Hover to rate link in the navigation bar");
        ActOn.element(driver, RatesLink).mouseHover();
        return this;
    }

    public RealApr navigateToRealApr() {
        LOGGER.debug("Navigation to real APR page");
        ActOn.element(driver, RealAprLink).click();
        return new RealApr(driver);
    }

    public Home navigateToHome() {
        LOGGER.debug("Navigation to home page");
        ActOn.element(driver, MortgageCalculatorLog).click();
        return new Home(driver);
    }

}
