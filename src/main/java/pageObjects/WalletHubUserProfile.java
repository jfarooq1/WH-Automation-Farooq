package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WalletHubUserProfile {

	public WalletHubUserProfile(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".pr-rec-texts-container a")
	WebElement company;

	public WebElement getCompany() {
		return company;
	}

}
