package selenium_basics;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

import static java.lang.Thread.sleep;

public class StepDefinitions {

    private static WebDriver driver;
    private static Robot robot;
    private static Point coordinates;


    @Given("that I'm on the youtube homepage")
    public void that_i_m_on_the_youtube_homepage() throws AWTException {

        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\Browsers driver\\chromedriver_win32\\chromedriver.exe");

        //initializing the webdriver
        driver = new ChromeDriver();

        //initializing the robot
        robot = new Robot();

        // opening browser on the youtube homepage
        driver.get("https://www.youtube.com/");
        driver.manage().window().maximize();

        //expected page title
        String expectedTitle = "YouTube";

        //get the actual page title
        String actualTitle = driver.getTitle();

        if(actualTitle.contentEquals(expectedTitle)){
            System.out.println("@Given(\"that I'm on the "+ actualTitle +" homepage\")");
        }
    }

    @When("I write something in the searchbar")
    public void i_write_something_in_the_searchbar() throws InterruptedException {

        sleep(1000);

        //remove the 1st dialog window
        WebElement elem1 = driver.findElement(By.xpath("/html/body/ytd-app/ytd-popup-container/paper-dialog/yt-upsell-dialog-renderer/div/div[3]/div[1]/yt-button-renderer/a/paper-button/yt-formatted-string"));

        //mouse move on the button
        coordinates = elem1.getLocation();
        robot.mouseMove(coordinates.getX(), coordinates.getY());

        //click on button
        sleep(1000);
        elem1.click();

        Thread.sleep(1000);

        //remove the 2nd dialog window
        try{
            //new WebDriverWait(driver,2).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().frame(1);

        }catch (Exception e){
            e.printStackTrace();
        }

        WebElement elem2 = driver.findElement(By.xpath("//*[@id=\"introAgreeButton\"]/span/span"));
        coordinates = elem2.getLocation();
        robot.mouseMove(coordinates.getX(),coordinates.getY());

        sleep(1000);
        elem2.click();

        //come back to the default window
        driver.switchTo().defaultContent();

        //look for the searchbar element

        WebElement searchbar = driver.findElement(By.id("search"));

        sleep(1000);

        //fill the searchbar
        searchbar.sendKeys("Selenium tutorial");

    }

    @When("I click on the search button")
    public void i_click_on_the_search_button() throws InterruptedException {

        sleep(1000);

        //click on search button
        WebElement elem3 = driver.findElement(By.id("search-icon-legacy"));
        coordinates = elem3.getLocation();
        elem3.click();

    }

    @Then("the result should be what I'm looking for")
    public void the_result_should_be_what_i_m_looking_for() {

        String expectedResult = "container";
        String actualResult = driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]")).getAttribute("id");

        assert actualResult.contentEquals(expectedResult) : "Error during searching";

    }

}
