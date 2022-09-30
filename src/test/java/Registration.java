import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.text.Element;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Registration{

    WebDriver driver = null;
    static String email = null;
    static String password = null;

    //Generate email address randomly
    public String randomAlphabeticString(int stringLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random =  new Random();
        return random.ints(leftLimit,rightLimit+1).limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }

    //Generate birth year
    public String generateBirthYear(int min, int max){
        return String.valueOf((int)Math.floor(Math.random()*(max-min+1)+min));
    }

    //Generate random gender
    public String randomGender(){
        List<String> genders = Arrays.asList("Male","Female","Diverse");
        return genders.get((int)(Math.random() * genders.size()));
    }

    @BeforeEach
    public void connectDriver(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.navigate().to("https://stage.tvnz-stage.co.nz/");
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Site loading successful....");
    }
    @AfterEach
    public void closeDriver(){
        driver.close();
        driver.quit();
    }

    @Test
    public void registerUser(){
        email = randomAlphabeticString(2)+"@grr.la";
        password = randomAlphabeticString(8);
        if(driver.findElement(By.xpath("//a[text()='Login']")).isDisplayed()) {
            driver.findElement(By.xpath("//a[text()='Login']")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.findElement(By.xpath("//span[text()='Sign up now']")).click();
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("firstName")).sendKeys(randomAlphabeticString(3));
            driver.findElement(By.id("lastName")).sendKeys(randomAlphabeticString(3));
            driver.findElement(By.xpath("//div[@name='yearOfBirth']")).click();
            driver.findElement(By.xpath("//div[.='" + generateBirthYear(1923, 2009) + "']")).click();
            driver.findElement(By.xpath("//div[@name='gender']")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//div[@id='gender']/div[.='" + randomGender() + "']")).click();
            driver.findElement(By.xpath("//label[@for='houseRules']//span")).click();
            driver.findElement(By.xpath("//label[@for='latestNews']/div/div")).click();
            driver.findElement(By.xpath("//button[.='Sign Me Up']")).click();
        }
    }
    @Test
    public void logIn() throws InterruptedException {
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Login']")).click();
//        Assertions.assertTrue(driver.findElement(By.xpath("//a[text()='Login']")).isDisplayed(),"Login button not available");
//        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//span[.='Login']/..")).click();
    }

    @Test
    public void findCategories(){
        driver.findElement(By.xpath("//li[@id='Categories']")).click();
        List<WebElement> categories = driver.findElements(By.xpath("//li[@id='Categories']//li//a"));
        for (WebElement e : categories) {
            System.out.println(e.getAttribute("text"));
        }
    }
    @Test
    public void findTopPicks(){
        List<WebElement> topPicks = driver.findElements(By.xpath("//h2[@id='anchor-TopPicks']/../../..//div[@class='swiper-wrapper']//a/div"));
        for (WebElement e : topPicks) {
            System.out.println(e.getAttribute("id"));
        }
    }
    @Test
    public void findMovies(){
        List<WebElement> movies = driver.findElements(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a"));
        for (WebElement e : movies) {
            System.out.println(e.getAttribute("id"));
        }
    }

}
