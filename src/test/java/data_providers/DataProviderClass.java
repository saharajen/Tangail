package data_providers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
    @DataProvider(name = "SingleValue")
    public Object[][] storeSingleValue() {
        return new Object[][] {
                {"Rifat"},
                {"Mohammad"},
                {"Ashraf"}
        };
    }

    @DataProvider(name = "MultipleValue")
    public Object [][] storeMultipleValue() {
        return new Object[][] {
                {"Rifat", "Florida", 33018},
                {"Farid", "New York", 12458},
                {"Ashraf", "Bangladesh", 1207}
        };
    }

    @DataProvider(name = "RealAprRates")
    public Object[][] storeRealAprRates() {
        return new Object[][] {
                {"200000", "15000", "3", "3.130%"}
        };
    }

    @Test(dataProvider = "SingleValue")
    public void run(String name) {
        System.out.println("Single value name is " + name);
    }
    @Test(dataProvider = "MultipleValue")
    public void run(String name, String state, int zipCode) {
        System.out.println("[Multiple Value] name is " + name);
        System.out.println("[Multiple Value] state is " + state);
        System.out.println("[Multiple Value] zip code is " + zipCode);
    }
}
