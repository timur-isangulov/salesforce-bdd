package com.salesforce.bdd.pages;

import com.salesforce.bdd.settings.TestSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static com.salesforce.bdd.settings.TestSettings.wait;

/**
 * @author Timur Isangulov
 */
public class HomePage extends BasePage {

    private WebDriver driver;
    private LoginPage loginPage;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "user-info-logout")
    private WebElement logoutButton;

    @FindBy(id = "st-search-input")
    private WebElement searchField;

    @FindBy(css = ".header-st-search-button")
    private WebElement searchButton;


    /**
     * Login to Salesforce
     */
    public void login() {
        loginButton.click();
        loginPage.login();
        waitForTitle("Salesforce Developers");
        Assert.assertTrue(getTitle().contains("Salesforce Developers"));
    }

    /**
     * Logout from Salesforce
     */
    public void logout() {
        loginButton.click();
        logoutButton.click();
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        Assert.assertTrue(TestSettings.driver.findElement(By.cssSelector(".btn-primary")).isDisplayed());
        TestSettings.driver.close();
    }

    /**
     * 
     * @param str
     */
    public void search(String str) {
        wait.until(ExpectedConditions.visibilityOf(searchField)).click();
        searchField.sendKeys(str);
        searchButton.click();
        waitForTitle("Search");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result__title")));
    }
}
