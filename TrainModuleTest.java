package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.trainModulePageFactory;

public class TrainModuleTest {
	WebDriver driver;
	trainModulePageFactory tbp;
	
	File f;
	FileInputStream fis;
	Properties p;
	File f2;
	FileInputStream fis2;
	Properties l;
	
	String fpath="C:\\Users\\lenovo\\eclipse-workspace\\IBIBO_CaseStudy\\src\\test\\resources\\configPage.properties";
	String fpathForTrain="C:\\Users\\lenovo\\eclipse-workspace\\IBIBO_CaseStudy\\src\\test\\resources\\trainModule.properties";
	
	@BeforeTest
	public void beforeTest() throws IOException {
		
		//properties for config page
				f=new File(fpath);
				fis=new FileInputStream(f);
				p=new Properties();
				p.load(fis);
				
				
				//properties for login page
				f2=new File(fpathForTrain);
				fis2=new FileInputStream(f2);
				l=new Properties();
				l.load(fis2);	
	
	

//	System.setProperty("webdriver.chrome.driver", "F:\\selenium\\chromedriver\\chromedriver.exe");
	System.setProperty(p.getProperty("chromeDriver"),p.getProperty("chromeDriverPath"));
	driver = new ChromeDriver();

	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get(l.getProperty("url"));
	//driver.get("https://www.goibibo.com/trains/");
	tbp = new trainModulePageFactory(driver);
	}
	
	
	
	@Test(priority = 1)
	public void validateSearchwithoutAnyData() {
	
	tbp.searchButton();
	Assert.assertEquals(tbp.getErrorMsg1(), l.getProperty("errorSearchWithoutData"));
//	Assert.assertEquals(tbp.getErrorMsg1(), "Please select valid Source Station and valid Destination Station");
	}
	
	
	

	@Test(priority = 2)
	public void validateSearchwithoutSource() throws InterruptedException {
	//tbp.clearSource();
	tbp.setDestination("Kolkata");
	tbp.searchButton();
	Assert.assertEquals(tbp.getErrorMsg1(), l.getProperty("errorSearchWithoutSource"));
//	Assert.assertEquals(tbp.getErrorMsg1(), "Please select valid Source Station");
	}
	
	
	
	@Test(priority = 3)
	public void validData() throws InterruptedException {
	tbp.setSource("Patna");
//	tbp.setDestination("Kolkata");
	tbp.selectDate();
	tbp.searchButton();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//*[@id=\"menu0\"]/li[1]/div")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	
	}
	
	@Test(priority = 4)
	public void addwithoutAnyData() {
	
		tbp.clickBookNowBtn();
		Assert.assertEquals(tbp.getErrId(), l.getProperty("errorId"));
		Assert.assertEquals(tbp.getuserMobileErr(), l.getProperty("errorUserMobile"));
		Assert.assertEquals(tbp.getTravellerErr(), l.getProperty("errorTravellerSection"));

	}
	/*
	@Test(priority = 5)
	public void addInvalidUserId() {
		tbp.setUserId("abc");
		tbp.clickVerifyIdBtn();
		Assert.assertEquals(tbp.getInvalidUserIdErr(), l.getProperty("errorInvalidId"));
//	Assert.assertEquals(tbp.getInvalidUserIdErr(), "Invalid IRCTC ID");
	
	}
	*/
	
	
	@Test(priority = 6)
	public void addInvalidMobileNumber() {
		
		tbp.setUserNumber("abcd");
		tbp.clickBookNowBtn();
		//tbp.clickVerifyIdBtn();
		Assert.assertEquals(tbp.getuserMobileErr(), l.getProperty("errorInvalidMobile"));
//	Assert.assertEquals(tbp.getuserMobileErr(), "Please provide a valid 10 digit Mobile Number");
	
	}
	@Test(priority = 7)
	public void clickSaveTravellerWithoutInput() throws InterruptedException {
		tbp.clickAddTravellersBtn();
		Thread.sleep(3000);
		tbp.clickSaveTravellerInfoBtn();
		Thread.sleep(3000);
		Assert.assertEquals(tbp.getTravellerNameErr(), l.getProperty("errorTravellerName"));
		Assert.assertEquals(tbp.getTravellerAgeErr(), l.getProperty("errorTravellerAge"));
//	Assert.assertEquals(tbp.getTravellerNameErr(), "Name is a required field");
//	Assert.assertEquals(tbp.getTravellerAgeErr(), "Age is a required field");
	
	}
	@Test(priority = 8)
	public void addTravellerNameOnly() throws InterruptedException {
		tbp.setTravellerName("nikkita");
		tbp.clickSaveTravellerInfoBtn();
		Thread.sleep(3000);
		Assert.assertEquals(tbp.getTravellerAgeErr(),l.getProperty("errorTravellerAge"));
		tbp.clearTravellerName();
//	Assert.assertEquals(tbp.getTravellerAgeErr(), "Age is a required field");
	
	}
	
	@Test(priority = 9)
	public void addInvalidAgeOnly() throws InterruptedException {
		tbp.setTravellerName("nikkita");
		tbp.setTravellerAge("-4");
		tbp.clickSaveTravellerInfoBtn();
		Thread.sleep(2000);
		tbp.clickSaveTravellerInfoBtn();
		Assert.assertEquals(tbp.getTravellerAgeErr(),l.getProperty("errorNegativeAge"));
		tbp.clearTravellerName();
		tbp.clearTravellerAge();
//	Assert.assertEquals(tbp.getTravellerAgeErr(), "Age is a required field");
	
	}

	@Test(priority = 10)
	public void addValidTraveller() {
		//tbp.clearTravellerName();
		tbp.clearTravellerAge();
		tbp.setTravellerName("nikita");
		tbp.setTravellerAge("22");
		tbp.clickSaveTravellerInfoBtn();
	}
	@Test(priority = 11)
	public void addValidData() throws InterruptedException {
		tbp.clearUserId();
		tbp.setUserId("Montyli123");
		tbp.clickVerifyIdBtn();
		Thread.sleep(3000);
		tbp.clearUserNumber();
		tbp.setUserNumber("9330312539");
		tbp.setUserEmail("nikitarani2305@gmail.com");
		//driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section[2]/section/section[1]/div[2]/div/div[2]/div[2]/div[2]/div[2]")).click();
		tbp.clickBookNowBtn();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	
	
	
	@AfterMethod
	public void afterMethod() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	
}
