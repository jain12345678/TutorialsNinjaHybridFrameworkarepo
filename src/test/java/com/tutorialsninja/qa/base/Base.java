package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public  Base()
	{
		//properties file for valid data
		prop= new Properties();
		File propFile = new File(System.getProperty("user.dir")+ "//src//main//java//com//tutorialsninja//config//Config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			}catch(Throwable e) {
			e.printStackTrace();
			}
		
		//properties file for invalid data
		dataProp= new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"//src//main//java//com//tutorialsninja//qa//testdata//testdata.properties");
		try {
			FileInputStream dataFis= new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		
	}	
		
	public WebDriver initializeBrowserAndOpenApplicationURL(String browsername)
	{
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if (browsername.equalsIgnoreCase("firefox")) {
			driver= new FirefoxDriver();
		} 
		else if (browsername.equalsIgnoreCase("safari")) {
			driver= new SafariDriver();
		} 
		else if (browsername.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait_time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.page_load_time));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
