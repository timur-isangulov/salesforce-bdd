package com.salesforce.bdd.pages;

import com.salesforce.bdd.settings.TestSettings;
import org.junit.Assert;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.salesforce.bdd.settings.TestSettings.wait;

/**
 * @author Timur Isangulov
 */
public class SearchPage extends BasePage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ".result__title")
    private List<WebElement> resultList;

    @FindBy(id = "topic-title")
    private WebElement topicTitle;

    @FindBy(linkText = "Testing Apex")
    private WebElement testingApexLink;

    public String getURL(String str) {
        if (str.isEmpty())
            throw new InvalidArgumentException("String cannot be empty");

        for (WebElement element : resultList) {
            if (element.getText().split("\\|")[0].trim().equalsIgnoreCase(str)) {
                Assert.assertTrue(element.getText().split("\\|")[0].trim().equalsIgnoreCase(str));
                return element.getAttribute("href").trim();
            }
        }
        return "";
    }

    public Map<String, String> getResultSet() {
        waitForTitle("Search");
        Map<String, String> links = new HashMap<>();
        for (WebElement element : resultList) {
            String linkTitle = element.getText().trim();
            links.put(linkTitle, element.getAttribute("href"));
        }
        return links;
    }

    public void verifyLinks(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            TestSettings.driver.get(entry.getValue());
            wait.until(ExpectedConditions.titleIs(entry.getKey()));
            Assert.assertTrue(getTitle().equalsIgnoreCase(entry.getKey()));
        }
    }

    public void verifyLink(String link, String str) {
        TestSettings.driver.get(link);
        wait.until(ExpectedConditions.visibilityOf(topicTitle));
        Assert.assertTrue(topicTitle.getText().equalsIgnoreCase(str));
    }
}
