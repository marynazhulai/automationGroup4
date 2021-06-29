package com.customertimes.test.framework.driver;

import com.customertimes.test.framework.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebdriverRunner {

    private static ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    private WebdriverRunner() {
    }

    public static WebDriver getWebDriver() {
        if (driver.get() == null) {
            switch (TestConfig.CONFIG.browser()) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                }
                default: {
                    if (TestConfig.CONFIG.remote()) {
                        try {
                            DesiredCapabilities capabilities = new DesiredCapabilities();
                            capabilities.setCapability("browserName", "chrome");
                            capabilities.setCapability("browserVersion", "86.0");
                            capabilities.setCapability("enableVNC", true);
                            driver.set(new RemoteWebDriver(new URL(TestConfig.CONFIG.seleniumServerUrl()), DesiredCapabilities.chrome()));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        WebDriverManager.chromedriver().setup();

                        driver.set(new ChromeDriver());
                    }
                }
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void closeWebDriver() {
        if (driver.get() != null) {
            driver.get().close();
            driver.remove();
        }
    }
}
