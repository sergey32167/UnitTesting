package unitTests.testNg;

import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import shop.Cart;
import shop.RealItem;

public class CartTest {
    private Cart testCart;
    private RealItem realItem;

    @BeforeGroups
    void startGroups(){
        variableInitialization();
    }

    @BeforeClass
    void startClass(){
        variableInitialization();
    }

    @Test(groups = {"Cart"})
    void equalsTotalPrise() {
        realItem.setPrice(10);
        testCart.addRealItem(realItem);
        Assert.assertEquals(12, testCart.getTotalPrice());
    }

    @Test(groups = {"Cart"})
    void equalsCartName() {
        Assertions.assertEquals("test-cart" ,testCart.getCartName());

    }

    private void variableInitialization(){
        testCart = new Cart("test-cart");
        realItem = new RealItem();
    }
}
