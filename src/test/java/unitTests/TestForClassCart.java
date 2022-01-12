package unitTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import shop.Cart;
import shop.RealItem;

public class TestForClassCart {
    private Cart testCart;
    private RealItem realItem;

    @BeforeGroups("Cart")
    void variableInitializationCart() {
        realItem = new RealItem();
        testCart = new Cart("test-cart");
    }

    @BeforeClass
    void variableInitialization() {
        testCart = new Cart("test-cart");
        realItem = new RealItem();
    }

    @Test(groups = {"Cart"})
    void equalsTotalPrise() {
        realItem.setPrice(10);
        testCart.addRealItem(realItem);
        Assert.assertEquals(12, testCart.getTotalPrice());
    }

    @Test(groups = {"Cart"})
    void addItem() {
        realItem.setName("BASIA");
        testCart.addRealItem(realItem);
        testCart.equals(realItem.toString());
    }
}
