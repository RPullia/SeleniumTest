package selenium_basics;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.*;

public class Main {

    //declare a webdriver
    private static WebDriver driver;
    private static Robot robot;

    public static void main(String[]args) throws AWTException, InterruptedException {

        //set the path to the chromedriver
        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\Browsers driver\\chromedriver_win32\\chromedriver.exe");

        //initialize the driver as a chromeDriver
        driver = new ChromeDriver();

        //initialize the robot
        robot = new Robot();

        //get an URL and launching a browser session
        //driver.get("https://www.youtube.com/?hl=it&gl=IT");
        driver.get("https://www.youtube.com/");

        driver.manage().window().maximize();



        String expectedTitle = "YouTube";

        //get the actual page title
        String actualTitle = driver.getTitle();

        System.out.println("Testing title");
        if(actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }

        /*
         Locating element

         By.className   finds elements based on the value of the class
         By.cssSelector finds elements based on the driver's underlaying CSS Selector engine
         By.id          finds elements based on their id
         By.name
         By.tagName
         By.xpath
         */

        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }

        //remove the 1st dialog window
        WebElement elem1 = driver.findElement(By.xpath("/html/body/ytd-app/ytd-popup-container/paper-dialog/yt-upsell-dialog-renderer/div/div[3]/div[1]/yt-button-renderer/a/paper-button/yt-formatted-string"));
        System.out.println(elem1.getTagName());

        Point coordinates = elem1.getLocation();
        robot.mouseMove(coordinates.getX(), coordinates.getY());

        sleep(1000);
        elem1.click();

        //remove the 2nd dialog window
        try{
            //new WebDriverWait(driver,2).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().frame(1);

        }catch (Exception e){
            e.printStackTrace();
        }

        WebElement elem2 = driver.findElement(By.xpath("//*[@id=\"introAgreeButton\"]/span/span"));
        System.out.println(elem2.getTagName());
        coordinates = elem2.getLocation();
        robot.mouseMove(coordinates.getX(),coordinates.getY());

        sleep(1000);
        elem2.click();

        //come back to the default window
        driver.switchTo().defaultContent();

        //look for the searchbar element
        //WebElement searchbar = driver.findElement(By.cssSelector("input[placeholder = 'Cerca']"));
        WebElement searchbar = driver.findElement(By.id("search"));
        System.out.println(searchbar.getTagName());

        sleep(1000);

        //fill the searchbar
        searchbar.sendKeys("Selenium tutorial");

        sleep(1000);
        //click on search button
        WebElement elem3 = driver.findElement(By.id("search-icon-legacy"));
        coordinates = elem3.getLocation();
        elem3.click();

        String expectedResult = "container";
        String actualResult = driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]")).getAttribute("id");
        System.out.println(driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]")).getTagName());

        assert expectedResult == actualResult;

    }
}
