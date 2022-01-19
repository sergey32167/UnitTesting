package unitTests.jUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.VirtualItem;

public class VirtualItemTest {
    private VirtualItem virtualItem;

    @BeforeEach
    void variableInitialization(){
        virtualItem = new VirtualItem();
    }

    @Test
    @DisplayName("checking correct data entry")
    void equalsData(){
        virtualItem.setSizeOnDisk(121212.987655544);
        Assertions.assertEquals(121212.987655544, virtualItem.getSizeOnDisk());
    }
}
