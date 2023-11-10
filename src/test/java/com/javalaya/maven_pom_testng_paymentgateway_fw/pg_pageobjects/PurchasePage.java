package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PurchasePage {

	WebDriver driver;
	
	public static int pricePerToy;

	public PurchasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectQuantity(String n) {

		WebElement quantityDD = driver.findElement(By.xpath("//select[@name='quantity']"));
		quantityDD.click();

		List<WebElement> quantities = driver.findElements(By.xpath("//select[@name='quantity']/option"));
		for (WebElement t : quantities) {
			if (t.getText().equalsIgnoreCase(n)) {
				t.click();
			}
		}
	}

	public void getPrice() {
		WebElement price = driver.findElement(By.tagName("h3"));
		String p[] = price.getText().split("\\$");// Price: $20
		pricePerToy = Integer.valueOf(p[1]);
	}
	
	public ProcessPurchasePage clickBuyNow() {
		WebElement bn = driver.findElement(By.xpath("//input[@type='submit']"));
		bn.click();
		
		return new ProcessPurchasePage(driver);
	}

}
