package testBase;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testUtils.Utils;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

/**
 * Description: Base Test is starting point for driver invocation.
 * WebDriver is a class which configures different drivers
 * code to handle Chrome browser * @author Mayank Tiwari
 */

public class BaseTest {

    public static WebDriver driver;

    public static void initialization() throws IOException {
        String executionEnvironment = System.getenv("executionEnvironment");
        if (executionEnvironment == null) {
            throw new IllegalArgumentException("Please specify the execution environment using -DexecutionEnvironment=local/container");
        }
        String browserName = Utils.getGlobalValues("browserName");

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            if (executionEnvironment.equalsIgnoreCase("local")) {
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
            }
            if (executionEnvironment.equalsIgnoreCase("container")) {
                options.addArguments("--start-maximized");
                options.setCapability(ChromeOptions.CAPABILITY,options);
                options.setCapability("browserName","chrome");
                options.setCapability("acceptsSslCerts","true");
                options.setCapability("javascriptEnabled","true");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                options.merge(capabilities);
                String Url = "http://localhost:4444/wd/hub";
                try {
                    driver = new RemoteWebDriver(new URL(Url), capabilities);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void explicitlyWait(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }
}
