package com.HMRC;

import com.HMRC.pageObjects.LandingPage;
import com.HMRC.pageObjects.cartPage;
import com.HMRC.pageObjects.dressesPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class AddHighestPriceItemToCart extends BaseTest{

    public WebDriver driver;
    public static Logger log = LogManager.getLogger(AddHighestPriceItemToCart.class.getName());
    ExtentReports extentReports;
    ITestResult test;

    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }
    @BeforeTest
    public void config() {
        String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Test Report");
        reporter.config().setDocumentTitle("Automation Test Results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Tamilselvam");
        extentReports.setSystemInfo("Env", "SIT");
        extentReports.setSystemInfo("Build", "Integration");
        extentReports.setSystemInfo("Browser", "Chrome");
    }

    @Test (description = "addHighestPriceItemToCart")
    public void addHighestPriceItemToCart() throws InterruptedException, IOException {
        ExtentTest test = extentReports.createTest("Extent Report Test");
        driver.get(prop.getProperty("url"));
        LandingPage lp = new LandingPage(driver);
        dressesPage dp = new dressesPage(driver);
        cartPage cp = new cartPage(driver);
        lp.clickOnDressMenu();
        dp.addHighestPriceDressToCart();
        String successMessage = cp.verifyCart();
        Assert.assertEquals(successMessage,"Product successfully added to your shopping cart");
        test.addScreenCaptureFromBase64String("Screenshot");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("reports/"+ System.currentTimeMillis() + ".png");
        FileUtils.copyFile(scrFile, Dest);
        log.info("Test is successful");

    }

       @AfterTest
        public void tearDown(){
        extentReports.flush();
        driver.close();
        driver.quit();

    }

}
