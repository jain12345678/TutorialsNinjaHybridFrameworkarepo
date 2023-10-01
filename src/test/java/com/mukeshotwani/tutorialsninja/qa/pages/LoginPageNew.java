package com.mukeshotwani.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageNew {
	
	WebDriver driver;
	
	public LoginPageNew(WebDriver ldriver)
	{
		this.driver=ldriver;
		
	}
	
	@FindBy(id="input-email")
	@CacheLookup
	WebElement username;
	
	@FindBy(how = How.ID, using="input-password")
	@CacheLookup// it will store element in cache memory. it will not search webelement every time on web page.
	WebElement password;
	
	@FindBy(how= How.XPATH, using="//input[@type='submit']")
	@CacheLookup
	WebElement 	login_button;
	
	@FindBy(how= How.LINK_TEXT, using="Forgotten Password")
	@CacheLookup
	WebElement forget_password;
	
	public void login(String uid, String pass)
	{
		username.sendKeys(uid);
		password.sendKeys(pass);
		login_button.click();
	}
	

}
