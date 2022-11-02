package nz.co.tvnz.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePageObjects {
    /*
    Categories
     */
    @FindBy(how = How.XPATH, using = "//li[@id='Categories']")
    public WebElement categoryMenu;

    @FindBy(how = How.XPATH, using = "//li[@id='Categories']//li//a")
    private List<WebElement> categories;
    public void printCategoryNames(){
        for (WebElement e : categories) {
            System.out.println(e.getAttribute("text"));
        }
    }
    public void categoryMenuIsDisplayed(){
        Assertions.assertTrue(categoryMenu.isDisplayed());
    }



    /*
    Top Picks belt
     */
    @FindBy(how = How.XPATH, using = "//h2[@id='anchor-TopPicks']/../../..//div[@class='swiper-wrapper']//a/div")
    private List<WebElement>topPicks;
    public void findAndPrintTopPicks(){
        for (WebElement e : topPicks) {
            System.out.println(e.getAttribute("id"));
        }
    }

    /*
    Movies belt
     */
    @FindBy(how = How.XPATH, using = "//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div")
    public WebElement movies;

    @FindBy(how = How.XPATH, using = "//h2[@data-anchor='Movies']/../../..//div[@class='swiper-button-next swiper-no-swiping']")
    public WebElement movieBeltArrow;

    public void scrollMovieBelt() throws InterruptedException {
        while (movieBeltArrow.isDisplayed()){
            movieBeltArrow.click();
            Thread.sleep(2000);
        }
    }

    @FindBy(how = How.XPATH, using = "//h2[@id='anchor-Movies']/../../..//div[@class='swiper-wrapper']//a/div")
    public List<WebElement> moviesList;
    public void printMovieNames(){
        for (WebElement e : moviesList) {
            System.out.println(e.getAttribute("id"));
        }
    }

    @FindBy(how = How.XPATH, using = "//div[@id='Blue Hawaii']")
    public WebElement movie;

    public By quickActionButton = By.cssSelector("div.QuickInfo-actions *> a.Button--primary");
    public By movieSynopsis = By.cssSelector("div.QuickInfo-synopsis");









}
