package com.HMRC.pageObjects;

import com.HMRC.utils.TakeScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class dressesPage {
    public WebDriver driver;
    TakeScreenshot ts = new TakeScreenshot();

    public dressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void addHighestPriceDressToCart() throws InterruptedException, IOException {
        List<WebElement> dressPriceElements = driver.findElements(By.xpath("//div[@class='right-block']//following-sibling::span[@class='price product-price']"));
        float maxValue = 0;
        for (int i = 0; i < dressPriceElements.size(); i++) {
            float dressPrice = Float.parseFloat(dressPriceElements.get(i).getText().replace("$", ""));
            if (dressPrice > maxValue)
                maxValue = dressPrice;
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(5000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("(//span[contains(text(),'"+maxValue+"')])[2]"))).perform();
        By container = By.cssSelector(".button-container");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(container));
        ts.getScreenshot("02_Highest Price dress selection", driver);
        driver.findElement(By.xpath("(//span[contains(text(),'"+maxValue+"')])[2]//following::div[1]/a/span[contains(text(),'Add to cart')]")).click();
        Thread.sleep(5000);
    }
}
