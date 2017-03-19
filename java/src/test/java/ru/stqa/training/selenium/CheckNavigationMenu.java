package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckNavigationMenu extends CommonSeleniumTest {

    @Test
    public void checkNavigationMenu() {
        loginToLitecard();

        final int numberOfMainMenuElments = driver.findElements(By.cssSelector("[id^=app-]")).size();
        for(int elementIndex = 8; elementIndex <= numberOfMainMenuElments; elementIndex++) {
            WebElement element = driver.findElement(By.cssSelector("[id^=app-]:nth-child(" + elementIndex + ")")) ;
            element.click();
            checkH1HeaderIsPresent(driver);

            if (areElementsPresent(driver, By.className("docs"))) {
                final int numberOfSubElments = driver.findElements(By.cssSelector("[id^=doc-]")).size();
                if (numberOfSubElments > 0) {
                    for (int subElementIndex = 1; subElementIndex <= numberOfSubElments; subElementIndex++) {
                        element = driver.findElement(By.cssSelector("[id^=doc-]:nth-child(" + subElementIndex + ")")) ;
                        element.click();
                        checkH1HeaderIsPresent(driver);
                    }
                } else {
                    System.out.println("There are no sub elements in list");
                }
            } else {
                System.out.println("Can't find elements by class name docs");
            }
        }
    }

    /**
     * Checks that at least 1 H1 header is present on page
     *
     * @param driver {@code WebDriver} instance
     */
    public void checkH1HeaderIsPresent(WebDriver driver) {
        List<WebElement> headers = driver.findElements(By.cssSelector("h1"))     ;
        Assert.assertTrue("At least 1 header should be present", headers.size() > 0);
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
