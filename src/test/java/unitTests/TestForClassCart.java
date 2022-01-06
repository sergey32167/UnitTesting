package unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;

public class TestForClassCart {
    private Cart testCart;
    private RealItem realItem;

    @BeforeEach
    void variableInitialization() {
        testCart = new Cart("test-cart");
        realItem = new RealItem();
    }

    @Test
    @DisplayName("checking the correct count total")
    void equalsTotalPrise() {
        realItem.setPrice(10);
        testCart.addRealItem(realItem);
        Assertions.assertEquals(testCart.getTotalPrice(), 12);
    }

    @Test
    @DisplayName("checking if an item has been added to the cart")
    void addItem() {
        realItem.setName("BASIA");
        testCart.addRealItem(realItem);
        testCart.showItems();

    }
}
