import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RegistrationAndLogin extends ConnectDriver {


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
}
