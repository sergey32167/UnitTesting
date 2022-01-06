package unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.RealItem;

public class TestForClassRealItem {
    private RealItem animal;

    @BeforeEach
    void variableInitialization(){
        animal = new RealItem();
    }

    @Test
    @DisplayName("checking correct data entry")
    void equalsData(){
        animal.setName("Cat");
        animal.setPrice(100.5454);
        animal.setWeight(3.5);
        Assertions.assertAll("compare all data to realItem",
                () -> Assertions.assertEquals( animal.getName(), "Cat"),
                () -> Assertions.assertEquals(animal.getPrice(), 100.5454),
                () -> Assertions.assertEquals(animal.getWeight(), 3.5 )
        );
    }
}
