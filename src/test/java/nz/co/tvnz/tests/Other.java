package nz.co.tvnz.tests;

import nz.co.tvnz.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;

public class Other extends BaseTest {

    public static int getDate(){
        return LocalDate.now().getDayOfMonth();
    }

    public static String getPickupMonth(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();

        //System.out.println("Month name: " + month);
        return monthName[cal.get(Calendar.MONTH)+1];
    }

    public static String getDropOffMonth(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();

        //System.out.println("Month name: " + month);
        return monthName[cal.get(Calendar.MONTH)+2];
    }

    public static String getYear(){
        //System.out.println(year);
        return String.valueOf(YearMonth.now().getYear());
    }

    @Test
    public void getTableData(){
        List<WebElement> list =  driver.findElements(By.xpath("//table[@width = '270']//td[1]"));
        for (WebElement e : list) {
            System.out.println(e.getText());
        }
    }

    //Search for cars in GoSee cars
    @Test
    public void searchCarsInGoSee() throws InterruptedException {

        WebElement pickupLocation = driver.findElement(By.xpath("//input[@id='pickup_location']"));
        pickupLocation.sendKeys(" Auckland");
        Thread.sleep(2000);
        pickupLocation.sendKeys(Keys.ARROW_DOWN);
        pickupLocation.sendKeys(Keys.ENTER);

        driver.findElement(By.id("pickupDateField")).click();


        //WebElement pickupDate = driver.findElement(By.xpath("//div[.='"+getPickupMonth()+" "+getYear()+"']//..//tr[1]//td[3]"));
        WebElement pickupDate = driver.findElement(By.xpath("//div[.='"+getPickupMonth()+" "+getYear()+"']/..//td[contains(@aria-label,'"+getDate()+"')]"));

        pickupDate.click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();

        driver.findElement(By.id("dropoffDateField")).click();
        //WebElement dropOffDate = driver.findElement(By.xpath("//div[.='"+getDropOffMonth()+" "+getYear()+"']/..//tr[5]//td[4]"));
        WebElement dropOffDate = driver.findElement(By.xpath("//div[.='"+getDropOffMonth()+" "+getYear()+"']/..//td[contains(@aria-label,'"+getDate()+"')]"));


        dropOffDate.click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();

        driver.findElement(By.id("search-cars-btn")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//ul[@class='resultsGroup' and @id='desktop-results']")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='progress search']")).isDisplayed());
        System.out.println("Cars search working...");

    }


    @Test
    public void findNewsAds() throws InterruptedException {
        //scrollToBottom(driver);
        Actions builder = new Actions(driver);
        List<WebElement> frames = driver.findElements(By.xpath("//iframe[starts-with(@id,'google_ads_iframe_')]"));
        //List<WebElement> ads = driver.findElements(By.xpath("//iframe[starts-with(@id,'google_ads_iframe_')]"));


        for (WebElement e : frames) {
            builder.scrollToElement(e).build().perform();
            driver.switchTo().frame(e);
//            int total = driver.findElements(By.xpath("html/body/iframe")).size();
            WebElement frame = driver.findElement(By.xpath("html/body/a"));
//            System.out.println(total);
            System.out.println(e.getAttribute("href"));
            //((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            //driver.switchTo().defaultContent();
        }

//        WebElement frame = driver.findElement(By.xpath("//iframe[starts-with(@id,'google_ads_iframe_')]"));
//        while (frame.isDisplayed()) {
//            Actions builder = new Actions(driver);
//            //Thread.sleep(10000);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.visibilityOf(frame));
//            builder.scrollToElement(frame).build().perform();
//            System.out.println(frame.getAttribute("text"));
//        }


//        List<WebElement> ads = driver.findElements(By.xpath("//iframe[starts-with(@id,'google_ads_iframe_')]"));
//
//        for (WebElement e : ads) {
//
//            System.out.println(e.getText());
//
//        }
    }

    @Test
    public void findLatestStories(){
        oneNewsPageObjects.latestStoryIsDisplayed();
        oneNewsPageObjects.clickFirstLatestStory();
    }
}
