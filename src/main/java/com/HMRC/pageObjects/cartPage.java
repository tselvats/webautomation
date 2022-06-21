package com.HMRC.pageObjects;


import com.HMRC.utils.TakeScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class cartPage {

    public WebDriver driver;

    @FindBy(xpath="(//div[@id='layer_cart']//following-sibling::h2)[1]")
    WebElement successMsg;

    public cartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String verifyCart() throws IOException {
        TakeScreenshot ts = new TakeScreenshot();
        ts.getScreenshot("03_Verify the dress is added to cart", driver);
        String successMessage = successMsg.getText();
        return successMessage;
        }

    }

