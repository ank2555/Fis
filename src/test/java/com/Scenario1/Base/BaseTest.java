package com.Scenario1.Base;

//import io.netty.util.Constant;


import com.Scenario1.Utils.DriverManager;
import com.Scenario1.Utils.FrameworkConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.io.File;
import java.io.IOException;

import static com.Scenario1.Utils.FrameworkUtility.readConfigurationFile;

public class BaseTest {


    protected static WebDriver driver = null;

    private String browser;


    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public WebDriver getDriver() {

        return driver;
    }


    @BeforeMethod
    public void startDriver() {

        browser = readConfigurationFile("browser");
        System.out.println(browser);
        setDriver(new DriverManager().InitializeDriver(browser));


        getDriver().manage().window().maximize();

    }

    @AfterMethod
    public void quitDriver(@Optional String Browser, ITestResult result) throws IOException {

        browser = readConfigurationFile("browser");
        File DestFiles = null;
        if (result.getStatus() == ITestResult.FAILURE) {

            DestFiles = new File(FrameworkConstants.SCREENSHOTS + File.separator + browser + File.separator + "FAILURE" + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getMethod().getMethodName() + ".png");
            takeScreenShot(DestFiles);

        }


        if (result.getStatus() == ITestResult.SUCCESS) {

            DestFiles = new File(FrameworkConstants.SCREENSHOTS + File.separator + browser + File.separator + "SUCCESS" + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getMethod().getMethodName() + ".png");
            takeScreenShot(DestFiles);
            takeFullscreenshotashot(DestFiles);
        }
        File CaptchaFileTobeDeleted = new File(FrameworkConstants.CAPTCHA + File.separator + File.separator + "captcha.png");

        CaptchaFileTobeDeleted.delete();


        getDriver().quit();
    }

    public void takeScreenShot(File DestFiles) throws IOException {

        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();


        File SrcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(SrcFile, DestFiles);
    }


    public void takeFullscreenshotashot(File DestFiles) throws IOException {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(getDriver());

        ImageIO.write(screenshot.getImage(), "JPG", DestFiles);
    }


}
