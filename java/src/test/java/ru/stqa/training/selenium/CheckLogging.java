package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by Darthyk on 28.03.2017.
 */
public class CheckLogging extends CommonSeleniumTest {
    /**
     * Checks logging
     */
    @Test
    public void checkLogging() {
        loginToLitecard();

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> elements = driver.findElements(By.cssSelector("tr.row td > a[href*=product]:not([title=Edit])"));

        for(int index = 0; index < elements.size(); index++) {
            elements = driver.findElements(By.cssSelector("tr.row td > a[href*=product]:not([title=Edit])")) ;
            elements.get(index).click();
            driver.manage().logs().get("browser").filter(Level.ALL).forEach(l -> Assert
                    .assertFalse("Browser log contains error message: " + l, l.getMessage().contains("error")));
            driver.navigate().back();

        }
    }
}
