package com.HMRC.pageObjects;

import com.HMRC.utils.TakeScreenshot;
import io.cucumber.java.StepDefinitionAnnotation;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;


public class LandingPage {
public WebDriver driver;

    private @FindBy (xpath="(//a[@title='Dresses'])[2]")
    WebElement dressMenu;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnDressMenu() throws IOException {
        TakeScreenshot ts = new TakeScreenshot();
        ts.getScreenshot("01_Landing Page", driver);
        dressMenu.click();
    }
}
