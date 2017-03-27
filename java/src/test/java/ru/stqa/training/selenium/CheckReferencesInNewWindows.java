package ru.stqa.training.selenium;

import com.gargoylesoftware.htmlunit.WebWindow;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Darthyk on 27.03.2017.
 */
public class CheckReferencesInNewWindows extends CommonSeleniumTest {

    /**
     * Checks links which should be opened in another window
     */
    @Test
    public void checkReferencesInNewWindows() {
        loginToLitecard();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        WebElement element = driver.findElement(By.cssSelector("table.dataTable a"));
        element.click();

        List<WebElement> externalReferences = driver.findElements(By.cssSelector("i[class*=external-link"));
        String currentWindowHandle = driver.getWindowHandle();
        for(WebElement reference : externalReferences) {
            reference.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windowHandles = driver.getWindowHandles();
            driver.switchTo().window(getAnotherWindowHandle(windowHandles, currentWindowHandle));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));
            driver.close();
            driver.switchTo().window(currentWindowHandle);
        }
    }

    /**
     * Returns window handle which differs from provided window handle
     *
     * @param handles {@code Set} of window handles
     * @param unexpectedHandle {@code String} of unexpected window handle
     * @return {@code String} of window handle which differs from provided window handle,
     *          {@code null} if there is no such handle
     */
    public String getAnotherWindowHandle(Set<String> handles, String unexpectedHandle) {
        String anotherHandle = null;
        Iterator<String> handleIterator = handles.iterator();
        while(handleIterator.hasNext()) {
            String handle = handleIterator.next();
            if(!unexpectedHandle.equals(handle)) {
                anotherHandle = handle;
            }
        }
        return anotherHandle;
    }
}
