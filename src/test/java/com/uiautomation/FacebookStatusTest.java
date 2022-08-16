package com.uiautomation;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.Base;

public class FacebookStatusTest extends Base {

	private static Logger log = LogManager.getLogger(FacebookStatusTest.class.getName());

	@Test
	public void postFacebookStatus() throws IOException, InterruptedException {

		driver = initializeDriver();
		log.debug("Waiting for Facebook...");
		driver.get("https://www.facebook.com");

		// Reading Property File to fetch the email & password
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/parameters.properties");
		prop.load(fis);

		LoginPage lp = new LoginPage(driver);
		log.debug("Entering Username.");
		lp.getEmail().sendKeys(prop.getProperty("email"));
		log.debug("Entering Password.");
		lp.getPassword().sendKeys(prop.getProperty("password"));
		log.debug("Click Sign-in.");
		lp.getLogin().click();

		HomePage hp = new HomePage(driver);
		hp.getSectionEnable().click();
		log.debug("Entering Status Message.");
		hp.getInput().sendKeys("Hello World! " + Math.random());
		log.debug("Posting Status.");
		hp.getPost().click();
		log.info("Status Posted");
		driver.quit();

	}
}