package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

/**
 * Created by Darthyk on 26.03.2017.
 */
public class CheckNewProductCreation extends CommonSeleniumTest {
    @Test
    public void checkNewProductCreation() {
        loginToLitecard();

        WebElement element = driver.findElements(By.cssSelector("ul#box-apps-menu li")).get(1);
        element.click();
        addNewProduct();
        checkNewProduct();
    }

    /**
     * Checks that new product was added to catalog
     */
    public void checkNewProduct() {
        WebElement element = driver.findElement(By.cssSelector("tr.footer > td"));
        String elementText = element.getAttribute("textContent");
        Assert.assertTrue("New product wasn't added to catalog!", elementText.contains("Products: 1"));
    }

    /**
     * Adds new product to catalog
     */
    public void addNewProduct() {
        WebElement element = driver.findElements(By.cssSelector("td#content div a[class=button]")).get(1);
        element.click();

        element = driver.findElement(By.cssSelector("td#content input[name*=name]"));
        element.sendKeys("test");

        element = driver.findElement(By.cssSelector("td#content input[name=code]"));
        element.sendKeys("test");

        element = driver.findElement(By.cssSelector("td#content input[data-name*=Ducks]"));
        element.click();

        Select defaultCategory = new Select(driver.findElement(By.cssSelector("select[name=default_category_id")));
        defaultCategory.selectByIndex(1);

        List<WebElement> gender = driver.findElements(By.cssSelector("input[name^=product_groups]"));
        gender.get(0).click();

        element = driver.findElement(By.cssSelector("td#content input[name=quantity]"));
        element.clear();
        element.sendKeys("10");

        File picture = new File("res/images/duck.jpg");
        element = driver.findElement(By.cssSelector("input[type=file]"));
        element.sendKeys(picture.getAbsolutePath());

        element = driver.findElement(By.cssSelector("input[name=date_valid_from]"));
        element.sendKeys("26032017");

        element = driver.findElement(By.cssSelector("input[name=date_valid_to]"));
        element.sendKeys("26032018");

        element = driver.findElement(By.cssSelector("a[href*=information"));
        element.click();

        Select manufacturer = new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id")));
        manufacturer.selectByIndex(1);

        element = driver.findElement(By.cssSelector("input[name=keywords]"));
        element.sendKeys("test");

        element = driver.findElement(By.cssSelector("input[name*=short_description]"));
        element.sendKeys("test");

        element = driver.findElement(By.cssSelector("input[name*=head_title]"));
        element.sendKeys("test");

        element = driver.findElement(By.cssSelector("input[name*=meta_description]"));
        element.sendKeys("test");

        element = driver.findElement(By.cssSelector("a[href*=prices"));
        element.click();

        element = driver.findElement(By.cssSelector("input[name=purchase_price]"));
        element.clear();
        element.sendKeys("100");

        Select price = new Select(driver.findElement(By.cssSelector("select[name*=purchase_price")));
        price.selectByIndex(1);

        element = driver.findElement(By.cssSelector("input[name*=USD][name*=prices"));
        element.sendKeys("100");

        element = driver.findElement(By.cssSelector("button[name=save"));
        element.click();
    }
}
