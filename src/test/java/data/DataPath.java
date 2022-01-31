package data;

import org.testng.annotations.DataProvider;

public class DataPath {

    @DataProvider(name = "Data Path")
    public Object[][] path(){
        return new Object[][]{
                {" "},
                {"src/main/resources/test"},
                {"src/main/resources/test-cart"},
                {"src/main/resources/andrew-cart"},
                {"src/main/resources/eugen-cart"}
        };
    }
}
