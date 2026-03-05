package web.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    public static void initDriver() {
        String browser = ConfigReader.getProperty("browser");
        // Set implicit wait from config
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*"); // WebHooks driver handling for CI stability
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Driver already closed.");
            }
            driver = null;
        }
    }
}