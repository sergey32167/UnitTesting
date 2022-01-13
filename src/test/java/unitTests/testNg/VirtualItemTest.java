package unitTests.testNg;

import org.testng.Assert;
import org.testng.annotations.*;
import shop.VirtualItem;

public class VirtualItemTest {
    private VirtualItem virtualItem;

    @BeforeGroups
    void startGroups(){
        variableInitialization();
    }

    @BeforeClass
    void startClass(){
        variableInitialization();
    }

    @Parameters({"sizeData"})
    @Test(groups = "Item")
    void equalsData(@Optional("121212.987655544") double size){
        virtualItem.setSizeOnDisk(size);
        Assert.assertEquals(size, virtualItem.getSizeOnDisk());
    }

    private void variableInitialization(){
        virtualItem = new VirtualItem();
    }
}
