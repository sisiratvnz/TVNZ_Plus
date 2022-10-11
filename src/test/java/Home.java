import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Home extends ConnectDriver {

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
    public void findMovies() throws InterruptedException {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div"))).build().perform();

        WebElement element = driver.findElement(By.xpath("//h2[@data-anchor='Movies']/../../..//div[@class='swiper-button-next swiper-no-swiping']"));

        while (element.isDisplayed()){
            element.click();
            Thread.sleep(1000);
        }


        List<WebElement> movies = driver.findElements(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div"));
        for (WebElement e : movies) {
            System.out.println(e.getAttribute("id"));
        }


//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60),Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.elementToBeSelected())


    }
    @Test
    public void hoverMovie() throws InterruptedException, AWTException {
        driver.findElement(By.xpath("//div[@id='Blue Hawaii']")).click();
        Actions builder = new Actions(driver);
        List<WebElement> movies = driver.findElements(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div"));
        Thread.sleep(3000);
        Robot bot = new Robot();
        Point p = movies.get(0).getLocation();
        bot.mouseMove(p.getX(),p.getY());
        Thread.sleep(3000);
        builder.moveToElement(movies.get(0)).perform();
        movies.get(0).findElement(By.cssSelector("div.QuickInfo-actions *> a.Button--primary")).click();
        Thread.sleep(3000);
        System.out.println(driver.getCurrentUrl().toString());
    }

    @Test
    public void findSynopsis() throws InterruptedException, AWTException {
        Actions builder = new Actions(driver);
        List<WebElement> movies = driver.findElements(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div"));
        Thread.sleep(3000);
        Robot bot = new Robot();
        Point p = movies.get(0).getLocation();
        bot.mouseMove(p.getX(),p.getY());
        Thread.sleep(3000);
        builder.moveToElement(movies.get(0)).perform();

        Thread.sleep(3000);
        System.out.println(movies.get(0).getAttribute("innerHTML"));
        System.out.println(driver.findElement(By.cssSelector("div.QuickInfo-synopsis")).getText());
    }
    @Test
    public void screenShotOnCategoryHover() throws IOException {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//span[@title='Categories']"))).build().perform();
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("/Users/SWisnara/Documents/automation/TVNZ_Plus/target/screenshots/screen.png");
        FileUtils.copyFile(sourceFile,destFile);
    }
}
