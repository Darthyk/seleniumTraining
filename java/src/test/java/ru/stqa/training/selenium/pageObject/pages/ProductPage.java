package ru.stqa.training.selenium.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Represents class for work with product page
 *
 * Created by Darthyk on 30.03.2017.
 */
public class ProductPage extends Page {

    /**
     * Constructs {@code ProductPage} instance
     *
     * @param driver
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns to previous page
     */
    public void returnToPreviousPage() {
        driver.navigate().back();
    }

    /**
     * Adds product to cart
     */
    public void addProductToCart() {
        Integer initialProductsInCart = Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity"))
                .getAttribute("textContent"));
        if (areElementsPresent(driver, By.cssSelector("td.options select"))) {
            Select options = new Select(driver.findElement(By.cssSelector("select[name*=options]")));
            options.selectByIndex(1);
        }

        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"),
                Integer.valueOf(initialProductsInCart + 1).toString()));
    }
}
