package testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utilities.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TestNgMavenExampleTest {
  private static final Logger LOGGER = LogManager.getLogger(TestNgMavenExampleTest.class);

    @Test
    public void run() {
      ResultSet resultSet = SqlConnector.readData("Select * from monthly_mortgage");
      try {
        while (resultSet.next()) {
          System.out.println("The Home price is: " + resultSet.getString("homevalue"));
          System.out.println("The Down Payment is: " + resultSet.getString("downpayment"));
          System.out.println("The Loan Amount is: " + resultSet.getString("loanamount"));
        }
      }catch (SQLException e) {
        LOGGER.error(e.getMessage());
      }
    }
}
