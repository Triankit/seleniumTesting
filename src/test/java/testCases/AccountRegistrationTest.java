package testCases;

import testBase.BaseClass;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import pageObjects.HomePage;
import utilities.StringUtils;
import utilities.ExtentReportListener;


@Listeners(ExtentReportListener.class)
public class AccountRegistrationTest extends BaseClass {
	
	@Test()
	public void verify_account_registrationPage() {
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccountDrp();
		hp.selectRegisterOptn();
		
		String getTtile = driver.getTitle();
		
		Assert.assertEquals(getTtile, "Register Account");
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
