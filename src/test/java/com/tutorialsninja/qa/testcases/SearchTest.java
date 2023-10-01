package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base
{
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;
	
	public SearchTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct());
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		searchPage = homePage.searchForAProduct(dataProp.getProperty("inValidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("noProductTextInSearchResult"),"No product message in search result is displayed");
	}
	
	@Test(priority = 3, dependsOnMethods ="verifySearchWithInvalidProduct" )
	public void verifySearchWithoutAnyProduct()
	{
		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("noProductTextInSearchResult"),"No product message in search result is displayed");
	}
}
