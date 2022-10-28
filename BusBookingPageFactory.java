package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusBookingPageFactory {
	WebDriver driver;
	
	//Locating Elements
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/header/ul/li[5]/a")
	WebElement busButton;
	
	@FindBy(xpath = "//*[@id=\"autosuggestBusSRPSrcHome\"]")
	WebElement src;
	
	@FindBy(xpath = "//*[@id=\"downshift-1-item-0\"]")
	WebElement srcSuggestion;
	
	@FindBy(xpath = "//*[@id=\"autosuggestBusSRPDestHome\"]")
	WebElement dest;
	
	@FindBy(xpath = "//*[@id=\"downshift-2-item-0\"]")
	WebElement destSuggestion;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[5]/button")
	WebElement searchBusButton;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[1]/div/label")
	WebElement srcErr;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[2]/div/label")
	WebElement destErr;
	
	@FindBy(xpath = "//*[@id=\"srpCardLayoutinner\"]/div[3]/div[1]/div[3]/div/div[2]/div/div/button")
	WebElement showBusesButton;
	
	@FindBy(xpath = "/html/body/div[1]/section/section/section[1]/section/div[3]/div/input")
	WebElement travelDate;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[3]/div/div[1]/div/div/div[2]/div/div/ul[2]/li[23]/span")
	WebElement date;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[3]/div/div[1]/div/div/div[2]/div/div/ul[2]/li[20]/span")
	WebElement duplicateDate;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[3]/div/div[1]/div/div/div[2]/div/div/ul[2]/li[30]/span")
	WebElement today;//to change
	
	@FindBy(xpath = "//*[@id=\"srpCardLayoutinner\"]/div[3]/div[2]/div[2]/div[4]/div/div[2]/div/div/button")
	WebElement selectSeatButton;
	
	@FindBy(xpath = "//*[@id=\"seatSectionredbusnew1000005742805336527\"]/div/section[1]/div/div[2]/div[1]/div/label[1]")
	WebElement boarding;//to change
	
	@FindBy(xpath = "//*[@id=\"seatSectionredbusnew1000005742755336539\"]/div/section[1]/div/div[2]/div[2]/div/label")
	WebElement dropping;//to change
	
	@FindBy(xpath = "//*[@id=\"seatSectionredbusnew1000005742805336527\"]/div/section[2]/section/div[2]/div[3]/aside[2]/div/div[47]/div")
	WebElement seat;//to change
	
	@FindBy(xpath = "//*[@id=\"seatSectionredbusnew1000005742805336527\"]/div/section[2]/section/div[2]/div[3]/aside[2]/div/div[8]/div")
	WebElement duplicateSeat;//to change
	
	@FindBy(xpath = "//*[@id=\"seatSectionredbusnew1000005742805336527\"]/div/section[2]/section/div[2]/button")
	WebElement continueButton;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[3]/div/div[1]/div/div/div[1]/div/div[2]/div")
	WebElement rightarrow;//to delete
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/section[1]/section/div[3]/div/div[1]/div/div/div[1]/div/div[1]/div")
	WebElement leftarrow;//to delete
	
	@FindBy(xpath = "//*[@id=\"seatViewredbusnew1000005742805336527\"]/span[2]")
	WebElement boardnDropErr;
	
	@FindBy(xpath = "//*[@id=\"reviewBlock\"]/button")
	WebElement payButton;
	
	@FindBy(xpath = "//*[@id=\"travellerForm1\"]/div/div/div[1]/div[1]/input")
	WebElement fullName;
	
	@FindBy(xpath = "//*[@id=\"travellerForm1\"]/div/div/div[1]/div[2]/input")
	WebElement age;
	
	@FindBy(xpath = "//*[@id=\"travellerForm1\"]/div/div/div[1]/div[3]/ul/li[1]")
	WebElement gender;
	
	@FindBy(xpath = "//*[@id=\"TravllerContactBlock\"]/div/div[1]/input")
	WebElement email;
	
	@FindBy(xpath = "//*[@id=\"TravllerContactBlock\"]/div/div[2]/div/input")
	WebElement mobileNumber;
	
	@FindBy(xpath = "//*[@id=\"travellerForm1\"]/div/div/div[1]/div[1]/p")
	WebElement fullNameErr;
	
	@FindBy(xpath = "//*[@id=\"travellerForm1\"]/div/div/div[1]/div[2]/p")
	WebElement ageErr;
	
	@FindBy(xpath = "//*[@id=\"travellerForm1\"]/div/div/div[1]/div[3]/p")
	WebElement genderErr;
	
	@FindBy(xpath = "//*[@id=\"TravllerContactBlock\"]/div/div[1]/p")
	WebElement emailErr;
	
	@FindBy(xpath = "//*[@id=\"TravllerContactBlock\"]/div/div[2]/p")
	WebElement mobileNumberErr;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section[1]/div/div[1]/span")
	WebElement cross;
	
	//constructor
	public BusBookingPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Methods
	public void clickBusButton() {
		busButton.click();
	}
	
	public void clickSearchButton() {
		searchBusButton.click();
	}
	
	public String validateSrcErr() {
		return srcErr.getText();
	}
	
	public String validateDestErr() {
		return destErr.getText();
	}
	
	public void srcDetails() {
		src.sendKeys("Hyderabad");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(srcSuggestion)).click();
	}
	
	public void destDetails() {
		dest.sendKeys("Bangalore");
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.visibilityOf(destSuggestion)).click();
	}
	
	public void clearSrc() {
		src.clear();
	}
	
	public void clearDest() {
		dest.clear();
	}
	
	public void clickTravelDate() {
		travelDate.click();
	}
	
	public void setTravelDate() {
		date.click();
	}
	
	public void setDuplicateTravelDate() {
		travelDate.click();
		duplicateDate.click();
	}
	
	public String dateText() {
		return travelDate.getText();
	}
	
	public void clickShowBuses() {
		showBusesButton.click();
	}
	
	public void setTodayDate() {
		today.click();
	}
	
	public void clickSelectSeat() {
		selectSeatButton.click();
	}
	
	public void setBoardingPlace() {
		boarding.click();
	}
	
	public void setDroppingPlace() {
		dropping.click();
	}
	
	public void setSeat() {
		seat.click();
	}
	
	public void setDuplicateSeat() {
		duplicateSeat.click();
	}
	
	public String validateContinue() {
		return continueButton.getText();
	}
	
	public void clickContinue() {
		continueButton.click();
	}
	
	//to remove
	public void afterMonth() {
		rightarrow.click();
	}
	
	public void previousMonth() {
		leftarrow.click();
	}
	
	public String boardnDropError() {
		return boardnDropErr.getText();
	}
	
	public void clickPayButton() {
		payButton.click();
	}
	
	public void setFullName(String fn) {
		fullName.sendKeys(fn);
	}
	
	public void setAge(String n) {
		age.sendKeys(n);
	}
	
	public void setGender() {
		gender.click();
	}
	
	public void setEmail(String e) {
		email.sendKeys(e);
	}
	
	public void setMobileNumber(String mn) {
		mobileNumber.sendKeys(mn);
	}
	
	public String fullNameError() {
		return fullNameErr.getText();
	}
	
	public String ageError() {
		return ageErr.getText();
	}
	
	public String genderError() {
		return genderErr.getText();
	}
	
	public String emailError() {
		return emailErr.getText();
	}
	
	public String mobileNumberError() {
		return mobileNumberErr.getText();
	}
	
	public void clearFullName() {
		fullName.clear();
	}
	
	public void clearAge() {
		age.clear();
	}
	
	public void clearEmail() {
		email.clear();
	}
	
	public void clearMobileNumber() {
		mobileNumber.clear();
	}
	
	public void clickCross() {
		cross.click();
	}
	
}








