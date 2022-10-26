package examples;

import org.asynchttpclient.util.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static String getMonthAndYear(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)+1];

        System.out.println("Month name: " + month);
        return month;

    }

    public static String getYear(){
        String year = String.valueOf(YearMonth.now().getYear());
        String month = String.valueOf(YearMonth.now().getMonth());
        System.out.println(year);
        System.out.println(month);
        System.out.println(LocalDate.now().getDayOfMonth());
        return year;

    }
//    public static int getDate(){
//
//    }

    public static void main(String[] args) {
        getMonthAndYear();
        getYear();
//        new ParameterizeRegistration().registration("aa","bb","az@grr.la","11111111","1999","Male");
//        new ParameterizeRegistration().registration("bb","cc","ax@grr.la","11111111","2014","Female");


//        WebDriverManager.chromedriver().setup();
//        ChromeDriver driver = new ChromeDriver();
//        driver.get("https://www.tvnz.co.nz");
//        driver.manage().window().fullscreen();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.findElement(By.cssSelector(".SiteNav-item.SiteNav-profile.ember-view > .SiteNav-item--title-link")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.findElement(By.id("email")).sendKeys("aaa@grr.la");
//        System.out.println("Site loaded...");
//        driver.close();
//        driver.quit();




//        WebDriverManager.firefoxdriver().setup();
//        FirefoxDriver fdriver = new FirefoxDriver();
//
//        fdriver.get("https://www.tvnz.co.nz");
//        System.out.println("Firefox loaded....");
//
//        WebDriverManager.safaridriver().setup();
//        SafariDriver sdriver = new SafariDriver();
//        sdriver.get("https://www.tvnz.co.nz");
//        System.out.println("Safari loaded...");
    }
}
