package selenium_grid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.TestEnvironment;
import java.net.MalformedURLException;

public class SeleniumGrid_Firefox {
    WebDriver driver;

    @Test
    public void executeInAwsDocker() throws MalformedURLException {
        driver = TestEnvironment.selectTestExecutionEnvironment();
        driver.get("http://www.amazon.com/");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}




//public class SeleniumGrid_Firefox {
//    WebDriver driver;
//    URL gridUrl;
//
//    @Test
//    public void executeInAwsDocker() {
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        String ec2Address = ReadConfigFiles.getPropertyValues("EC2PublicIp");
//        try {
//            gridUrl = new URL("http://100.26.182.142:4444/wd/hub");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        driver = new RemoteWebDriver(gridUrl, firefoxOptions);
//        driver.get("http://www.amazon.com/");
//        System.out.println(driver.getTitle());
//        driver.quit();
//    }
//}
