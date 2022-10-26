import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void logIn() {
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//span[.='Login']/..")).click();
    }

    @Test
    public void jsLogin() throws InterruptedException {

        try {
            WebElement loginLink = driver.findElement(By.xpath("//a[text()='Login']"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginLink);

            WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
            String emailText = "assignment@grr.la";
            emailField.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+ emailText +"';", emailField);
            Thread.sleep(2000);
            //js.executeScript("document.getElementById('email').value='assignment@grr.la';");
            WebElement passField = driver.findElement(By.xpath("//input[@id='password']"));
            String passText = "test1234";
            passField.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+ passText +"';", passField);
            Thread.sleep(2000);
            //js.executeScript("document.getElementById('password').value='test1234';");
            WebElement loginButton = driver.findElement(By.xpath("//button[.='Login']"));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            js.executeScript("arguments[0].click();", loginButton);


            //WebElement letsGoButton = driver.findElement(By.xpath("//button[starts-with(text(),'Let')]"));
            //wait.until(ExpectedConditions.elementToBeClickable(letsGoButton));
            //js.executeScript("arguments[0].click();", letsGoButton);
            //letsGoButton.click();
            WebElement profile = driver.findElement(By.xpath("//ul[@class='choose-profile__avatars']/li[1]/a[@href='#']//img[@alt='test']"));
            wait.until(ExpectedConditions.elementToBeClickable(profile));
            profile.click();

            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            alert.accept();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
