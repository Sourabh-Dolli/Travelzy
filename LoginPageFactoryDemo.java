package com.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactoryDemo {
	 WebDriver driver;
	 @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[1]/header/div[2]/div/div")
	 WebElement signUpBtn;

	 @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/div/div[1]/form/div/input")
	 WebElement number;

	 @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/div/div[1]/p")
	 WebElement getError;

	 @FindBy(className = "loginCont__btn")
	 WebElement continueBtn;
	 
	 @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/div/div[1]/h3")
	 WebElement loginBtn;

	//constructor
			public LoginPageFactoryDemo(WebDriver driver) {
				this.driver=driver;
				PageFactory.initElements(driver,this);
				
			}
			//method of comparing two webelements
			public boolean compare() {
				if(continueBtn==loginBtn) {
					return true;
				}else {
					return false;
				}
			}	
			//method of entering user number
			public void setMobileNumber(String un) {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				number.sendKeys(un);
			}
			
			//method of clearing user number
			public void clearNumber() {
				number.clear();
			}
		
				
			//method to click on continue button
			public void clickbtn() {
				continueBtn.click();
			}
			
			//method to click on signup btn
			public void signUpBtn() {
				
				signUpBtn.click();
			}
			
			//method to get error 
			public  String getError() {
				return getError.getText();
			}
	
}
