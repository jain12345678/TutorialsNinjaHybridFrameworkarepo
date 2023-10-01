package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage loginPage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLogin();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test(priority=1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String Email, String Password)
	{
		AccountPage accountPage = loginPage.login(Email, Password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "user id or password not matched");
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data = Utilities.getTestDataFromExcelSheet("Login");
		return data;
	}
	
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidpassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordnotmatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNotMatchWarning")),"Expected warining message not displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordnotmatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNotMatchWarning")),"Expected warining message not displayed");
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidpassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordnotmatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNotMatchWarning")),"Expected warining message not displayed");
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveEmailPasswordnotmatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNotMatchWarning")),"Expected warining message not displayed");
	}
	
}
