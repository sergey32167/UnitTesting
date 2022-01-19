package unitTests.testNg;

import data.DataPath;
import org.testng.Assert;
import org.testng.annotations.*;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;

public class JsonParserTest {
    private JsonParser parser;
    private File file;

    @BeforeGroups
    void startGroups(){
        variableInitialization();
    }

    @BeforeClass
    void startClass(){
        variableInitialization();
    }

    @AfterClass
    void deleteTestFile() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test(groups = "Parser")
    void fileIsCreated() {
        parser.writeToFile(new Cart("test-cart"));
        Assert.assertTrue(file.exists());
    }

    @Test(groups = "Parser", alwaysRun = true)
    void writeAndReadFromFile() {
        parser.writeToFile(new Cart("test-cart"));
        Cart testCart = parser.readFromFile(file);
        Assert.assertNotNull(testCart);
    }

    @Test(groups = "Parser", expectedExceptions = NullPointerException.class)
    void writeToFileNull() {
        parser.writeToFile(null);
    }

    @Test(groups = "Parser", expectedExceptions = NullPointerException.class, enabled = false)
    void readFromFileNull() {
        Cart testCart = parser.readFromFile(null);
    }

    @Test(dataProvider = "Data Path", dataProviderClass = DataPath.class, groups = "Parser")
    void readFromFile(String pathName) {
        Assert.assertThrows(NoSuchFileException.class, () -> {
            parser.readFromFile(new File(pathName));
        });
    }

    private void variableInitialization() {
        parser = new JsonParser();
        file = new File("src/main/resources/test-cart.json");
    }
}
