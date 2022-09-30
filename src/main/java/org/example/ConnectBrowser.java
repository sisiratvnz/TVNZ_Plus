package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class ConnectBrowser {
    private static ChromeDriver driver = null;
    public static WebDriver connect(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().fullscreen();
        return driver;
    }
}
