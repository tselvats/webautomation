package com.HMRC;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public Properties prop;
    String browserName=null;
    public WebDriver initializeDriver() throws IOException {
        prop=new Properties();
        String FilePath = "src/main/resources/web.properties";
        FileInputStream fis = new FileInputStream(FilePath);
        prop.load(fis);
        if (prop.getProperty("runLocally").equalsIgnoreCase("true")){
            browserName = prop.getProperty("browser");
        }else
        {
            browserName=System.getProperty("browser");
        }

        if (browserName.contains("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")){
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }
}
