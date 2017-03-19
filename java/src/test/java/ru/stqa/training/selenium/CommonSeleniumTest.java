package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CommonSeleniumTest {
    protected WebDriver driver;
    protected WebDriverWait wait;


    /**
     * Configures Chrome driver and WebDriver wait
     */
    @Before
    public void startUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    /**
     * Deletes ChromeDriver
     */
    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    /**
     * Checks that elements of provided locator are present
     *
     * @param driver {@code WebDriver} instance
     * @param locator locator of elements
     * @return {@code true} if elements are present, {@code false} otherwise
     */
    public  boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    /**
     * Connects to Litecard site as admin user
     */
    public void loginToLitecard() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
