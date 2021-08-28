package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BaseClass {
    public static WebDriver driver;
    Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    String testCaseName = String.format("--------Test: %s-----------", this.getClass().getName());
    String endTestCase = String.format("--------Test End:");
}
