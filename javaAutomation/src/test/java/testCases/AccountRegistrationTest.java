package testCases;

import testBase.BaseClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import pageObjects.HomePage;

public class AccountRegistrationTest extends BaseClass {
	
	@Test
	public void verify_account_registration() {
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccountDrp();
		hp.selectRegisterOptn();
		
		String getTtile = driver.getTitle();
		
		Assert.assertEquals(getTtile, "Register Account");
	}

}
