package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Darthyk on 21.03.2017.
 */
public class CheckGoodsPage extends CommonSeleniumTest {

    public static final String COLOR_RGBA_PATTERN = "(rgb)\\D+(?<red>\\d+)\\D+(?<green>\\d+)\\D+(?<blue>\\d+)\\D+";
    public static final String BOLD_FONT = "bold";
    public static final String LINE_THROUGH = "line-through";

    @Test
    public void checkGoodsPage() {
        getLitecard();

        WebElement goods = driver.findElement(By.cssSelector("div#box-campaigns a[class=link]"));
        final String mainPageGoodsName = goods.findElement(By.cssSelector("div.name")).getAttribute("textContent");
        final String mainPageRegularPrice = goods.findElement(By.cssSelector("s.regular-price"))
                .getAttribute("textContent");
        final String mainPageCampaignPrice = goods.findElement(By.cssSelector("strong.campaign-price"))
                .getAttribute("textContent");
        final String mainPageRegularPriceColor = goods.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("color");
        final String mainPageRegularPriceTextDecor = goods.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("text-decoration");
        final String mainPageCampaignPriceTextDecor = goods.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("font-weight");
        final String mainPageCampaignPriceColor = goods.findElement(By.cssSelector("strong.campaign-price"))
                .getCssValue("color");
        final String mainPageRegularFontSize = goods.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("font-size");
        final String mainPageCampaignFontSize = goods.findElement(By.cssSelector("strong.campaign-price"))
                .getCssValue("font-size");

        goods.click();

        final String goodsPageGoodsName = driver.findElement(By.cssSelector("div.content h1.title")).getAttribute("textContent");
        final String goodsPageRegularPrice = driver.findElement(By.cssSelector("div.information s.regular-price"))
                .getAttribute("textContent");
        final String goodsPageCampaignPrice = driver.findElement(By.cssSelector("div.information strong.campaign-price"))
                .getAttribute("textContent");
        final String goodsPageRegularPriceColor = driver.findElement(By.cssSelector("div.information s.regular-price"))
                .getCssValue("color");
        final String goodsPageRegularPriceTextDecor = driver.findElement(By.cssSelector("div.information s.regular-price"))
                .getCssValue("text-decoration");
        final String goodsPageCampaignPriceTextDecor = driver.findElement(By.cssSelector("div.information s.regular-price"))
                .getCssValue("font-weight");
        final String goodsPageCampaignPriceColor = driver.findElement(By.cssSelector("div.information strong.campaign-price"))
                .getCssValue("color");
        final String goodsPageRegularFontSize = driver.findElement(By.cssSelector("div.information s.regular-price"))
                .getCssValue("font-size");
        final String goodsPageCampaignFontSize = driver.findElement(By.cssSelector("div.information strong.campaign-price"))
                .getCssValue("font-size");

        Assert.assertEquals("Goods names are not equal", mainPageGoodsName, goodsPageGoodsName);
        Assert.assertEquals("Goods regular prices are not equal", mainPageRegularPrice, goodsPageRegularPrice);
        Assert.assertEquals("Goods campaign prices are not equal", mainPageCampaignPrice, goodsPageCampaignPrice);
        Assert.assertTrue("Goods text isn't correct", checkGrayStrikedText(mainPageRegularPriceColor,
                mainPageRegularPriceTextDecor));
        Assert.assertTrue("Goods text isn't correct", checkGrayStrikedText(goodsPageRegularPriceColor,
                goodsPageRegularPriceTextDecor));
        Assert.assertTrue("Goods text isn't correct", checkRedBoldText(mainPageCampaignPriceColor,
                mainPageCampaignPriceTextDecor));
        Assert.assertTrue("Goods text isn't correct", checkRedBoldText(goodsPageCampaignPriceColor,
                goodsPageCampaignPriceTextDecor));
        Assert.assertTrue("Font has incorrect size", goodsPageRegularFontSize.compareTo(goodsPageCampaignFontSize) < 0);
    }

    /**
     * Checks that text's color and decoration have expected values
     *
     * @param color {@code String} which represents RGBA model
     * @param textDecor text decoration
     * @return {@code true} if color and text decoration have expected values,
     *          otherwise {@code false}
     */
    public boolean checkGrayStrikedText(String color, String textDecor) {
        Integer red = null;
        Integer green = null;
        Integer blue = null;
        Pattern colorPattern = Pattern.compile(COLOR_RGBA_PATTERN);
        Matcher colorMatcher = colorPattern.matcher(color);
        if (colorMatcher.find()) {
            red = Integer.valueOf(colorMatcher.group("red"));
            green = Integer.valueOf(colorMatcher.group("green"));
            blue = Integer.valueOf(colorMatcher.group("blue"));
        } else {
            System.out.println("Can't parse colors from " + color);
        }
        return red.equals(green)&& green.equals(blue) && LINE_THROUGH.equals(textDecor);
    }

    /**
     * Checks that text's color and decoration have expected values
     *
     * @param color {@code String} which represents RGBA model
     * @param textDecor text decoration
     * @return {@code true} if color and text decoration have expected values,
     *          otherwise {@code false}
     */
    public boolean checkRedBoldText(String color, String textDecor) {
        Integer red = null;
        Integer green = null;
        Integer blue = null;
        Pattern colorPattern = Pattern.compile(COLOR_RGBA_PATTERN);
        Matcher colorMatcher = colorPattern.matcher(color);
        if (colorMatcher.find()) {
            red = Integer.valueOf(colorMatcher.group("red"));
            green = Integer.valueOf(colorMatcher.group("green"));
            blue = Integer.valueOf(colorMatcher.group("blue"));
        } else {
            System.out.println("Can't parse colors from " + color);
        }
        return red.compareTo(0) > 0 && green.equals(0) && blue.equals(0) && BOLD_FONT.equals(textDecor);
    }
}
