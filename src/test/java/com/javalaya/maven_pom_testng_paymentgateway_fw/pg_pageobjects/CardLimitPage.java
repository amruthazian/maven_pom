package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CardLimitPage {
	
	WebDriver driver;
	
	public CardLimitPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, driver);
	}
	
	public void enterCardNumber() {
		WebElement cardNum = driver.findElement(By.id("card_nmuber"));
		cardNum.clear();
		cardNum.sendKeys(CardNumberPage.carddetail.get("Card Number"));
	}
	
	public BalanceStatementPage clickSubmit() {
		WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		submitBtn.click();
		
		return new BalanceStatementPage(driver);
	}

}
