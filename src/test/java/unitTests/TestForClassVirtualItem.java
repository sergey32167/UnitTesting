package unitTests;

import org.testng.Assert;
import org.testng.annotations.*;
import shop.VirtualItem;

public class TestForClassVirtualItem {
    private VirtualItem virtualItem;

    @BeforeGroups("Item")
    void variableInitializationItem(){
        virtualItem = new VirtualItem();
    }

    @BeforeClass
    void variableInitialization(){
        virtualItem = new VirtualItem();
    }

    @Parameters({"sizeData"})
    @Test(groups = "Item")
    void equalsData(@Optional("121212.987655544") double size){
        virtualItem.setSizeOnDisk(size);
        Assert.assertEquals(size, virtualItem.getSizeOnDisk());
    }
}
