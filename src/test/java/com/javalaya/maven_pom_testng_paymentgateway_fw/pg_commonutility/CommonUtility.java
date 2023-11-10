package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_commonutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
 

public class CommonUtility {
	
	public static HashMap<String, String> datas = new HashMap<String, String>();

	public static void getproperties(String fName) {

		String fn = fName.contains(".properties") ? fName : fName + ".properties";

		try (FileInputStream fis = new FileInputStream("paymentgateway_testdata/" + fn)) {

			Properties p = new Properties();
			p.load(fis);

			for (Object t : p.keySet()) {
				datas.put(t.toString(), p.getProperty(t.toString()));
			}

		} catch (IOException e) {

		}

	}

	public static void getproperties(final File folder) {
		for (final File files : folder.listFiles()) {
			getproperties(files.getName());
		}

	}

	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		getproperties("test_conf");
		System.out.println(datas);
		
	}
	
//	public static int getWindowCount(WebDriver driver) {
//		Set<String> allWindows = driver.getWindowHandles();
//		return allWindows.size();
//	}

	public static void switchToWindow(WebDriver driver, int num) {
		Set<String> allWindows = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<>(allWindows);
		driver.switchTo().window(windowsList.get(1));
		
	}

}
