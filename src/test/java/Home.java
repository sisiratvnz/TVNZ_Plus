import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Home extends ConnectDriver {

    JavascriptExecutor js = (JavascriptExecutor) driver;

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
        String currentDir = System.getProperty("user.dir");
        File destFile = new File(currentDir + "/target/screenshots/screen.png");
        FileUtils.copyFile(sourceFile,destFile);
    }
    @Test
    public void screenShotOfMoviesBelt() throws IOException, InterruptedException {
        WebElement movieBeltElement  = driver.findElement(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']"));
        Thread.sleep(3000);
        File moviesBeltScreenShot = movieBeltElement.getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        File destFile = new File(currentDir + "/target/screenshots/movieBelt.png");
        FileUtils.copyFile(moviesBeltScreenShot,destFile);
    }
    @Test
    public void waitTillClick() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement movieTile = driver.findElement(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div"));
        wait.until(ExpectedConditions.elementToBeClickable(movieTile));

        File moviesBeltScreenShot = movieTile.getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        File destFile = new File(currentDir + "/target/screenshots/movieTile.png");
        FileUtils.copyFile(moviesBeltScreenShot,destFile);

    }

    @Test
    public void getRequestsAndResponseURLS(){


    }
    @Test
    public void explicitMoviesClick() throws IOException {
        WebElement homeMoviesBeltTitle = driver.findElement(By.id("anchor-Movies"));
        homeMoviesBeltTitle.click();
        WebElement moviePageTitle = driver.findElement(By.xpath("//h1[contains(text(), 'Movies')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(moviePageTitle));
        Assertions.assertNotNull(moviePageTitle);

        String currentDir = System.getProperty("user.dir");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(currentDir + "/target/screenshots/moviePageWhole.png");
        FileUtils.copyFile(sourceFile,destFile);
    }

    @Test
    public void jsExecutorTest() throws InterruptedException {
//        driver.navigate().to("https://stage.tvnz-stage.co.nz/");
        WebElement homeMoviesBeltTitle = driver.findElement(By.id("anchor-Movies"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(homeMoviesBeltTitle));
        String title = driver.getTitle();
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String titleFromJS = (String) js.executeScript("return document.title");
            System.out.println(title);
            System.out.println(titleFromJS);
            Assertions.assertEquals(title,titleFromJS);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllURLS(){
        WebElement homeMoviesBeltTitle = driver.findElement(By.id("anchor-Movies"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(homeMoviesBeltTitle));
        try {
            //JavascriptExecutor js = (JavascriptExecutor) driver;
            Long count = (Long) js.executeScript("var links = document.getElementsByTagName('A'); return links.length");
            System.out.println(count);

            List<WebElement> elements = (List<WebElement>) js.executeScript("var links = document.getElementsByTagName('A'); return links");
            for (WebElement e : elements) {
                System.out.println(e.getAttribute("href"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void clickFooterLinkAboutTVNZ(){

        WebElement footerElement = driver.findElement(By.xpath("//p[.='Corporate']/..//a[.='About TVNZ']"));
        //((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(footerElement)).click();
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(1));

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.history.go(-1)");
        WebElement reports = driver.findElement(By.xpath("//a[text()='Reports']"));
        wait.until(ExpectedConditions.elementToBeClickable(reports));
        reports.click();

    }

    @Test
    public void navigateBackFromShowPage() throws InterruptedException {
        WebElement show = driver.findElement(By.id("The Walkers"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(show)).click();
        driver.navigate().back();
        Thread.sleep(3000);
    }

    @Test
    public void handleAlert(){
        driver.findElement(By.xpath("//a[text()='Login']")).click();
//        Assertions.assertTrue(driver.findElement(By.xpath("//a[text()='Login']")).isDisplayed(),"Login button not available");
//        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys("assignment@grr.la");
        driver.findElement(By.id("password")).sendKeys("test1234");
        driver.findElement(By.xpath("//span[.='Login']/..")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

    }

    //Custom sync
    @Test
    public void  customSyncTest(){

    }
    @Test
    public void customSyncWithCountTest(){
        waiForPageLoad();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return d.findElements(By.xpath("//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div")).size()<10;
            }
        });
    }

    @Test
    public void fluentWaitWithPredicateTest(){

    }

    @Test
    public void scrollToBottomForNewsElement() throws IOException, InterruptedException {
        Actions builder = new Actions(driver);
        builder.scrollToElement(driver.findElement(By.xpath("//a[text()='News']"))).build().perform();
        Thread.sleep(30000);

    }
    @Test
    public void scrollToHowToWatchTile(){
        Actions builder = new Actions(driver);
        builder.scrollToElement(driver.findElement(By.xpath("//h4[.='How to watch']/../.."))).build().perform();
    }

    @Test
    public void scrollToBottomWithJS() throws InterruptedException {
//        Actions builder = new Actions(driver);
//        builder.scrollToElement(driver.findElement(By.xpath("//h4[.='How to watch']/../.."))).build().perform();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(3000);
    }


}
