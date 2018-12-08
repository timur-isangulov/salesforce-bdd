package com.salesforce.bdd.pages;

import com.salesforce.bdd.settings.TestSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Timur Isangulov
 */
abstract class BasePage {
    private WebDriver driver;

    BasePage(WebDriver driver) {
        if (driver == null)
            throw new IllegalArgumentException("Driver object is null");

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    String getTitle() {
        return TestSettings.driver.getTitle();
    }

    void waitForTitle(String str) {
        TestSettings.wait.until(ExpectedConditions.titleContains(str));
    }
}
