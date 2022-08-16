package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WalletHubLoginPage {

	public WalletHubLoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Login']")
	WebElement logintab;

	@FindBy(name = "em")
	WebElement email;

	@FindBy(name = "pw1")
	WebElement password;

	@FindBy(xpath = "//span[text()='Login']")
	WebElement loginbutton;

	
	public WebElement getLoginTab() {
		return logintab;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginbutton;
	}
}
