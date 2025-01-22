package com.Scenario1.Test;

import com.Scenario1.Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static com.Scenario1.Utils.FrameworkUtility.readConfigurationFile;

public class Scenario1 extends BaseTest {
    String BaseURL;


    @Test
    public void ebay() {

        driver = getDriver();
        BaseURL = readConfigurationFile("baseUrl");
        driver.get(BaseURL + "/");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Reporter.log("Browser opened");

        WebElement sercahbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search']")));

        sercahbox.sendKeys("Book");
        sercahbox.submit();


        List<WebElement> lst = driver.findElements(By.xpath("//a[@class='s-item__link']"));

        for (WebElement elements : lst) {
            // System.out.println(elements.getAttribute("href"));

        }


        WebElement firstBook = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" (//a[@class='s-item__link'])[3]")));
        firstBook.click();

        Set<String> windows = driver.getWindowHandles();


        for (String w : windows) {

            System.out.println(driver.switchTo().window(w).getTitle());
            driver.switchTo().window(w);

        }

        String cartText = driver.findElement(By.xpath("//span[@class='gh-cart__icon']")).getText();
        System.out.println(cartText);

        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Add to cart']")));
        addToCart.click();

        WebElement cartItems = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@role='img'])[1]")));

        Assert.assertEquals(cartItems.getText(), "1");
    }

}
