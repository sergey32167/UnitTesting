package unitTests.testNg;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.RealItem;

public class RealItemTest {
    private RealItem animal;

    @BeforeGroups
    void startGroups(){
        variableInitialization();
    }

    @BeforeClass
    void startClass(){
        variableInitialization();
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

    private void variableInitialization() {
        animal = new RealItem();
    }
}