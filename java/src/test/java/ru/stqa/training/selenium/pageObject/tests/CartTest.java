package ru.stqa.training.selenium.pageObject.tests;

import org.junit.Test;
import ru.stqa.training.selenium.pageObject.app.Application;

/**
 * Created by Darthyk on 30.03.2017.
 */
public class CartTest {
    final static int PRODUCT_NUMBER_FOR_TEST = 3;

    /**
     * Cart test
     */
    @Test
    public void cartTest() {
        Application application = new Application();
        application.addProductsToCart(PRODUCT_NUMBER_FOR_TEST);
        application.removeProductsFromCart(PRODUCT_NUMBER_FOR_TEST);
        application.quit();
    }
}
