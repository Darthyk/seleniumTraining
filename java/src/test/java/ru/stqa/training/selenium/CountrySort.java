package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * Created by Darthyk on 19.03.2017.
 */
public class CountrySort extends CommonSeleniumTest {

    @Test
    public void countrySort() {
        loginToLitecard();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<Integer> countriesToCheck = new LinkedList<>();
        List<String> countries = new LinkedList<>();

        final List<WebElement> elements = driver.findElements(By.cssSelector("[class=row]"));
        int countriesCount = 1;
        for (WebElement element : elements) {
            countries.add(element.findElement(By.cssSelector("a:not([title=Edit])")).getAttribute("textContent"));

            if (Integer.parseInt(element.findElements(By.cssSelector("td")).get(5).getAttribute("textContent")) > 0) {
                System.out.println(element.findElement(By.cssSelector("a:not([title=Edit])")).getAttribute("textContent"));
                countriesToCheck.add(countriesCount);
            }
            countriesCount++;
        }

        for (Integer element : countriesToCheck) {
            WebElement country = driver.findElement(By.cssSelector("[class=row]:nth-child(" + element + ") a:not([title=Edit])"));
            System.out.println(country.getAttribute("textContent"));
            country.click();


            checkCountryZones(driver);



            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        }

        LinkedList<String> sortedCountries = null;
        Collections.copy(sortedCountries, countries);
        Collections.sort(sortedCountries);

        for (int index = 0; index < sortedCountries.size(); index++) {
            Assert.assertEquals("Country " + countries.get(index) + " is not on the right place",
                    sortedCountries.get(index), countries);
        }
    }

    public void checkCountryZones(WebDriver driver) {
        List<String> zones = new LinkedList<>();
        List<WebElement> zoneElements = driver.findElements(By.cssSelector("[id=table-zones] > tr:not([class=header])"));
        for (WebElement zone : zoneElements) {
            zones.add(zone.findElements(By.cssSelector("td")).get(3).getAttribute("textContent"));
        }

        LinkedList<String> sortedZones = null;
        Collections.copy(sortedZones, zones);
        Collections.sort(sortedZones);

        for (int index = 0; index < sortedZones.size(); index++) {
            Assert.assertEquals("Zone " + zones.get(index) + " is not on the right place",
                    sortedZones.get(index), zones);
        }
    }
}
