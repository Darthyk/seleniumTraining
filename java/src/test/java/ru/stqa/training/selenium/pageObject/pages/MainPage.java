package ru.stqa.training.selenium.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Represents class for work with main page
 *
 * Created by Darthyk on 30.03.2017.
 */
public class MainPage extends Page {

    /**
     * Constructs {@code MainPage} instance
     *
     * @param driver
     */
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets to Litecard site
     */
    public void getMainPage() {
        driver.get("http://localhost/litecart/");
    }

    @FindBy(css = "div#box-most-popular li")
    private List<WebElement> mostPopularProducts;

    /**
     * Gets random product from most popular products
     *
     * @return {@code WebElement} instance of random product from most popular
     */
    public void getRandomMostPopularProduct() {
        driver.findElement(By.cssSelector("div#box-most-popular li:nth-child(" + (1 + (int) Math.random()
                * mostPopularProducts.size()) + ")")).click();
    }
}
