package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Factory;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccountDrp;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement optnRegister;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement optnLogin;
	
	@FindBy(id ="input-firstname")
	WebElement inputFirstname;
	
	@FindBy(name="lastname")
	WebElement inputLastname;
	
	@FindBy(id="input-email")
	WebElement inputEmail;
	
	@FindBy(css="[type='password']")
	WebElement inputPassword;
	
	@FindBy(xpath="//*[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	public
	WebElement txtAccountCreated;
	

	public void clickOnMyAccountDrp() {
		myAccountDrp.click();
	}
	
	public void selectRegisterOptn() {
		optnRegister.click();
	}

	public void selectLoginOptn() {
		optnLogin.click();
	}
	
	public void fillRegistrationForm(String firstName,String lastName, String email, String pass) {
		inputFirstname.sendKeys(firstName);
		inputLastname.sendKeys(lastName);
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(pass);		
	}
	
	
	public void acceptPrivacyPolicy() {
		chkPrivacyPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
		
	}
	
}