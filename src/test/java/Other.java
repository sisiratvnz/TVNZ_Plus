import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;

public class Other extends ConnectDriver{

    WebDriver drivers = null;
    @Test
    public void getTableData(){
        WebDriverManager.chromedriver().setup();
        drivers= new ChromeDriver();
        drivers.navigate().to("https://demo.guru99.com/test/newtours/");
        List<WebElement> list =  drivers.findElements(By.xpath("//table[@width = '270']//td[1]"));
        for (WebElement e : list) {
            System.out.println(e.getText());
        }
        drivers.close();
        drivers.quit();
    }

    //Search for cars in GoSee cars
    @Test
    public void searchCarsInGoSee() throws InterruptedException {

        //Actions builder = new Actions(driver);
        WebElement pickupLocation = driver.findElement(By.xpath("//input[@id='pickup_location']"));
        pickupLocation.sendKeys(" Auckland");
        Thread.sleep(2000);
        pickupLocation.sendKeys(Keys.ARROW_DOWN);
        pickupLocation.sendKeys(Keys.ENTER);

        driver.findElement(By.id("pickupDateField")).click();

        WebElement pick = driver.findElement(By.xpath("//div[.='November 2022']/..//td[contains(@aria-label,'10')]"));


        WebElement pickupDate = driver.findElement(By.xpath("//div[.='"+getPickupMonth()+" "+getYear()+"']//..//tr[1]//td[3]"));
        //builder.moveToElement(startDate).build().perform();
        pickupDate.click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();

        driver.findElement(By.id("dropoffDateField")).click();
        WebElement dropOffDate = driver.findElement(By.xpath("//div[.='"+getDropOffMonth()+" "+getYear()+"']/..//tr[5]//td[4]"));
        //builder.moveToElement(endDate).build().perform();
        dropOffDate.click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();

        driver.findElement(By.id("search-cars-btn")).click();
        Assertions.assertNotNull(driver.findElement(By.xpath("//div[@class='progress search']")));
        Assertions.assertNotNull(driver.findElement(By.xpath("//ul[@class='resultsGroup' and @id='desktop-results']")));
        System.out.println("Cars search working...");

    }

    public static String getPickupMonth(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)+1];

        System.out.println("Month name: " + month);
        return month;
    }

    public static String getDropOffMonth(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)+2];

        System.out.println("Month name: " + month);
        return month;
    }

    public static String getYear(){
        String year = String.valueOf(YearMonth.now().getYear());
        System.out.println(year);
        return year;
    }
}
