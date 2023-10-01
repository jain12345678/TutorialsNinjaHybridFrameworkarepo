/**
 * 
 */
package com.mukeshotwani.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tutorialsninja.qa.base.Base;

/**
 * 
 */
public class LoginPage extends Base {
	
WebDriver driver;

	public LoginPage()
	{
		super();
	}
	
	By username = By.id("input-email");
	By password = By.xpath("//input[@id='input-password']");
	By loginButton = By.xpath("//input[@type='submit']");
	
	public LoginPage(WebDriver driver)
	{
		this.driver= driver;
	}
	
	public void typeUserName() 
	{
		driver.findElement(username).sendKeys(prop.getProperty("validEmail"));
	}
	
	public void typePassword()
	{
		driver.findElement(password).sendKeys(prop.getProperty("validPassword"));
	}
	
	public void clickLoginButton()
	{
		driver.findElement(loginButton).click();
	}

}
