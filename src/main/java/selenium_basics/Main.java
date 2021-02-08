package selenium_basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    //declare a webdriver
    private static WebDriver driver;

    public static void main(String[]args){

        //set the path to the chromedriver
        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\Browsers driver\\chromedriver_win32\\chromedriver.exe");

        //initialize the driver as a chromeDriver
        driver = new ChromeDriver();

        //get an URL and open it
        driver.get("https://google.com");
        driver.manage().window().maximize();

        driver.quit();
    }
}
