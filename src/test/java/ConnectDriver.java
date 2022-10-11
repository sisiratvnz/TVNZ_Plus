import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ConnectDriver {
    WebDriver driver = null;
    @BeforeEach
    public void connectDriver(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.navigate().to("https://stage.tvnz-stage.co.nz/");
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
}
