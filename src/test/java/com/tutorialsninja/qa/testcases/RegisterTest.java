package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base
{
	public WebDriver driver;
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegistringAnAccountWithMandatoryFieldsTest()
	{
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageheading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success page is not displayed");
	}
	
	@Test(priority=2)
	public void verifyRegistringAnAccountByProvidingAllFields()
	{
	accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
	Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageheading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success page is not displayed");
	//dataProp.getProperty("accountSuccessfullyCreatedHeading")
	}
	
	@Test(priority=3)
	public void verifyRegistringAccountWithExistingEmailAddress()
	{
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retirieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"warning message regaring duplicate email address not displayed");
	}
	
	@Test(priority=4)
	public void verifyRegistringAccountWithoutFillingAnyDetails()
	{		
		registerPage.clickOnContinueButton();
		
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy message not displayed");
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"),"first name warning message is not displayed");
		Assert.assertEquals(registerPage.retrieveLastNameWarning(), dataProp.getProperty("lastNameWarning"),"Last name warning message is not displayed");
		}
}
