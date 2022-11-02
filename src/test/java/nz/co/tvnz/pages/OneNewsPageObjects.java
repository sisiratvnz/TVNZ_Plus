package nz.co.tvnz.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class OneNewsPageObjects {




    //1News page objects
    @FindBy(how = How.XPATH, using = "//h2[.='Latest stories']/../../../..//div[contains(@class,'storyContainer')]")
    private List<WebElement> latest;

    public void latestStoryIsDisplayed(){
        Assertions.assertTrue(latest.get(0).isDisplayed());
    }
    public void clickFirstLatestStory(){
        latest.get(0).click();
    }
}
