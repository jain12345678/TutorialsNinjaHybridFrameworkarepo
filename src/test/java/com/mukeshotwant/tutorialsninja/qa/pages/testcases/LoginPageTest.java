/**
 * 
 */
package com.mukeshotwant.tutorialsninja.qa.pages.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mukeshotwani.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.base.Base;

/**
 * 
 */
public class LoginPageTest extends Base{
	
	WebDriver driver;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@Test
	public void verifyValidLogin() 
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.typeUserName();
		loginpage.typePassword();
		loginpage.clickLoginButton();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
