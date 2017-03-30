package ru.stqa.training.selenium.pageObject.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Represents class for work with cart page
 *
 * Created by Darthyk on 30.03.2017.
 */
public class CartPage extends Page {

    /**
     * Constructs {@code CartPage} instance
     *
     * @param driver
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets cart page
     */
    public void getCartPage() {
        driver.findElement(By.cssSelector("div#cart a[class=content]")).click();
    }

    /**
     * Removes first product from cart
     */
    public void removeProductFromCart() {
        WebElement tableElement = driver.findElement(By.cssSelector("table.dataTable.rounded-corners")) ;
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        wait.until(ExpectedConditions.stalenessOf(tableElement));
    }

    /**
     * Checks that cart is empty
     */
    public void checkThatCartIsEmpty() {
        Assert.assertEquals("Cart is not empty", "There are no items in your cart.", driver.findElement(By
                .cssSelector("div#content em")).getAttribute("textContent"));
    }

    /**
     * Returns number of products in cart
     *
     * @return number of products in cart
     */
    public int getNumberOfProducts() {
        return Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity"))
                .getAttribute("textContent"));
    }

    /**
     * Returns to previous page
     */
    public void returnToPreviousPage() {
        driver.navigate().back();
    }
}
