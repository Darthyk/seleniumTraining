package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

/**
 * Created by Darthyk on 26.03.2017.
 */
public class CheckUserRegistration extends CommonSeleniumTest {
    List<String> accounts = new LinkedList<>();

    /**
     * Checks user registration
     */
    @Test
    public void checkUserRegistration() {
        getLitecard();
        registerUser();
        loguot();
        login();
        loguot();
    }

    /**
     * Registrates new user on Litecard site
     */
    public void registerUser() {
        WebElement createAccountLink = driver.findElement(By.cssSelector("form[name=login_form] a"));
        createAccountLink.click();

        WebElement formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=firstname]"));
        formElement.click();
        formElement.sendKeys("Name");

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=lastname]"));
        formElement.click();
        formElement.sendKeys("Lastname");

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=address1]"));
        formElement.click();
        formElement.sendKeys("Address1");

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=postcode]"));
        formElement.click();
        formElement.sendKeys(randomPostcode());

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=city]"));
        formElement.click();
        formElement.sendKeys("City");

        Select countrySelect = new Select(driver.findElement(By.cssSelector("form[name=customer_form] select[name=country_code")));
        countrySelect.selectByVisibleText("United States");

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=email]"));
        formElement.click();
        String email =  randomEmail();
        accounts.add(email);
        formElement.sendKeys(email);

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=phone]"));
        formElement.click();
        formElement.sendKeys(randomPhoneNumber());

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=password]"));
        formElement.click();
        String password = "qwerty11";
        formElement.sendKeys(password);

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] input[name=confirmed_password]"));
        formElement.click();
        formElement.sendKeys("qwerty11");

        formElement = driver.findElement(By.cssSelector("form[name=customer_form] button[name=create_account]"));
        formElement.click();
    }

    /**
     * Logins as registered user
     */
    public void login() {
        WebElement formElement = driver.findElement(By.cssSelector("form[name=login_form] input[name=email]"));
        formElement.click();
        formElement.clear();
        formElement.sendKeys(accounts.get(0));

        formElement = driver.findElement(By.cssSelector("form[name=login_form] input[name=password]"));
        formElement.click();
        formElement.clear();
        formElement.sendKeys("qwerty11");

        formElement = driver.findElement(By.cssSelector("form[name=login_form] button[name=login]"));
        formElement.click();
    }

    /**
     * Logouts from current user
     */
    public void loguot() {
        WebElement loguotLink = driver.findElements(By.cssSelector("div#box-account a")).get(3);
        loguotLink.click();
    }

    /**
     * Returns random postcode
     *
     * @return {@code String} of postcode
     */
    public String randomPostcode() {
        StringBuilder postcode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            postcode.append((0 + (int) (Math.random() * 9)));
        }
        return postcode.toString();
    }

    /**
     * Returns random phone number
     *
     * @return {@code String} of phone number
     */
    public String randomPhoneNumber() {
        StringBuilder phone = new StringBuilder();
        phone.append("+");
        phone.append((1 + (int) (Math.random() * 999)));
        for (int i = 0; i < 10; i++) {
            phone.append((0 + (int) (Math.random() * 9)));
        }
        return phone.toString();
    }

    /**
     * Returns random email
     *
     * @return {@code String} of email
     */
    public String randomEmail() {
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            email.append((0 + (int) (Math.random() * 1000)));
        }
        email.append("@mail.ru");
        return email.toString();
    }
}
