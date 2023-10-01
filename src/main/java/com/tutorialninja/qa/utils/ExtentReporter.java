package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter 
{
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Result");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		
		Properties prop= new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"//src//main//java//com//tutorialsninja//config//Config.properties");
		
		try 
		{
		FileInputStream fis = new FileInputStream(configPropFile);
		prop.load(fis);
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browserName"));
		extentReport.setSystemInfo("Email", prop.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("java Version", System.getProperty("java.version"));
		
		return extentReport;
	}

}
