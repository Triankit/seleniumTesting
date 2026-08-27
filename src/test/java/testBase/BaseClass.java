package testBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseClass {
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		ChromeOptions chromeOption = new ChromeOptions();
		chromeOption.addArguments("--incognito");
		driver = new ChromeDriver(chromeOption);
		driver.get("http://localhost/opencart/upload");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
