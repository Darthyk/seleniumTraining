package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Darthyk on 19.03.2017.
 */
public class CheckStickersOnMainPage extends CommonSeleniumTest {

    /**
     * Check that each goods on the main page have only 1 sticker
     */
    @Test
    public void checkStickersOnMainPage() {
        driver.get("http://localhost/litecart/");
        List<WebElement> elements = driver.findElements(By.cssSelector("div.content > li"));
        for (WebElement element : elements) {
            final int numberOfStickers = element.findElements(By.cssSelector("[class^=sticker]")).size();
            final int expectedNumberOfStickers = 1;
            Assert.assertEquals("Each goods should have only " + expectedNumberOfStickers + " sticker",
                    expectedNumberOfStickers, numberOfStickers);
        }
    }



}
