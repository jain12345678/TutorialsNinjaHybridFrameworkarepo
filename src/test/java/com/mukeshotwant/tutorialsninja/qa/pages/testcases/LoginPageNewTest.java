package com.mukeshotwant.tutorialsninja.qa.pages.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mukeshotwani.tutorialsninja.qa.pages.LoginPageNew;
import com.tutorialsninja.qa.base.Base;

public class LoginPageNewTest extends Base {
	
	WebDriver driver;
	
	public LoginPageNewTest()
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
	public void login()
	{
		LoginPageNew login_page_new = PageFactory.initElements(driver, LoginPageNew.class);
		//LoginPage login_page_old = PageFactory.initElements(driver, LoginPage.class);
		
		login_page_new.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}

}
