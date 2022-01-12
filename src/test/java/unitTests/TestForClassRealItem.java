package unitTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.RealItem;

public class TestForClassRealItem {
    private RealItem animal;

    @BeforeGroups("Item")
    void variableInitializationItem() {
        animal = new RealItem();
    }

    @BeforeClass
    void variableInitialization() {
        animal = new RealItem();
    }

    @Test(groups = "Item")
    void equalsData() {
        SoftAssert softassert = new SoftAssert();
        animal.setName("Cat");
        animal.setPrice(100.5454);
        animal.setWeight(3.5);
        softassert.assertEquals("Cat", animal.getName());
        softassert.assertEquals(100.5454, animal.getPrice());
        softassert.assertEquals(3.5, animal.getWeight());
        softassert.assertAll( );
    }
}