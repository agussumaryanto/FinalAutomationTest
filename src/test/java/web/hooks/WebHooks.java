package web.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import web.utils.ConfigReader;
import web.utils.DriverFactory;

public class WebHooks {

    @Before
    public void beforeAllTests() {
        System.out.println("=== Starting WEB scenario ===");
        // Initialize driver
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        // Open base URL
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @After
    public void afterAllTests(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        // Take screenshot if scenario fails
        try {
            if (scenario.isFailed() && driver != null) {
                final byte[] screenshot =
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
        // Quit driver
        try {
            DriverFactory.quitDriver();
        } catch (Exception e) {
            System.out.println("Driver already closed.");
        }
        System.out.println("=== Ending WEB scenario ===");
    }
}