package com.javalaya.maven_pom_testng_paymentgateway_fw.pg_testrunner_module;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_commonutility.BrowserFactory;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_commonutility.CommonUtility;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects.BalanceStatementPage;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects.CardLimitPage;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects.CardNumberPage;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects.IndexPage;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects.OrderIDPage;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects.ProcessPurchasePage;
import com.javalaya.maven_pom_testng_paymentgateway_fw.pg_pageobjects.PurchasePage;

public class PG_TestRunner {

	public static String cardnumber = "https://demo.guru99.com/payment-gateway/cardnumber.php";

	public static String index = "https://demo.guru99.com/payment-gateway/index.php";

	@Test
	public void verifyRightAmount() {

		CommonUtility.getproperties(new File("paymentgateway_testdata"));

		String bn = CommonUtility.datas.get("browsername");
		WebDriver driver = BrowserFactory.openBrowser(bn);

		String address = CommonUtility.datas.get("applicationurl");
		BrowserFactory.openApplication(address);

		IndexPage ip = new IndexPage(driver);

		CommonUtility.wait(3);

		CardNumberPage cnp = ip.clickCardDetails();

		CommonUtility.switchToWindow(driver, 1);

		cnp.fetchCardDetails();

		CommonUtility.wait(3);

		PurchasePage pp = cnp.clickCart();

		pp.selectQuantity(CommonUtility.datas.get("purchasequantity"));

		pp.getPrice();

		ProcessPurchasePage payproc = pp.clickBuyNow();

		CommonUtility.wait(3);

		payproc.enterCardNumer();

		payproc.selectMonth();

		payproc.selectYear();

		payproc.enterCVV();

		CommonUtility.wait(3);
		
		payproc.getReflectedAmount();

		int actualValue = ProcessPurchasePage.payableAmt;

		int expValue = PurchasePage.pricePerToy * Integer.valueOf(CommonUtility.datas.get("purchasequantity"));

		Assert.assertEquals(actualValue, expValue);

	}
	
	@Test
	public void verifyPayment() {

		CommonUtility.getproperties(new File("paymentgateway_testdata"));

		String bn = CommonUtility.datas.get("browsername");
		WebDriver driver = BrowserFactory.openBrowser(bn);

		String address = CommonUtility.datas.get("applicationurl");
		BrowserFactory.openApplication(address);

		IndexPage ip = new IndexPage(driver);

		CommonUtility.wait(3);

		CardNumberPage cnp = ip.clickCardDetails();

		CommonUtility.switchToWindow(driver, 1);

		cnp.fetchCardDetails();

		CommonUtility.wait(3);

		PurchasePage pp = cnp.clickCart();

		pp.selectQuantity(CommonUtility.datas.get("purchasequantity"));

		pp.getPrice();

		ProcessPurchasePage payproc = pp.clickBuyNow();

		CommonUtility.wait(3);

		payproc.enterCardNumer();

		payproc.selectMonth();

		payproc.selectYear();

		payproc.enterCVV();

		CommonUtility.wait(3);
		
		OrderIDPage oip = payproc.clickPayBtn();
		
		String actual = oip.getPaymentSuccessfullMsg();
		
		String expected = "Payment successfull!";
		
		Assert.assertEquals(actual, expected);

	}
	
	@Test
	public void verifyCardBalanceAndOrderId() {
		
		CommonUtility.getproperties(new File("paymentgateway_testdata"));

		String bn = CommonUtility.datas.get("browsername");
		WebDriver driver = BrowserFactory.openBrowser(bn);

		String address = CommonUtility.datas.get("applicationurl");
		BrowserFactory.openApplication(address);

		IndexPage ip = new IndexPage(driver);

		CommonUtility.wait(3);

		CardNumberPage cnp = ip.clickCardDetails();

		CommonUtility.switchToWindow(driver, 1);

		cnp.fetchCardDetails();

		CommonUtility.wait(3);

		PurchasePage pp = cnp.clickCart();

		pp.selectQuantity(CommonUtility.datas.get("purchasequantity"));

		pp.getPrice();

		ProcessPurchasePage payproc = pp.clickBuyNow();

		CommonUtility.wait(3);

		payproc.enterCardNumer();

		payproc.selectMonth();

		payproc.selectYear();

		payproc.enterCVV();
		
		payproc.getReflectedAmount();

		CommonUtility.wait(3);
		
		OrderIDPage oip = payproc.clickPayBtn();
		
		oip.getOrderId();
		
		CardLimitPage clp =  oip.clickCardLimit();
		
		clp.enterCardNumber();
		
		BalanceStatementPage bsp =  clp.clickSubmit();
		
		int actualbal = bsp.getCardBalance();
		
		int expectedbal = (int)(double)Double.valueOf(CardNumberPage.carddetail.get("Credit Limit")) - ProcessPurchasePage.payableAmt;
		
		int actualId = bsp.getOrderId();
		
		int expectedId = OrderIDPage.orderid;
		
		Assert.assertEquals(actualbal, expectedbal);
		
		Assert.assertEquals(actualId, expectedId);

	}

}
