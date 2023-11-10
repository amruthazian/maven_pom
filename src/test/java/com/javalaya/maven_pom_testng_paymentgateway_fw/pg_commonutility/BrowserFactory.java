package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_commonutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	private static WebDriver driver;

	public static void openChrome() {
		System.setProperty("webdriver.chrome.driver", "paymentgateway_browserexes//chromedriver.exe");
		driver = new ChromeDriver();
	}

	public static void openFirefox() {
		System.setProperty("webdriver.gecko.driver", "paymentgateway_browserexes//geckodriver.exe");
		driver = new FirefoxDriver();
	}

	public static void openEdge() {
		System.setProperty("webdriver.edge.driver", "paymentgateway_browserexes//msedgedriver.exe");
		driver = new EdgeDriver();
	}

	public static WebDriver openBrowser(String browserName) {
		
		switch (browserName.intern().toLowerCase()) {
		case "chrome":
			openChrome();
			break;
		case "firefox":
			openFirefox();
		case "edge":
			openEdge();
			break;
		}
		return driver;
	}
	
	public static void openApplication(String url) {
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		
	}

	public static void closeWindows() {
		driver.close();
	}

	public static void closeBrowser() {
		driver.quit();
	}

}
