package com.salesforce.bdd.pages;

import com.salesforce.bdd.settings.Credentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Timur Isangulov
 */
class LoginPage extends BasePage {

    private WebDriver driver;

    LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "Login")
    private WebElement loginButton;

    void login() {
        usernameInput.sendKeys(Credentials.getUsername());
        passwordInput.sendKeys(Credentials.getPassword());
        loginButton.click();
    }
}
