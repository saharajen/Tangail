package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestEnvironment {
    private static final Logger LOGGER = LogManager.getLogger(TestEnvironment.class);

    /***
     * This method will select the execution environment bases on the user imput
     * @return
     * @throws MalformedURLException
     */


    public static WebDriver selectTestExecutionEnvironment() throws MalformedURLException {
        String environment = System.getProperty("environment") == null ? "Local" : System.getProperty("environment");
        String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
        String ec2Address = ReadConfigFiles.getPropertyValues("EC2PublicIp");

        URL gridUrl = new URL("http://" + ec2Address + ":4444/wd/hub");
        WebDriver driver;

        if (environment.equals("remote") && browser.equals("chrome")) {
         LOGGER.info("Execute test in remote with chrome browser");
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new RemoteWebDriver(gridUrl, chromeOptions);
        } else if (environment.equals("remote") && browser.equals("firefox")) {
            LOGGER.info("Execute test in remote with firefox browser");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new RemoteWebDriver(gridUrl, firefoxOptions);
        } else if (environment.equals("remote") && browser.equals("edge")) {
            LOGGER.info("Execute test in remote with edge browser");
            EdgeOptions edgeOptions = new EdgeOptions();
            driver = new RemoteWebDriver(gridUrl, edgeOptions);
        } else {
            LOGGER.info("Execute test in local with chrome browser");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }
}
