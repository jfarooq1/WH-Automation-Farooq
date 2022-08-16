package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WalletHubCompanyProfile {

	public WalletHubCompanyProfile(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='left-content']/a[2]")
	WebElement reviewDiv;

	@FindBy(css = ".review-action .rvs-svg .rating-box-wrapper [aria-label='4 star rating']")
	WebElement starDiv;

	@FindBy(css = ".wrev-drp > .dropdown > .dropdown-placeholder")
	WebElement dropDown;

	@FindBy(xpath = "//textarea")
	WebElement review;

	@FindBy(xpath = "//div[text()=' Submit ']")
	WebElement submit;

	@FindBy(css = ".rvc-header h4")
	WebElement response;

	@FindBy(xpath = "//div[text()='Continue']")
	WebElement con;

	@FindBy(css=".rsba-h3.bold-font")
	WebElement label;
	
	// -----

	public WebElement getReviewDiv() {
		return reviewDiv;
	}

	public WebElement getStarDiv() {
		return starDiv;
	}

	public WebElement getDropDown() {
		return dropDown;
	}

	public WebElement getReview() {
		return review;
	}

	public WebElement getSubmit() {
		return submit;
	}

	public WebElement getResponse() {
		return response;
	}

	public WebElement getContinue() {
		return con;
	}

	public WebElement getLabel() {
		return label;
	}


}
