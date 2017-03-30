package ru.stqa.training.selenium.pageObject.app;

import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.pageObject.pages.CartPage;
import ru.stqa.training.selenium.pageObject.pages.MainPage;
import ru.stqa.training.selenium.pageObject.pages.ProductPage;

import java.util.concurrent.TimeUnit;

/**
 * Represents application for work with site
 *
 * Created by Darthyk on 30.03.2017.
 */
public class Application {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected MainPage mainPage;
    protected ProductPage productPage;
    protected CartPage cartPage;


    /**
     * Configures Chrome driver and WebDriver wait
     */
    public Application() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    /**
     * Adds provided number of products to cart
     *
     * @param productNumber expected number of products in cart
     */
    public void addProductsToCart(int productNumber) {
        Preconditions.checkArgument(productNumber > 0, "At least 1 product should be stated as parameter");

        mainPage.getMainPage();
        for(int product = 0; product < productNumber; product++) {
            mainPage.getRandomMostPopularProduct();
            productPage.addProductToCart();
            productPage.returnToPreviousPage();
        }
    }

    /**
     * Removes provided number of products from cart
     *
     * @param productNumber expected number of products in cart
     */
    public void removeProductsFromCart(int productNumber) {
        Preconditions.checkArgument(productNumber > 0, "At least 1 product should be stated as parameter");

        mainPage.getMainPage();
        int initialProductNumber = cartPage.getNumberOfProducts();
        cartPage.getCartPage();
        for(int product = 0; product < productNumber; product++) {
            cartPage.removeProductFromCart();
        }

        if(initialProductNumber == productNumber) {
            cartPage.checkThatCartIsEmpty();
        }
    }

    /**
     * Deletes ChromeDriver
     */
    public void quit() {
        driver.quit();
        driver = null;
    }
}
