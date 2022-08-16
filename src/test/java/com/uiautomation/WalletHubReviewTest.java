package com.uiautomation;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.WalletHubCompanyProfile;
import pageObjects.WalletHubLoginPage;
import pageObjects.WalletHubUserProfile;
import resources.Base;

public class WalletHubReviewTest extends Base {

	private static Logger log = LogManager.getLogger(WalletHubReviewTest.class.getName());

	@Test
	public void postReview() throws IOException, InterruptedException {

		driver = initializeDriver();
		log.debug("Waiting for WalletHub.com...");
		driver.get("https://wallethub.com/join/light");

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/parameters.properties");
		prop.load(fis);

		// Login Screen Flow
		WalletHubLoginPage loginPage = new WalletHubLoginPage(driver);
		loginPage.getLoginTab().click();

		log.debug("Entering Email.");
		loginPage.getEmail().sendKeys(prop.getProperty("wallethubemail"));

		log.debug("Entering Password.");
		loginPage.getPassword().sendKeys(prop.getProperty("wallethubpassword"));

		log.debug("Signing-in.");
		loginPage.getLoginButton().click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".brgm-button.brgm-user.brgm-list-box .brgm-list-title")));

		String profile = driver.getCurrentUrl();
		log.info(profile);
		driver.navigate().to("https://wallethub.com/profile/13732055i");

		log.debug("Navigating to Test Insurance Company Profile.");

		// Test Insurance Company Profile Flow
		WalletHubCompanyProfile companyProfile = new WalletHubCompanyProfile(driver);
		log.debug("Switch to Reviews Tab.");
		companyProfile.getReviewDiv().click();
		log.debug("Clicking 4 Star Reviews.");
		wait.until(ExpectedConditions.elementToBeClickable(companyProfile.getStarDiv()));
		Thread.sleep(1000);
		companyProfile.getStarDiv().click();
		log.info("4 Star Review Selected.");

		companyProfile.getDropDown().click();
		List<WebElement> li = driver
				.findElements(By.xpath("//div[@class='dropdown second opened'] //li[@class='dropdown-item']"));

		log.debug("Seleting Health Insurance.");
		for (int i = 0; i < li.size(); i++) {
			String insurance = li.get(i).getText();
			if (insurance.equalsIgnoreCase("Health Insurance")) {
				li.get(i).click();
			}
		}

		String review = prop.getProperty("review");
		log.debug("Entering Review.");
		companyProfile.getReview().sendKeys(review);

		log.debug("Submitting Review.");
		companyProfile.getSubmit().click();

		String response = companyProfile.getResponse().getText();
		log.debug("Validating Review Response Success Message.");
		if (response.equalsIgnoreCase("Your review has been posted.")) {
			log.info("Review Submitted.");
			Assert.assertTrue(true, "Response Message Validation");

		} else {
			log.error("Something's wrong happened while posting review");
			Assert.assertFalse(false, "Response Message Validation");
		}

		log.debug("Click Continue.");
		companyProfile.getContinue().click();
		log.debug("Navigating to Profile.");
		driver.navigate().to(profile);
		driver.navigate().refresh();

		WalletHubUserProfile userProfile = new WalletHubUserProfile(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rvs-star-svg")));

		WebElement el = userProfile.getCompany();
		String company = el.getText();
		System.out.println(company);

		if (company.equalsIgnoreCase("Test Insurance Company")) {

			log.info("Company Present:" + company);
			List<WebElement> els = driver.findElements(By.cssSelector(".rvs-star-svg"));
			int count = 0;
			for (int i = 0; i < els.size(); i++) {
				String star = els.get(i).getAttribute("aria-label");
				WebElement el2 = els.get(i).findElement(By.cssSelector("g path"));
				String fill = el2.getAttribute("fill");
				if (fill.equalsIgnoreCase("#4ae0e1")) {
					log.info(star + " is selected");
					count++;
				}
			}
			if (count == 4) {
				log.info("PASS: Four Stars are selected");
				Assert.assertTrue(true, "Review Stars Verification");
			} else {
				log.info("Review Stars not Matched");
				Assert.assertFalse(false, "Review Stars Verification");
			}
		}

		else {
			log.error("Review not Found");
			Assert.assertFalse(false, "Company Name Verification");
		}
		driver.quit();
	}
}
