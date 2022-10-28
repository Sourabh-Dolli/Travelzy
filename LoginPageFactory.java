package com.pages;



import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPageFactory {
	
	WebDriver driver;
	
		
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div[1]/div[1]/header/div[2]/div/div")
	WebElement signUpBtn;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"get_sign_in\"]/div/div/p[5]")
	WebElement number;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/div/div[1]/p")
	WebElement getError;
	
	@FindBy(how=How.CLASS_NAME,using="loginCont__btn")
	WebElement continueBtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/div/div[1]/h3")
	WebElement loginBtn;
	
	String cmpContinueBtn="loginCont__btn";
	String cmpLoginBtn="//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div/div[2]/div/div[1]/h3";
	
	//constructor
		public LoginPageFactory(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
		}
		
		//methods
		
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
				//WebDriverWait wait = new WebDriverWait(driver, 20);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"get_sign_in\"]/div/div/p[5]"))).sendKeys(un);
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
