package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Connector {
    public Connector(){
        connect();
    }
    public WebDriver driver = null;
    public void connect(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    public void disconnect(){
        driver.close();
        driver.quit();
    }
}
