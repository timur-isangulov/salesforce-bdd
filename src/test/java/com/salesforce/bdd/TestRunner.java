package com.salesforce.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * @author Timur Isangulov
 */

@CucumberOptions(features = {"src/test/resources/featurefile"}, glue = {
        "com/salesforce/bdd/steps",
        "com/salesforce/bdd/settings"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
