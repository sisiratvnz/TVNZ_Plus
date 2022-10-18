import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.GlobalPropertyConfig;
import org.example.PropertiesHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class ConnectDriver {
    public WebDriver driver = null;

    @BeforeEach
    public void connectDriver(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.navigate().to(GlobalPropertyConfig.getURL());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Site loading successful....");
    }
    @AfterEach
    public void closeDriver(){
        driver.close();
        driver.quit();
    }

    public void waiForPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
