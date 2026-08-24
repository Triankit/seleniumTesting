package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	public void clickOnMyAccountDrp() {
		myAccountDrp.click();
	}
	
	public void selectRegisterOptn() {
		optnRegister.click();
	}

	public void selectLoginOptn() {
		optnLogin.click();
	}
}