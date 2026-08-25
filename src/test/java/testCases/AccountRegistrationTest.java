package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class AccountRegistrationTest extends BaseClass {

    @Test
    public void verify_account_registration() {

        HomePage hp = new HomePage(driver);

        // Step 1
        hp.clickOnMyAccountDrp();

        ExtentReportListener.getTest()
                .pass("Click My Account");

        // Step 2
        hp.selectRegisterOptn();

        ExtentReportListener.getTest()
                .pass("Select Register option");

        // Step 3
        String getTitle = driver.getTitle();

        Assert.assertEquals(
                getTitle,
                "Register Account");

        ExtentReportListener.getTest()
                .pass("Verify Register Account title");
    }
}
