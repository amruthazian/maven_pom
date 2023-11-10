package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BalanceStatementPage {
	
	WebDriver driver;
	
	public BalanceStatementPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public int getCardBalance() {
		WebElement cBal = driver.findElement(By.xpath("//h4/span"));
		
		return Integer.valueOf(cBal.getText());
	}
	
	public int getOrderId() {
		WebElement idNum = driver.findElement(By.xpath("//table[@class='alt']//tbody//td[6]"));
		
		return Integer.valueOf(idNum.getText());
		
	}

}
