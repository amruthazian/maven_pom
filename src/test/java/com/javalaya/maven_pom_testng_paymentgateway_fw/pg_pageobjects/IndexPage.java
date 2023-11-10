package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "Generate Card Number")
	private WebElement cardNumberLink;
	
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public CardNumberPage clickCardDetails() {
		cardNumberLink.click();
		
		return new CardNumberPage(driver);
	}

}
