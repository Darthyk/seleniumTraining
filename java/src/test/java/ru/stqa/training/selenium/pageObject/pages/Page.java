package ru.stqa.training.selenium.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Represents common class for page
 */
public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;


    /**
     * Configures Chrome driver and WebDriver wait
     */
    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
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
}
