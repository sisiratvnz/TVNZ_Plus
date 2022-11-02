package nz.co.tvnz;

import nz.co.tvnz.connect.ConnectDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest extends ConnectDriver {
    public void takeScreenShot(String filename) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        File destFile = new File(currentDir +"/target/screenshots/"+ filename);
        FileUtils.copyFile(sourceFile,destFile);
    }

    public void waitForPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /*
Scroll page
 */
    public static void scrollBackToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }
    /*
    Scroll to bottom
     */
    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /*
    Scroll to an element
     */
    public void scrollToElement(WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }
}
