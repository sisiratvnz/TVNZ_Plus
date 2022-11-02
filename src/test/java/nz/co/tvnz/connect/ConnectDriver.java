package nz.co.tvnz.connect;

import io.github.bonigarcia.wdm.WebDriverManager;
import nz.co.tvnz.GlobalPropertyConfig;
import nz.co.tvnz.pages.HomePageObjects;
import nz.co.tvnz.pages.OneNewsPageObjects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConnectDriver {
    public WebDriver driver = null;

    public HomePageObjects homePageObjects;
    public OneNewsPageObjects oneNewsPageObjects;

    @BeforeEach
    public void connectDriver(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get(GlobalPropertyConfig.getURL());
        //driver.navigate().to(GlobalPropertyConfig.getURL());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Site loading successful....");
        homePageObjects = PageFactory.initElements(driver, HomePageObjects.class);
        oneNewsPageObjects = PageFactory.initElements(driver, OneNewsPageObjects.class);
    }
    @AfterEach
    public void closeDriver(){
        driver.close();
        driver.quit();
    }
}
