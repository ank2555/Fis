package com.Scenario1.Test;

import com.Scenario1.Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.Scenario1.Utils.FrameworkUtility.readConfigurationFile;

public class Scenario1 extends BaseTest {
    String BaseURL;


    @Test(invocationCount = 4)
    public void ebay() throws InterruptedException {

        driver = getDriver();
        BaseURL = readConfigurationFile("baseUrl");
        driver.get(BaseURL + "/");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
        WebElement cartText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='gh-cart__icon']")));

        System.out.println(cartText.getText());
        try {
            boolean flag = driver.findElement(By.xpath("//div[@id='close']")).isDisplayed();
            System.out.println("flag is "+flag);
            if (flag) {
                driver.findElement(By.xpath("//div[@id='close']")).click();

            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

//        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Add to cart']")));
        WebElement addToCart2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='atcBtn_btn_1'  or @class='ux-call-to-action']")));
//        WebElement addToCart3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='atcBtn_btn_1'  or @class='ux-call-to-action']")));
//

        Actions actions = new Actions(driver);

//        actions.moveToElement(addToCart2).click().build().perform();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        addToCart2.click();

        WebElement cartItems = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@role='img'])[1]")));

        Assert.assertEquals(cartItems.getText(), "1");
    }

}
