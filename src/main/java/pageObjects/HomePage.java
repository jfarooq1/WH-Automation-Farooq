package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".k4urcfbm.g5gj957u.buofh1pr.j83agx80.ll8tlv6m")
	WebElement section;

	@FindBy(css = "div[aria-label*='on your mind']")
	WebElement input;

	@FindBy(css = "div[aria-label*='Post']")
	WebElement post;

	public WebElement getSectionEnable() {
		return section;
	}

	public WebElement getInput() {
		return input;
	}

	public WebElement getPost() {
		return post;
	}

}
