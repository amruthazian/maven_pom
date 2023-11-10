package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardNumberPage {
	
	WebDriver driver;
	
	public static HashMap<String, String> carddetail = new HashMap<>();
	
	
	public CardNumberPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void fetchCardDetails() {
		
		List<WebElement> cardDetails = driver.findElements(By.xpath("//p[@style]/../h4"));
		
		for(WebElement t : cardDetails) {
			if(t.getText().contains(":-")) {
			String a[] = t.getText().split(":- ");
			carddetail.put(a[0], a[1]);
			a = null;
			}else {
				String a[] = t.getText().split(" \\$");
				carddetail.put(a[0] ,a[1]);
				a = null;
			}
		}	
		//System.out.println(carddetail);
	}
	
	public PurchasePage clickCart() {
		WebElement cartBtn = driver.findElement(By.linkText("Cart"));
		cartBtn.click();
		
		return new PurchasePage(driver);
	}
}
