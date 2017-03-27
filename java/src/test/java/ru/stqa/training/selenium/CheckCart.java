package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Darthyk on 27.03.2017.
 */
public class CheckCart extends CommonSeleniumTest {

    /**
     * Checks that products can be added to cart and then deleted from it
     */
    @Test
    public void checkCart() {
        getLitecard();

        List<WebElement> products;
        WebElement element;
        for(int product = 0; product < 3; product++) {
            products = driver.findElements(By.cssSelector("div#box-most-popular li"));
            products.get(product).click();

            if(areElementsPresent(driver, By.cssSelector("td.options select"))) {
                Select options = new Select(driver.findElement(By.cssSelector("select[name*=options]")));
                options.selectByIndex(1);
            }

            element = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
            element.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"),
                    Integer.valueOf(product + 1).toString()));

            driver.navigate().back();
        }

        element = driver.findElement(By.cssSelector("div#cart a[class=content]"));
        element.click();

        for(int product = 0; product < 3; product++) {
            WebElement tableElement = driver.findElement(By.cssSelector("table.dataTable.rounded-corners")) ;
            element = driver.findElement(By.cssSelector("button[name=remove_cart_item]"));
            element.click();
            wait.until(ExpectedConditions.stalenessOf(tableElement));
        }
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#content em"),
                "There are no items in your cart."));
    }
}
