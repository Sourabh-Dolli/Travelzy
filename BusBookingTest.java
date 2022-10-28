package com.test;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.pages.BusBookingPageFactory;

public class BusBookingTest {
	WebDriver driver;
	BusBookingPageFactory bb;
	
	File f;
	FileInputStream fis;
	Properties p;
	File f2;
	FileInputStream fis2;
	Properties l;
	
	String configfpath="/home/satya/eclipse-workspace/IBIBO_CaseStudy/src/test/resources/configPage.properties";
	String busfpath="/home/satya/eclipse-workspace/IBIBO_CaseStudy/src/test/resources/busBookingElemenets.properties";
	
	
	@BeforeTest
	public void beforeTest() throws IOException {
		
		f=new File(configfpath);
		fis=new FileInputStream(f);
		p=new Properties();
		p.load(fis);
		
		
		//properties for bus page
		f2=new File(busfpath);
		fis2=new FileInputStream(f2);
		l=new Properties();
		l.load(fis2);
		
		
		System.setProperty(p.getProperty("chromeDriver"), p.getProperty("chromeDriverPath"));
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		bb = new BusBookingPageFactory(driver);
	}
	
	
	@Test(priority = 1)
	public void validateWithNoData() {
		bb.clickBusButton();
		bb.clickSearchButton();
		Assert.assertEquals(bb.validateSrcErr(), l.getProperty("errorSrc"));
		Assert.assertEquals(bb.validateDestErr(), l.getProperty("errorDest"));
	}
	
	@Test(priority = 2)
	public void validateWithoutDest() {
		bb.srcDetails();
		bb.clickSearchButton();
		Assert.assertEquals(bb.validateDestErr(), l.getProperty("errorDest"));
		bb.clearSrc();
		bb.clearDest();
	}
	
	@Test(priority = 3)
	public void validatePastTravelDate() {
		String str = bb.dateText();
		bb.setDuplicateTravelDate();
		Assert.assertEquals(bb.dateText(), str);
		bb.setTodayDate();
		bb.clearSrc();
		bb.clearDest();
	}
	
	@Test(priority = 4)
	public void validateInvalidSeat() {
		bb.srcDetails();
		bb.destDetails();
		bb.clickTravelDate();
		bb.afterMonth();//to change
		bb.setTravelDate();
		bb.clickSearchButton();
		bb.clickShowBuses();
		bb.clickSelectSeat();
		bb.setBoardingPlace();
		String str = bb.validateContinue();
		try {
			bb.setDuplicateSeat();
		}
		finally {
			Assert.assertEquals(bb.validateContinue(), str);
		}
	}
	
	@Test(priority = 5)
	public void validateBoardingAndDroppingPoint() {
		bb.setSeat();
		//bb.setDroppingPlace();
		bb.setBoardingPlace();
		bb.clickContinue();
		Assert.assertEquals(bb.boardnDropError(), l.getProperty("errorBoardAndDropPoint"));
	}
	
	@Test(priority = 6)
	public void validateWithNoUserDetails() {
		bb.setBoardingPlace();
		bb.clickContinue();
		bb.clickPayButton();
		Assert.assertEquals(bb.fullNameError(), l.getProperty("ErrorNoName"));
		Assert.assertEquals(bb.ageError(), l.getProperty("ErrorNoAge"));
		Assert.assertEquals(bb.genderError(), l.getProperty("ErrorNogender"));
		Assert.assertEquals(bb.emailError(), l.getProperty("ErrorNoEmail"));
		Assert.assertEquals(bb.mobileNumberError(), l.getProperty("ErrorNoNumber"));
	}
	
	@Test(priority = 7)
	public void validateInvalidFullName() {
		bb.setFullName("Sai123");
		bb.setAge("20");
		bb.setGender();
		bb.setEmail("a@gmail.com");
		bb.setMobileNumber("7032896621");
		bb.clickPayButton();
		Assert.assertEquals(bb.fullNameError(), l.getProperty("ErrorInvalidName"));
		
	}
	
	@Test(priority = 8)
	public void validateMobileNumberWithLessDigits() {
		bb.clearFullName();
		bb.setFullName("Sai");
		bb.clearAge();
		bb.setAge("21");
		bb.clearEmail();
		bb.setEmail("b@gmail.com");
		bb.clearMobileNumber();
		bb.setMobileNumber("70328966");
		bb.setGender();
		bb.clickPayButton();
		Assert.assertEquals(bb.mobileNumberError(), l.getProperty("ErrorInvalidNumber"));
		
	}
	
	
	@Test(priority = 9)
	public void validateInvalidEmailID() {
		bb.clearFullName();
		bb.setFullName("Saai");
		bb.clearAge();
		bb.setAge("23");
		bb.clearEmail();
		bb.setEmail("abc");
		bb.clearMobileNumber();
		bb.setMobileNumber("7032896622");
		bb.setGender();
		bb.clickPayButton();
		Assert.assertEquals(bb.emailError(), l.getProperty("ErrorInvalidEmail"));
		
	}
	
	@Test(priority = 10)
	public void validateInvalidEmailIDWithCorrectFormat() {
		bb.clearFullName();
		bb.setFullName("Saai");
		bb.clearAge();
		bb.setAge("23");
		bb.clearEmail();
		bb.setEmail("abc");
		bb.clearMobileNumber();
		bb.setMobileNumber("7032896622");
		bb.setGender();
		//bb.clickPayButton();
		
	}
	
	@Test(priority = 11)
	public void validateInvalidMobileNumber() {
		bb.clearFullName();
		bb.setFullName("Satya");
		bb.clearAge();
		bb.setAge("22");
		bb.clearEmail();
		bb.setEmail("c@gmail");
		bb.clearMobileNumber();
		bb.setMobileNumber("1234567890");
		bb.setGender();
		//bb.clickPayButton();		
	}
	
	@Test(priority = 12)
	public void validatevalidData() {
		bb.clearFullName();
		bb.setFullName("Saaai");
		bb.clearAge();
		bb.setAge("24");
		bb.clearEmail();
		bb.setEmail("hi@gmail.com");
		bb.clearMobileNumber();
		bb.setMobileNumber("7032896623");
		bb.setGender();
		/*bb.clickPayButton();
		try {
			bb.clickCross();
		}
		finally {
			System.out.println(driver.getTitle());
		}*/
	}
		
	
	@AfterTest
	public void afterTest() throws InterruptedException {
		//Thread.sleep(1000);
		//driver.close();
	}
	
}







