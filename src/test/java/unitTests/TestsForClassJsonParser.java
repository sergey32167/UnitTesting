package unitTests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;

public class TestsForClassJsonParser {
    private JsonParser parser;
    private File file;

    @BeforeEach
    public void variableInitialization() {
        parser = new JsonParser();
        file = new File("src/main/resources/test-cart.json");
    }

    @AfterEach
    void deleteTestFile() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("checking of creating a file")
    void fileIsCreated() {
        parser.writeToFile(new Cart("test-cart"));
        Assertions.assertTrue(file.exists());
    }

    @Test
    @DisplayName("checking writing and reading from a file")
    void writeAndReadFromFile() {
        parser.writeToFile(new Cart("test-cart"));
        Cart testCart = parser.readFromFile(file);
        Assertions.assertNotNull(testCart);
    }

    @Test
    @DisplayName("checking if a file is written to a null value")
    void writeToFileNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            parser.writeToFile(null);
        });
    }

    @Test
    @DisplayName("checking data read from null")
    void readFromFileNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Cart testCart = parser.readFromFile(null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "src/main/resources/test", "src/main/resources/test-cart", "src/main/resources/andrew-cart", "src/main/resources/eugen-cart"})
    @DisplayName("checking reading from non-existent files")
    void readFromNoSuchFileExceptions(String pathName) {
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            parser.readFromFile(new File(pathName));
        });
    }
}
