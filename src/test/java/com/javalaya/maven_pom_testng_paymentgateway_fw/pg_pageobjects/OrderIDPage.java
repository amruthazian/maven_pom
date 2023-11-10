package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderIDPage {
	
	WebDriver driver;
	
	public static int orderid;
	
	public OrderIDPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPaymentSuccessfullMsg() {
		WebElement smsg = driver.findElement(By.xpath("//h2[normalize-space()='Payment successfull!']"));
		return smsg.getText();
	}

	public void getOrderId() {
		WebElement id = driver.findElement(By.xpath("//td[2]/h3/strong"));
		orderid = Integer.valueOf(id.getText());
	}
	
	public CardLimitPage clickCardLimit() {
		WebElement limit = driver.findElement(By.linkText("Check Credit Card Limit"));
		limit.click();
		
		return new CardLimitPage(driver);
	}

}
