package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import com.pages.LoginPageFactoryDemo;

import au.com.bytecode.opencsv.CSVReader;

public class LoginTest {

	WebDriver driver;
	LoginPageFactoryDemo loginPg;
	
	File f;
	FileInputStream fis;
	Properties p;
	File f2;
	FileInputStream fis2;
	Properties l;
	
	String fpathcsv="C:\\Users\\lenovo\\eclipse-workspace\\IBIBO_CaseStudy\\src\\test\\resources\\loginData.csv";
	CSVReader reader;
	String[] values;
	
	String fpath="C:\\Users\\lenovo\\eclipse-workspace\\IBIBO_CaseStudy\\src\\test\\resources\\configPage.properties";
	String fpathForLogin="C:\\Users\\lenovo\\eclipse-workspace\\IBIBO_CaseStudy\\src\\test\\resources\\loginPage.properties";

	@BeforeTest
	public void beforeTest() throws IOException {
		
		
		f=new File(fpath);
		fis=new FileInputStream(f);
		p=new Properties();
		p.load(fis);
		
		
		//properties for login page
		f2=new File(fpathForLogin);
		fis2=new FileInputStream(f2);
		l=new Properties();
		l.load(fis2);
		
		System.setProperty(p.getProperty("chromeDriver"),p.getProperty("chromeDriverPath"));
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		loginPg = new LoginPageFactoryDemo(driver);
		loginPg.signUpBtn();
		reader=new CSVReader(new FileReader(fpathcsv));
	
	}
	
	@Test
	public void login() throws IOException, InterruptedException {
		while((values=reader.readNext())!=null) {
			String phnNumber=values[0];
			String err=values[1];
			loginPg.clearNumber();
			loginPg.setMobileNumber(phnNumber);
			loginPg.clickbtn();
			if(phnNumber !="9330312539") {
				String gterr=loginPg.getError();
				Assert.assertEquals(gterr, err);
				
			}
			
			
		}
		
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/span/span")).click();
	}
	
	/*
	
	
	@Test(priority=2)
	public void mobileNumberLessThanTenDigit() throws InterruptedException {
		loginPg.setMobileNumber("1234");
		loginPg.clickbtn();
		String gterr=loginPg.getError();
		Assert.assertEquals(gterr, l.getProperty("errorLessThanTenDigit"));
		loginPg.clearNumber();

		
	}
//	
//	@Test(priority=3)
//	public void mobileNumberMoreThanTenDigit() {
//		loginPg.setMobileNumber("12345678912");
//		loginPg.clickbtn();
//		
//	}
	
	@Test(priority=4)
	public void invalidMobileNumberWithDigit() {
		loginPg.setMobileNumber("12345678912");
		loginPg.clickbtn();
		String gterr=loginPg.getError();
		Assert.assertEquals(gterr,l.getProperty("errorInvalidMobileNumber"));
		loginPg.clearNumber();

//		Assert.assertEquals(gterr,"Please enter a valid mobile number");
		
	}
	@Test(priority=5)
	public void invalidMobileNumberWithAlphaNumericCharacter() {
		loginPg.setMobileNumber("abcd");
		loginPg.clickbtn();
		String gterr=loginPg.getError();
		Assert.assertEquals(gterr, l.getProperty("errorAlphanumericCharacter"));
		loginPg.clearNumber();

//		Assert.assertEquals(gterr, "Alphabets and Special Characters are not allowed");
		
	}
	@Test(priority=6)
	public void validMobileNumber() throws InterruptedException {
		loginPg.setMobileNumber("9330312539");
		loginPg.clickbtn();
		Thread.sleep(3000);
		loginPg.clearNumber();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/span/span")).click();
		
		
	}
	
//	@AfterMethod
//	public void AfterMethod() {
//		loginPg.clearNumber();
//	}
	*/
	
	
	
}
