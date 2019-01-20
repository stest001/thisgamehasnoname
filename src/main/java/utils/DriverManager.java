package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class DriverManager {

    private static final int TIMEOUT = 10;
    private static WebDriver driver;

    public static WebDriver getDriver() {
        System.setProperty(PropertyLoader.loadProperty("driver"), PropertyLoader.loadProperty("driver_path"));
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        }
        return driver;
    }
}