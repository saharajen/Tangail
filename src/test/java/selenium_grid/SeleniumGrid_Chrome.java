package selenium_grid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.TestEnvironment;
import java.net.MalformedURLException;


public class SeleniumGrid_Chrome {
    WebDriver driver;

    @Test
    public void executeInAwsDocker() throws MalformedURLException {
        driver = TestEnvironment.selectTestExecutionEnvironment();
        driver.get("http://www.mortgagecalculator.org/");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}


//public class SeleniumGrid_Chrome {
//    WebDriver driver;
//    URL gridUrl;


//    @Test
//    public void executeInAwsDocker() {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        String ec2Address = ReadConfigFiles.getPropertyValues("EC2PublicIp");
//        try {
//            gridUrl = new URL("http://" + ec2Address + ":4444/wd/hub");
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        driver = new RemoteWebDriver(gridUrl, chromeOptions);
//        driver.get("http://www.mortgagecalculator.org/");
//        System.out.println(driver.getTitle());
//        driver.quit();
//    }
//}