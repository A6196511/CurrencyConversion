package com;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverProvider.class);
    private static final ThreadLocal<WebDriver> singleton = new ThreadLocal<>();

    private static boolean isDriverNotNull() {
        return singleton.get() != null;
    }

    private static boolean isDriverNull() {
        return singleton.get() == null;
    }

    /**
     * Provide WebDriver context.
     *
     * @return {@link WebDriver}
     */
    public static WebDriver webDriver() {
        if (isDriverNull()) {
            LOGGER.info("Starting WebDriver...");
            singleton.set(chooseDriver());
        }
        return singleton.get();
    }

    /**
     * Close session and tear down context instance.
     */
    public static void stopDriver() {
        if (isDriverNotNull()) {
            webDriver().quit();
            LOGGER.info("Stopping WebDriver...");
            singleton.set(null);
        }
    }

    /**
     * Provides required context type
     *
     * @return {@link WebDriver} instance
     */
    private static WebDriver chooseDriver() {
        WebDriver driver = null;
        try {
            if (PropertyProvider.getProperty("BROWSER_NAME").equalsIgnoreCase("Firefox")) {
                LOGGER.info("Requested FFDriver...");
                String pathToDriver = "src/main/resources/drivers/geckodriver";
                String os = System.getProperty("os.name");
                if (os.contains("Linux"))
                    pathToDriver = pathToDriver + "_lin";
                else
                    pathToDriver = pathToDriver + "_win.exe";
                System.setProperty("webdriver.gecko.driver", pathToDriver);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("intl.accept_languages", "en,en-US");
                firefoxOptions.setProfile(firefoxProfile);
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
            } else if (PropertyProvider.getProperty("BROWSER_NAME").equalsIgnoreCase("Chrome")) {
                LOGGER.info("Requested ChromeDriver...");
                ChromeOptions options = new ChromeOptions();
                String pathToDriver = "src/main/resources/drivers/chromedriver";
                String os = System.getProperty("os.name");
                if (os.contains("Linux"))
                    pathToDriver = pathToDriver + "_lin";
                else
                    pathToDriver = pathToDriver + "_win.exe";
                System.setProperty("webdriver.chrome.driver", pathToDriver);
                options.addArguments("--lang=en-us");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
            }
        } catch (WebDriverException e) {
            LOGGER.error("!!!! Driver can't be started check your configuration \n" + e.getMessage());
        }
        return driver;
    }
}

