/*@author Chindhu Babu*/
/* Test to Verify Check Box can be checked against a specific value passing it as an argument
* @text  */

package uitests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.datatable.SelectionPg;


public class TestDataTable extends TestBase {

    @Test
    public void checkboxTest() {
        SelectionPg selectPg=new SelectionPg(driver);
        Assert.assertTrue(selectPg.verifyCheckBox("Blue Band").equals("true"));
    }
}
