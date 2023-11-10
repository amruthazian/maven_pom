package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProcessPurchasePage {

	WebDriver driver;

	String c[] = CardNumberPage.carddetail.get("Exp").split("/");
	
	public static int payableAmt;

	public ProcessPurchasePage(WebDriver driver) {
		this.driver = driver;
	}

	public ProcessPurchasePage enterCardNumer() {
		WebElement cn = driver.findElement(By.xpath("//input[@name='card_nmuber']"));
		cn.clear();
		cn.sendKeys(CardNumberPage.carddetail.get("Card Number"));

		return this;
	}

	public ProcessPurchasePage selectMonth() {

		WebElement month = driver.findElement(By.xpath("//select[@name='month']"));
		month.click();

		List<WebElement> monthDD = driver.findElements(By.xpath("//select[@name='month']/option"));
		for (WebElement t : monthDD) {
			if (t.getText().equals(c[0])) {
				t.click();
			}
		}

		return this;
	}

	public ProcessPurchasePage selectYear() {

		WebElement year = driver.findElement(By.xpath("//select[@name='year']"));
		year.click();

		List<WebElement> yearDD = driver.findElements(By.xpath("//select[@name='year']/option"));
		for (WebElement t : yearDD) {
			if (t.getText().equals(c[1])) {
				t.click();
			}
		}

		return this;
	}

	public ProcessPurchasePage enterCVV() {
		WebElement cvv = driver.findElement(By.xpath("//input[@name='cvv_code']"));
		cvv.clear();
		cvv.sendKeys(CardNumberPage.carddetail.get("CVV"));

		return this;
	}

	public void getReflectedAmount() {
		WebElement submit = driver.findElement(By.xpath("//input[@name='submit']"));
		String sub[] = submit.getAttribute("value").split(" \\$");
		
		payableAmt = (int)(float)(Float.valueOf(sub[1]));
	}

	public OrderIDPage clickPayBtn() {
		WebElement submit = driver.findElement(By.xpath("//input[@name='submit']"));
		submit.click();

		return new OrderIDPage(driver);
	}
}
