package com.salesforce.bdd.steps;

import com.salesforce.bdd.TestRunner;
import com.salesforce.bdd.pages.HomePage;
import com.salesforce.bdd.pages.SearchPage;
import com.salesforce.bdd.settings.TestSettings;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Timur Isangulov
 */
public class SearchStepDefs extends TestRunner {

    private String weblink;
    private HomePage homePage;
    private SearchPage searchPage;

    @Given("^I am logged in$")
    public void i_am_logged_in() {
        TestSettings.driver.get("https://developer.salesforce.com");
        homePage = new HomePage(TestSettings.driver);
        homePage.login();
    }

    @When("^I search for the \"([^\"]*)\"$")
    public void i_search_for_the(String arg1) {
        homePage.search(arg1);
    }

    @Then("^I should get \"([^\"]*)\" link in result$")
    public void i_should_get_link_in_result(String arg1) {
        searchPage = new SearchPage(TestSettings.driver);
        weblink = searchPage.getURL(arg1);
        Assert.assertTrue("Cannot find link", (!weblink.isEmpty() || searchPage.getURL(arg1).length() > 0));
    }

    @And("^retrieved links work$")
    public void retrieved_links_work() {
        searchPage.verifyLinks(searchPage.getResultSet());
    }

    @Then("^I click on the \"([^\"]*)\" link$")
    public void i_click_on_the_link(String arg1) {
        searchPage.verifyLink(weblink, arg1);
    }

    @Then("^\"([^\"]*)\" page opens$")
    public void page_opens(String arg1) {
        TestSettings.wait.until(ExpectedConditions.titleContains(arg1));
    }

    @Then("^I click on the \"([^\"]*)\" link on the page$")
    public void iClickOnTheLinkOnThePage(String arg0) {
        TestSettings.driver.findElement(By.linkText("Testing Apex")).click();
    }

    @Then("^I logout$")
    public void iLogout() {
        homePage.logout();
    }
}
