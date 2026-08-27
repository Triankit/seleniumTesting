package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import utilities.StringUtils;
import testBase.BaseClass;
import utilities.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class AccountRegistrationTest extends BaseClass {

	
	@Test()
	public void verify_account_registrationPage() {

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
	
	@Test
	public void verify_account_registration() {
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccountDrp();
		hp.selectRegisterOptn();
		StringUtils su = new StringUtils();
		String email = su.getRandomEmail(5);
		System.out.println(email);
		
		hp.fillRegistrationForm("ank", "tri", email, "123456");
		hp.acceptPrivacyPolicy();
		hp.clickContinue();
		Boolean bool = hp.txtAccountCreated.isDisplayed();
		Assert.assertEquals(true, bool);
	}

}
