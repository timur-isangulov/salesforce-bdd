package com.salesforce.bdd.settings;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Timur Isangulov
 */
public class InitializeDriver {
    private WebDriver driver;

    private WebDriver standAloneStepUp(BrowserType bType) {
        switch (bType) {
            case Chrome:
                System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                return driver;

            case Firefox:
                System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\geckodriver.exe");
                driver = new FirefoxDriver();
                return driver;

            default:
                throw new NullPointerException("Driver not found");
        }
    }

    @Before(value = {"@chrome"})
    public void beforeChrome()  {
        setUpDriver(BrowserType.Chrome);
    }

    @After(value = {"@chrome"})
    public void afterChrome() {
        tearDownDriver();
    }

    @Before(value = {"@firefox"})
    public void beforeFirefox() {
        setUpDriver(BrowserType.Firefox);
    }

    @After(value = {"@firefox"})
    public void afterFirefox() {
        tearDownDriver();
    }

    private void setUpDriver(BrowserType bType)  {
        TestSettings.driver = standAloneStepUp(bType);
        TestSettings.driver.manage().window().maximize();
        TestSettings.wait = new WebDriverWait(TestSettings.driver, 30);
    }

    private void tearDownDriver( ) {
        if (TestSettings.driver != null)
            TestSettings.driver = null;
    }
}



